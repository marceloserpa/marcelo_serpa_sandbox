package com.marceloserpa.grocery.todo;

import com.marceloserpa.grocery.todo.commands.AddItemCommand;
import com.marceloserpa.grocery.todo.commands.ChangeCompletedToggleCommand;
import com.marceloserpa.grocery.todo.commands.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class TodoListManager {

    private final Stack<Command> operations = new Stack<>();
    private final List<Item> todoList = new ArrayList<>();


    public void addItem(Item item) {
        AddItemCommand command = new AddItemCommand(todoList, item);
        command.execute();
        this.operations.add(command);
    }

    public void undoLastOperation(){
        Command command = operations.pop();
        command.undo();
    }

    public List<Item> getTodoList(){
        return Collections.unmodifiableList(todoList);
    }

    public void changeToggle(Item item) {
        Item item1 = todoList.stream().filter(p -> p.getProduct().equals(item.getProduct())).findFirst().orElseThrow(() -> new IllegalArgumentException("Not found product!"));
        ChangeCompletedToggleCommand changeCompletedToggleCommand = new ChangeCompletedToggleCommand(todoList, item1);
        changeCompletedToggleCommand.execute();
        this.operations.add(changeCompletedToggleCommand);
    }
}
