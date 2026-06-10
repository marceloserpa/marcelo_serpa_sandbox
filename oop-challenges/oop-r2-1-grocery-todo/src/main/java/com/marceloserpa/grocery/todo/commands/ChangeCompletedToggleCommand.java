package com.marceloserpa.grocery.todo.commands;

import com.marceloserpa.grocery.todo.Item;

import java.util.List;

public class ChangeCompletedToggleCommand implements Command{

    private final List<Item> items;
    private final Item item;

    public ChangeCompletedToggleCommand(List<Item> todoList, Item item) {
        this.items = todoList;
        this.item = item;
    }


    @Override
    public void execute() {
        item.setCompleted(!item.isCompleted());
    }

    @Override
    public void undo() {
        item.setCompleted(!item.isCompleted());
    }
}
