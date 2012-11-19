package ru.leks13.feedback;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sql {

    String ip;
    String port;
    String base;
    String user;
    String password;

    Sql() throws IOException {
        Properties prop = new Properties();
        try {
            String fileName = "/tmp/config.cfg";
            InputStream is = new FileInputStream(fileName);
            prop.load(is);
            ip = prop.getProperty("ip", "localhost");
            port = prop.getProperty("port", "5432");
            base = prop.getProperty("db", "db");
            user = prop.getProperty("user", "example");
            password = prop.getProperty("password", "example");
        } catch (FileNotFoundException ex) {
            ip = "localhost";
            port = "5432";
            base = "db";
            user = "example";
            password = "example";
        }

    }

    public void greateBase() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String query;
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/" + base, user, password);
            boolean res;
            java.sql.Statement st = (java.sql.Statement) connection.createStatement();
            query = "create table feedback (id serial,mail varchar(50), phone varchar(12), message varchar(1000), status varchar(15));";
            st.execute(query);
            st.close();
        } catch (SQLException e) {
            if (e.toString().equals("org.postgresql.util.PSQLException: ERROR: relation \"feedback\" already exists")) {
                System.out.println("INFO: Base exists");
            } else {
                Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public void addMessage(String mail, String phone, String message) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/" + base, user, password);
            boolean res;
            java.sql.Statement st = (java.sql.Statement) connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("insert into feedback (mail,phone,message,status) values(?,?,?,?);");
            ps.setString(1, mail);
            ps.setString(2, phone);
            ps.setString(3, message);
            ps.setString(4, "Not processed");
            ps.execute();
            st.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
    }

    public String listOfMessage() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        ResultSet rs;
        String res = "";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/" + base, user, password);
            java.sql.Statement st = (java.sql.Statement) connection.createStatement();
            String query = "select * from \"public\".feedback";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString("id");
                String mail = rs.getString("mail");
                String phone = rs.getString("phone");
                String message = rs.getString("message");
                String status = rs.getString("status");
                res += "<tr><td>" + id + "</td><td>" + mail + "</td><td>" + phone + "</td><td>" + message + "</td><td>" + status + "</td></tr><br>";
            }
            st.close();
        } finally {
            connection.close();
        }
        return res;
    }

    void changeStatus(String id, String status) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String query;
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://" + ip + ":" + port + "/" + base, user, password);
            boolean res;
            int i = Integer.parseInt(id);
            java.sql.Statement st = (java.sql.Statement) connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("UPDATE feedback SET status =? WHERE id =?;");
            ps.setString(1, status);
            ps.setInt(2, i);
            ps.execute();
            st.close();
        } finally {
            connection.close();
        }

    }
}
