package com.books.app.model;

public class BookDto {

    private String name;
    private String author;

    public BookDto(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public BookDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
