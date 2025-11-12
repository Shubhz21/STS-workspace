package com.institute.mcq.servlet;

import com.institute.mcq.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/finalsubmit")
public class SubmitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int attemptId = Integer.parseInt(req.getParameter("attemptId"));
        try (Connection conn = DBUtil.getConnection()) {
            // verify attempt within time (or mark auto_submitted)
            String q = "SELECT a.started_at, e.duration_minutes, a.status FROM attempts a JOIN exams e ON a.exam_id=e.id WHERE a.id = ?";
            try (PreparedStatement ps = conn.prepareStatement(q)) {
                ps.setInt(1, attemptId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (!rs.next()) {
                        resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                    Timestamp started = rs.getTimestamp("started_at");
                    int dur = rs.getInt("duration_minutes");
                    String status = rs.getString("status");
                    Timestamp deadline = new Timestamp(started.getTime() + dur * 60L * 1000L);
                    Timestamp now = new Timestamp(System.currentTimeMillis());
                    if (now.after(deadline)) {
                        // time over -- mark auto_submitted
                        try (PreparedStatement up = conn.prepareStatement("UPDATE attempts SET status='auto_submitted', finished_at=NOW() WHERE id=?")) {
                            up.setInt(1, attemptId);
                            up.executeUpdate();
                        }
                        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        resp.getWriter().write("{\"error\":\"time_over\"}");
                        return;
                    }
                    // compute score: naive single choice check
                    double score = 0.0;
                    // query responses and options
                    String respSql = "SELECT r.question_id, r.selected_options FROM responses r WHERE r.attempt_id = ?";
                    try (PreparedStatement prs = conn.prepareStatement(respSql)) {
                        prs.setInt(1, attemptId);
                        try (ResultSet rrs = prs.executeQuery()) {
                            while (rrs.next()) {
                                int qid = rrs.getInt("question_id");
                                String selected = rrs.getString("selected_options"); // CSV of option ids (for single it is single id)
                                if (selected == null) continue;
                                String[] parts = selected.split(",");
                                // fetch correct options count and marks
                                try (PreparedStatement pQ = conn.prepareStatement("SELECT marks FROM questions WHERE id=?")) {
                                    pQ.setInt(1, qid);
                                    try (ResultSet rq = pQ.executeQuery()) {
                                        double qMarks = 1.0;
                                        if (rq.next()) qMarks = rq.getDouble("marks");
                                        // find correct option ids
                                        try (PreparedStatement pOpt = conn.prepareStatement("SELECT id FROM options WHERE question_id=? AND is_correct=1")) {
                                            pOpt.setInt(1, qid);
                                            try (ResultSet rc = pOpt.executeQuery()) {
                                                Set<String> correct = new HashSet<>();
                                                while (rc.next()) correct.add(String.valueOf(rc.getInt("id")));
                                                // compute simple scoring: full marks only if selected exactly equals correct set
                                                Set<String> sel = new HashSet<>();
                                                for (String s : parts) sel.add(s.trim());
                                                if (sel.equals(correct)) {
                                                    score += qMarks;
                                                } // else 0 (you can implement partial credit later)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    // update attempts
                    try (PreparedStatement up = conn.prepareStatement("UPDATE attempts SET status='submitted', finished_at=NOW(), score=? WHERE id=?")) {
                        up.setDouble(1, score);
                        up.setInt(2, attemptId);
                        up.executeUpdate();
                    }
                    resp.setContentType("application/json");
                    resp.getWriter().write("{\"status\":\"ok\",\"score\":" + score + "}");
                }
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
