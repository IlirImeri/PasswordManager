package com.company;

import java.sql.*;

public class Database {
    private Connection c = null;
    private Statement stmt = null;
    private String UserName;
    private String Password;
    private String Email;
    private String Website;

    String sql = "INSERT INTO PasswordManager (UserName,Password,Email,Website) " +
            "VALUES (?, ?, ?, ?)";

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }



    public Connection connect(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    public void createTable(){
        try {
            c=connect();
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PasswordManager " +
                    "(UserName           TEXT     NOT NULL," +
                    " Password           TEXT     NOT NULL," +
                    " Email              TEXT     NOT NULL," +
                    " Website            TEXT     NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }

    public void insertData(String UserName, String Password, String Email, String Website){
        try {
            c=connect();
            stmt = c.createStatement();
            c.setAutoCommit(false);
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, UserName);
            ps.setString(2, Password);
            ps.setString(3, Email);
            ps.setString(4, Website);
            System.out.println("Data inserted successfully");
            ps.executeUpdate();
            ps.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void selectData(String searchFromWebsiteName){
        try {
            c=connect();
            stmt = c.createStatement();
            c.setAutoCommit(false);

            ResultSet rs = stmt.executeQuery( "SELECT * FROM PasswordManager WHERE Website LIKE '%" +searchFromWebsiteName+ "%'");

            while ( rs.next() ) {
                UserName = rs.getString("UserName");
                Password = rs.getString("Password");
                Email = rs.getString("Email");
                Website = rs.getString("Website");

                printData();
            }
            System.out.println("Operation done successfully");
            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void printData() {
        System.out.println("UserName: " + UserName + "\n" +
                "Password: " + Password + "\n" +
                "Email: " + Email + "\n" +
                "Website: " + Website + "\n");
    }
}