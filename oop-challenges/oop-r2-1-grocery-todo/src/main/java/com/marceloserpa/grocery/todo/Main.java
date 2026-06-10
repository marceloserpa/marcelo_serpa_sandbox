package com.marceloserpa.grocery.todo;

public class Main {
    static void main() {

        TodoListManager todoListManager = new TodoListManager();

        System.out.println("\nAdding items");
        todoListManager.addItem(new Item("Book X", 1));
        todoListManager.addItem(new Item("Pen", 1));
        todoListManager.addItem(new Item("Notebook", 2));

        for(Item item : todoListManager.getTodoList()){
            System.out.println(item);
        }

        System.out.println("\nUndo Last Operations");
        todoListManager.undoLastOperation();

        for(Item item : todoListManager.getTodoList()){
            System.out.println(item);
        }

        System.out.println("\nToggle as completed Operations");
        todoListManager.changeToggle(new Item("Book X", 1));
        for(Item item : todoListManager.getTodoList()){
            System.out.println(item);
        }

        System.out.println("\nUndo Last Operations");
        todoListManager.undoLastOperation();

        for(Item item : todoListManager.getTodoList()){
            System.out.println(item);
        }

        /**
         Adding items
         product: 'Book X' quantity: 1 completed: false
         product: 'Pen' quantity: 1 completed: false
         product: 'Notebook' quantity: 2 completed: false

         Undo Last Operations
         product: 'Book X' quantity: 1 completed: false
         product: 'Pen' quantity: 1 completed: false

         Toggle as completed Operations
         product: 'Book X' quantity: 1 completed: true
         product: 'Pen' quantity: 1 completed: false

         Undo Last Operations
         product: 'Book X' quantity: 1 completed: false
         product: 'Pen' quantity: 1 completed: false
         */
    }
}
