package com.institute.mcq.dao;

import com.institute.mcq.util.DBUtil;
import at.favre.lib.crypto.bcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public static boolean validateLogin(String username, String password) throws Exception {
        String sql = "SELECT password_hash FROM users WHERE username = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password_hash");
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), storedHash);
                    return result.verified;
                }
            }
        }
        return false;
    }

    public static int getUserId(String username) throws Exception {
        String sql = "SELECT id FROM users WHERE username = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }
}
