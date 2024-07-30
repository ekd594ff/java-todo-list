package org.homework.step4;

import org.homework.step4.controller.TodoController;
import org.homework.step4.jdbc.JDBCConnectionPool;
import org.homework.step4.repository.MapTodoRepository;
import org.homework.step4.repository.MySQLTodoRepository;
import org.homework.step4.repository.TodoRepository;
import org.homework.step4.service.TodoService;
import org.homework.step4.view.InputView;
import org.homework.step4.view.OutputView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private static Properties properties = new Properties();

    public static void main(String[] args) {

        TodoRepository todoRepository = null;

        try (InputStream input = new FileInputStream("src/main/java/org/homework/step4/application.properties")) {
            properties.load(input);
            String appMode = (String) properties.get("app.mode");
            switch (appMode) {
                case "development":
                    todoRepository = new MapTodoRepository();
                    break;
                case "production":
                    String url = properties.getProperty("db.url");
                    String username = properties.getProperty("db.username");
                    String password = properties.getProperty("db.password");
                    int poolSize = Integer.parseInt(properties.getProperty("db.poolsize"));
                    JDBCConnectionPool jdbcConnectionPool;
                    jdbcConnectionPool = new JDBCConnectionPool(url, username, password, poolSize);
                    todoRepository = new MySQLTodoRepository(jdbcConnectionPool);
                    break;
                default:
                    todoRepository = new MapTodoRepository();
            }
        } catch (IOException | SQLException ex) {
            todoRepository = new MapTodoRepository();
        }


        TodoController todoController = new TodoController(
                new InputView(),
                new TodoService(todoRepository),
                new OutputView()
        );
        todoController.run();
    }
}
