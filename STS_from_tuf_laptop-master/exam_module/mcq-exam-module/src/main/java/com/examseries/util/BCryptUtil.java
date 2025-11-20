package com.examseries.util;



import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Simple utility to hash and verify passwords using BCrypt.
 */
public class BCryptUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Hash raw password.
     *
     * @param raw raw password
     * @return hashed password
     */
    public static String hashPassword(String raw) {
        if (raw == null) return null;
        return encoder.encode(raw);
    }

    /**
     * Verify raw password against hashed.
     *
     * @param raw raw password
     * @param hashed hashed password
     * @return true if match
     */
    public static boolean matches(String raw, String hashed) {
        if (raw == null || hashed == null) return false;
        return encoder.matches(raw, hashed);
    }
}

