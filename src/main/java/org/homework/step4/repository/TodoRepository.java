package org.homework.step4.repository;

import org.homework.step4.entity.Todo;
import org.homework.step4.dto.CreateTodoDTO;
import org.homework.step4.jdbc.JDBCConnectionPool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public interface TodoRepository {
    Optional<Todo> select(int id);

    Todo insert(CreateTodoDTO createTodoDTO);

    Optional<Todo> delete(int id);

    Optional<Todo> update(int id, String description);

    List<Todo> getTodoList();

    List<Todo> getSearchTodoList(String keyword);

    void clearConnections();

    static TodoRepository createRepository() throws SQLException {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("src/main/java/org/homework/step4/application.properties")) {
            properties.load(input);
            String appMode = (String) properties.get("app.mode");
            switch (appMode) {
                case "development":
                    return MapTodoRepository.getInstance();
                case "production":
                    String url = properties.getProperty("db.url");
                    String username = properties.getProperty("db.username");
                    String password = properties.getProperty("db.password");
                    int poolSize = Integer.parseInt(properties.getProperty("db.poolsize"));
                    JDBCConnectionPool jdbcConnectionPool;
                    jdbcConnectionPool = JDBCConnectionPool.of(url, username, password, poolSize);
                    return MySQLTodoRepository.from(jdbcConnectionPool);
                default:
                    return MapTodoRepository.getInstance();
            }
        } catch (IOException | SQLException ex) {
            return MapTodoRepository.getInstance();
        }
    }
}
