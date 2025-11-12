package com.institute.mcq.servlet;

import com.institute.mcq.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/saveanswer")
public class SaveAnswerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int attemptId = Integer.parseInt(req.getParameter("attemptId"));
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        String selected = req.getParameter("selected"); // CSV for multi
        
        try (Connection conn = DBUtil.getConnection()) {
            // Check if attempt is still within time by looking up started_at and exam duration
            String q = "SELECT a.started_at, e.duration_minutes FROM attempts a JOIN exams e ON a.exam_id=e.id WHERE a.id = ?";
            try (PreparedStatement ps = conn.prepareStatement(q)) {
                ps.setInt(1, attemptId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Timestamp started = rs.getTimestamp("started_at");
                        int dur = rs.getInt("duration_minutes");
                        Timestamp deadline = new Timestamp(started.getTime() + dur * 60L * 1000L);
                        Timestamp now = new Timestamp(System.currentTimeMillis());
                        if (now.after(deadline)) {
                            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            resp.getWriter().write("{\"error\":\"time_over\"}");
                            return;
                        }
                    } else {
                        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }
                }
            }
            // insert or update responses (simple approach using unique constraint on attempt+question if created)
            String up = "INSERT INTO responses (attempt_id, question_id, selected_options, answered_at) " +
                        "VALUES (?,?,?,NOW()) " +
                        "ON DUPLICATE KEY UPDATE selected_options=VALUES(selected_options), answered_at=NOW()";
            try (PreparedStatement ps2 = conn.prepareStatement(up)) {
                ps2.setInt(1, attemptId);
                ps2.setInt(2, questionId);
                ps2.setString(3, selected);
                ps2.executeUpdate();
            }
            resp.setContentType("application/json");
            resp.getWriter().write("{\"status\":\"ok\"}");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
