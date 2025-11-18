package com.rnk.design_patterns.singleton.connection_manager;

import java.sql.Connection;
import java.sql.SQLDataException;
import java.sql.SQLException;

public interface ConnectionPoolManager {
    Connection getConnection() throws SQLException;
    void releaseConnection(Connection connection) throws SQLException;
    void closeAllConnections() throws SQLException;
    int getMaxConnections();
    void setMaxConnections(int maxConnections);
}
