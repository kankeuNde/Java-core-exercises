package com.rnk.design_patterns.singleton.connection_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DatabaseConnectionManager implements ConnectionPoolManager{
    // Singleton instance
    private static volatile DatabaseConnectionManager instance;

    // Configuration
    private volatile int maxConnections;

    // DB Credentials
    private static final String URL = "jdbc:postgresql://localhost:5432/singleton";
    // Username
    private static final String USERNAME = "postgres";
    // Password
    private static final String PASSWORD = "postgres";


    // Internal connection pool
    private final BlockingQueue<Connection> connectionPool;

    public DatabaseConnectionManager(int maxConnections) throws SQLException {
        this.maxConnections = maxConnections;
        this.connectionPool = new ArrayBlockingQueue<>(maxConnections);
        initializeConnectionPool();
    }

    private void initializeConnectionPool() throws SQLException {
        for(int i = 0; i < maxConnections; i++){
            connectionPool.add(createNewConnection());
        }
    }

    public Connection createNewConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static DatabaseConnectionManager getInstance(int maxConnections) throws SQLException {
        if(instance == null){
            synchronized (DatabaseConnectionManager.class){
                if(instance == null){
                    instance = new DatabaseConnectionManager(maxConnections);
                }
            }
        }
        return instance;
    }


    @Override
    public Connection getConnection() throws SQLException {
        try {
            return connectionPool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new SQLException("Interrupted while waiting for a connection", e);
        }
    }

    @Override
    public void releaseConnection(Connection connection) throws SQLException {
        if (connection != null){
            connectionPool.offer(connection);
        }
    }

    @Override
    public void closeAllConnections() throws SQLException {
        for(Connection conn: connectionPool)
            conn.close();
    }

    @Override
    public int getMaxConnections() {
        return maxConnections;
    }

    @Override
    public void setMaxConnections(int maxConnections) {
        this.maxConnections = maxConnections;
    }
}
