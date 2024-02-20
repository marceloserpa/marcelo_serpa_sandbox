package com.marceloserpa.dop.search;

public class Application {

    public static void main(String[] args) {
        System.out.println("Started ");

        var bookService = new BookService();
        var result1 = bookService.findByAuthor("Stephen");
        System.out.println(result1);

        System.out.println("--- ");
        var result2 = bookService.findByAuthor("Philip");
        System.out.println(result2);

        System.out.println("--- ");
        var result3 = bookService.findByAuthor("POE");
        System.out.println(result3);
    }
}
