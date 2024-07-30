package org.homework.step4.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

public class JDBCConnectionPool {
    private final ConcurrentLinkedQueue<Connection> connections;

    public JDBCConnectionPool(String url, String username, String password, int poolSize) throws SQLException {
        connections = new ConcurrentLinkedQueue<>();
        IntStream.range(0, poolSize).forEach(i -> {
            try {
                connections.add(DriverManager.getConnection(url, username, password));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public synchronized Optional<Connection> getConnection() {
        return Optional.ofNullable(connections.poll());
    }

    public synchronized void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    public synchronized void allConnectionsClose() {
        connections.forEach(connection -> {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
