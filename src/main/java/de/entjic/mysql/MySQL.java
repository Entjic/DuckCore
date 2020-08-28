package de.entjic.mysql;

import de.entjic.files.File;
import de.entjic.files.FileContainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    private static MySQL instance = new MySQL();

    public static MySQL getInstance() {
        return instance;
    }


    private Connection connection;
    private String host;
    private String port;
    private String database;
    private String username;
    private String password;


    public void connect() {
        if (! isConnected()) {
            try {
                readLoginData();
                connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void readLoginData() {
        File file = FileContainer.getInstance().getFile("sql");
        host = file.getString("host", "127.0.0.1");
        port = file.getString("port", "3306");
        database = file.getString("database", "sample");
        username = file.getString("username", "root");
        password = file.getString("password", "1234");
    }

    public void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<String> get(String sql, String key) {
        if (connection == null) {
            connect();
        }
        List<String> strings = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            while (resultSet.next()) {
                strings.add(resultSet.getString(key));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return strings;
    }


    public boolean isConnected() {
        return connection != null;
    }

    public void update(String sql) {
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResult(String qry) {
        try {
            return connection.createStatement().executeQuery(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
