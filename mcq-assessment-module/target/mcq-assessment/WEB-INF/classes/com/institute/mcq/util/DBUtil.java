package com.institute.mcq.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
    private static HikariDataSource ds;

    static {
        try {
            Properties p = new Properties();
            try (InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("app.properties")) {
                p.load(is);
            }
            HikariConfig cfg = new HikariConfig();
            cfg.setJdbcUrl(p.getProperty("jdbc.url"));
            cfg.setUsername(p.getProperty("jdbc.user"));
            cfg.setPassword(p.getProperty("jdbc.password"));
            cfg.setMaximumPoolSize(Integer.parseInt(p.getProperty("jdbc.pool.max", "10")));
            ds = new HikariDataSource(cfg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
