package com.gfnqueuechecker.backend;

import java.sql.*;

public class ProcessThreads {

    private static Connection connection;

    public static void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager
                .getConnection("jdbc:h2:file:./database;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE",
                        "dbuser","pass@");
    }


    public static void lastSearchedThread(){
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM last_searched";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Timestamp timestamp = rs.getTimestamp("game_id");
                System.out.println();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        lastSearchedThread();
                    }
                },
                5000
        );
    }

    public static void checkQueueThread(){
        System.out.println("x");
    }
}
