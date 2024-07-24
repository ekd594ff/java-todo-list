package org.homework.step3;

import org.homework.step3.controller.TodoController;
import org.homework.step3.service.TodoService;
import org.homework.step3.view.InputView;
import org.homework.step3.view.OutputView;

public class Main {
    public static void main(String[] args) {
        TodoController todoController = new TodoController(
                new InputView(),
                new TodoService(),
                new OutputView()
        );
        todoController.run();
    }
}
