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
            String sql = "delete from LAST_SEARCHED where LAST_SEARCHED_ID NOT IN " +
                    "(select LAST_SEARCHED_ID from LAST_SEARCHED order by LAST_SEARCHED_ID desc limit 20);";
            stmt.executeUpdate(sql);

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
                60000 * 15
        );
    }

    public static void checkQueueThread(){
        System.out.println("x");
    }
}
