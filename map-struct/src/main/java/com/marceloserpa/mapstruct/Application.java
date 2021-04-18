package com.marceloserpa.mapstruct;

public class Application {

    public static void main(String[] args) {
        Book book = new Book();
        book.setTitle("The Shining");
        book.setNumberOfPages(512);
        book.setAuthor("Stephen King");
        book.setDescription("Danny is only five years old, but in the words of old Mr Hallorann he is a 'shiner', aglow with psychic voltage. When his father becomes caretaker of the Overlook Hotel, Danny's visions grow out of control. ..");
        book.setPublicationDate("1977/01/28");

        BookDTO bookDTO = BookMapper.INSTANCE.bookToBookDto(book);

        System.out.println(bookDTO.getTitle());
        System.out.println(bookDTO.getAuthor());
        System.out.println(bookDTO.getPages());
        System.out.println(bookDTO.getDescription());
        System.out.println(bookDTO.getPublicationDate());

    }


}
