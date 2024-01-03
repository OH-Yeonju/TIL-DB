package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

    public ConnectionTest() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        ConnectionTest ct = new ConnectionTest();
        Connection conn = ct.getConnection();
        if (conn != null) {
            System.out.println("DB 연결 성공~!");
        }
    }

    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spkpath?serverTimezone=UTC", "root", "ssafy");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
