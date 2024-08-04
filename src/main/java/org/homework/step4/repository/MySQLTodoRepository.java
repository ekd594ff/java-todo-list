package org.homework.step4.repository;

import org.homework.step4.entity.Todo;
import org.homework.step4.dto.CreateTodoDTO;
import org.homework.step4.enums.Status;
import org.homework.step4.jdbc.JDBCConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MySQLTodoRepository implements TodoRepository {

    private static JDBCConnectionPool connectionPool;
    private static MySQLTodoRepository mySQLTodoRepository;

    public static MySQLTodoRepository from(JDBCConnectionPool connectionPool) {
        if(mySQLTodoRepository == null) {
            MySQLTodoRepository.connectionPool = connectionPool;
            createTodoTable();
            mySQLTodoRepository = new MySQLTodoRepository();
        }
        return mySQLTodoRepository;
    }

    private static int executeUpdate(String query) {
        return connectionPool.getConnection().map(connection -> {
            try {
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate(query);
                statement.close();
                return result;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }).orElse(-1);
    }

    private Optional<ResultSet> selectTodoById(String query, int id) {
        return connectionPool.getConnection().map(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                return preparedStatement.executeQuery();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        });
    }

    private Optional<ResultSet> insertTodo(String query, Todo todo) {
        return connectionPool.getConnection().map(connection -> {
            String getQuery = "select * from Todo where description=? and status=? and deadline=?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, todo.getId());
                preparedStatement.setString(2, todo.getDescription());
                preparedStatement.setString(3, todo.getStatus().getValue());
                preparedStatement.setString(4, todo.getDeadline().toString());

                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement(getQuery);
                preparedStatement.setString(1, todo.getDescription());
                preparedStatement.setString(2, todo.getStatus().getValue());
                preparedStatement.setString(3, todo.getDeadline().toString());

                return preparedStatement.executeQuery();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        });
    }

    private Optional<Todo> deleteTodo(int id) {
        return this.select(id).flatMap(todo -> connectionPool.getConnection().map(connection -> {
            String query = "DELETE FROM Todo WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return todo;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }));
    }

    private Optional<Todo> updateTodo(int id, String description) {
        String query = "UPDATE Todo SET description = ? WHERE id = ?";
        return connectionPool.getConnection().map(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, description);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
                return this.select(id).orElse(null);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        });
    }

    public static int createTodoTable() {
        String query = "CREATE TABLE IF NOT EXISTS Todo (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "description VARCHAR(128), " +
                "status VARCHAR(32), " +
                "deadline DATETIME)";
        return executeUpdate(query);
    }

    private List<Todo> selectTodoList() {
        String query = "SELECT * FROM Todo WHERE deadline <= DATE_ADD(NOW(), INTERVAL 7 DAY) ORDER BY deadline ASC";
        return connectionPool.getConnection().map(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Todo> todoList = new ArrayList<>();
                while (resultSet.next()) {
                    todoList.add(this.toTodoItem(resultSet));
                }
                return todoList;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }).orElse(List.of());
    }

    private List<Todo> selectSearchTodoList(String keyword) {
        String query = "SELECT * FROM Todo WHERE description LIKE ?";
        return connectionPool.getConnection().map(connection -> {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, "%" + keyword + "%");
                ResultSet resultSet = preparedStatement.executeQuery();
                List<Todo> todoList = new ArrayList<>();
                while (resultSet.next()) {
                    todoList.add(this.toTodoItem(resultSet));
                }
                return todoList;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                connectionPool.releaseConnection(connection);
            }
        }).orElse(List.of());
    }

    private Todo toTodo(ResultSet resultSet) {
        int index = 1;
        int id = 0;
        String description = "";
        Status status = Status.valueOf("INCOMPLETE");
        LocalDateTime deadline = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            do {
                if (!resultSet.next()) {
                    return null;
                }
                id = resultSet.getInt(index++);
                description = resultSet.getString(index++);
                status = Status.of(resultSet.getString(index++));
                deadline = LocalDateTime.parse(resultSet.getString(index++), dateTimeFormatter);
                index = 1;
            } while (resultSet.next());
            return new Todo(id, description, status, deadline);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Todo toTodoItem(ResultSet resultSet) {
        int index = 1;
        int id = 0;
        String description = "";
        Status status = Status.valueOf("INCOMPLETE");
        LocalDateTime deadline = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            id = resultSet.getInt(index++);
            description = resultSet.getString(index++);
            status = Status.of(resultSet.getString(index++));
            deadline = LocalDateTime.parse(resultSet.getString(index++), dateTimeFormatter);
            return new Todo(id, description, status, deadline);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Optional<Todo> select(int id) {
        String query = "SELECT * FROM Todo where id = ?";
        return this.selectTodoById(query, id).map(this::toTodo);
    }

    @Override
    public Todo insert(CreateTodoDTO createTodoDTO) {
        String query = "INSERT INTO Todo VALUES (?, ?, ?, ?)";
        Todo todo = createTodoDTO.toTodo();
        return insertTodo(query, todo).map(this::toTodo).orElse(null);

    }

    @Override
    public Optional<Todo> delete(int id) {
        return this.deleteTodo(id);
    }

    @Override
    public Optional<Todo> update(int id, String description) {
        return this.updateTodo(id, description);
    }

    @Override
    public List<Todo> getTodoList() {
        return this.selectTodoList();
    }

    @Override
    public List<Todo> getSearchTodoList(String keyword) {
        return this.selectSearchTodoList(keyword);
    }

    @Override
    public void clearConnections() {
        this.connectionPool.allConnectionsClose();
    }
}
