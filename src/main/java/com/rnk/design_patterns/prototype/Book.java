package com.rnk.design_patterns.prototype;

import java.util.List;

public class Book implements Cloneable {
    private int isbn;
    private String title;
    private List<String> authors;

    public Book() {
    }

    public Book(int isbn, String title, List<String> authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors = authors;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                '}';
    }

    @Override
    public Book clone() {
            Book clone = new Book();
            clone.setTitle(this.getTitle());
            clone.setIsbn(this.getIsbn());
            clone.setAuthors(this.getAuthors());
            return clone;
    }
}
