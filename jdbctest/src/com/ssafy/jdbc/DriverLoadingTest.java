package com.ssafy.jdbc;

public class DriverLoadingTest {
    public DriverLoadingTest(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("드라이버 로딩 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        new DriverLoadingTest();
    }
}
