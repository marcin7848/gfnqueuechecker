package com.gfnqueuechecker.backend;

import java.sql.*;

public class ProcessThreads {

    private static Connection connection;

    public static void initialize() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        connection = DriverManager
                .getConnection("jdbc:h2:file:./database;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE",
                        "dbuser", "pass@");
    }


    public static void lastSearchedThread() {
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

    public static void checkQueueThread() {
        try {
            //TODO: delete to old

            Statement stmt = connection.createStatement();
            String sql = "select * from CHECK_QUEUE WHERE PROCESS = 0 OR PROCESS = 1 order by ADD_TIME limit 1";
            ResultSet rs = stmt.executeQuery(sql);

            if (!rs.isBeforeFirst()) {
                new java.util.Timer().schedule(
                        new java.util.TimerTask() {
                            @Override
                            public void run() {
                                checkQueueThread();
                            }
                        },
                        3000
                );
                return;
            }

            rs.next();
            long id = rs.getLong("CHECK_QUEUE_ID");
            long gameId = rs.getLong("GAME_ID");
            long serverId = rs.getLong("SERVER_ID");

            sql = "select * from CONFIG where CONFIG_NAME = 'Authorization'";
            ResultSet rs2 = stmt.executeQuery(sql);
            rs2.next();
            String authorization = rs2.getString("CONFIG_VALUE");

            sql = "select * from CONFIG where CONFIG_NAME = 'X-Device-Id'";
            ResultSet rs3 = stmt.executeQuery(sql);
            rs3.next();
            String xDeviceId = rs3.getString("CONFIG_VALUE");

            sql = "select * from GAME where GAME_ID = " + gameId;
            ResultSet rs4 = stmt.executeQuery(sql);
            rs4.next();
            long appId = rs4.getLong("APP_ID");

            sql = "select * from SERVER where SERVER_ID = " + serverId;
            ResultSet rs5 = stmt.executeQuery(sql);
            rs5.next();
            String serverHost = rs5.getString("SERVER_HOST");

            System.out.println(authorization);
            System.out.println(xDeviceId);
            System.out.println(id);
            System.out.println(appId);
            System.out.println(serverHost);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
