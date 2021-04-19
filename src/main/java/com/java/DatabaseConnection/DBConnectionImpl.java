package com.java.DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectionImpl {

    static String url = "jdbc:postgresql://localhost:5432/reflections";
    static String user = "refadmin";
    static String password = "password";
    static Connection connection = null;


    private static Connection getConnection() {
        if(connection != null)
        {
            return connection;
        }
        try{
            connection = DriverManager.getConnection(url,user,password);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return connection;
    }

    public static PreparedStatement getPreparedStatement(String query)
    {
        PreparedStatement ps = null;
        try {
            ps = getConnection().prepareStatement(query);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return ps;
    }


}
