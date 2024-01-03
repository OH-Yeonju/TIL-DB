package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateTest {
    public UpdateTest(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/spkpath?serverTimezone=UTC", "root", "ssafy");
    }

    public static void main(String[] args) {
        UpdateTest it = new UpdateTest();
        int cnt = it.modify("하하하", 20);
        if (cnt != 0) {
            System.out.println("수정 성공");
        } else {
            System.out.println("수정 실패");
        }

    }

    private int modify(String name, int age) {
        int cnt = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // DB 연결
            conn = getConnection();

            //SQL 실행 준비
            StringBuilder sql = new StringBuilder("update jdbctest \n");
            sql.append("set age = ? \n");
            sql.append("where name = ? \n");
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, age);
            pstmt.setString(2, name);

            //SQL 실행
            cnt = pstmt.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException e) {

                throw new RuntimeException(e);
            }
        }
        return cnt;
    }
}
