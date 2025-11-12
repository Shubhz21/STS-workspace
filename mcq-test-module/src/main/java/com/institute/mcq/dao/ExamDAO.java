package com.institute.mcq.dao;

import com.institute.mcq.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExamDAO {
    public static Integer getDurationMinutes(int examId) throws Exception {
        String sql = "SELECT duration_minutes FROM exams WHERE id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, examId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("duration_minutes");
            }
        }
        return null;
    }
}
