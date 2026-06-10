package com.marceloserpa.grocery.todo.commands;

public interface Command {

    void execute();

    void undo();

}
