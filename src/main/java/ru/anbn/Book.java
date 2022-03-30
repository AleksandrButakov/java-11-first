package ru.anbn;

import java.util.Objects;

public class Book {
    String author;
    String text;

    public Book(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public Book() {

    }

    String doReadBook() {
        return "The book is read! Autor: " + author + " with text: " + text;
    }

    /* для работы методом equals с объектами необходимо переопределить метод
       и переопределить hashCode(). Вызов списка производится клавишами Alt + Insert
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(author, book.author) && Objects.equals(text, book.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, text);
    }


}
