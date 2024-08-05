package org.homework.step4;

import org.homework.step4.config.DBConfiguration;
import org.homework.step4.controller.TodoController;
import org.homework.step4.repository.TodoRepository;
import org.homework.step4.service.TodoService;
import org.homework.step4.view.InputView;
import org.homework.step4.view.OutputView;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        TodoController todoController = new TodoController(
                new InputView(),
                new TodoService(TodoRepository.from(DBConfiguration.getInstance())),
                new OutputView()
        );
        todoController.run();
    }
}
