package com.rnk.design_patterns.prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookShop implements Cloneable{
    private String title;
    private List<Book> listOfBooks;

    public BookShop() {
        this.listOfBooks = new ArrayList<>();
    }

    public BookShop(String title, List<Book> listOfBooks) {
        this.title = title;
        this.listOfBooks = listOfBooks;
    }

    public void loadData(){
        if(listOfBooks == null)
            listOfBooks = new ArrayList<>();
        for(int i=1; i<=10; i++) {
            Book book = new Book(((int) (Math.random() * 1000000) + 5678493), "Book" + i, Arrays.asList("author 1", "author 2"));
            listOfBooks.add(book);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }

    public void setListOfBooks(List<Book> listOfBooks) {
        this.listOfBooks = listOfBooks;
    }

    @Override
    public String toString() {
        return "BookShop{" +
                "title='" + title + '\'' +
                ", listOfBooks=" + listOfBooks +
                '}';
    }

    @Override
    public BookShop clone() {
            BookShop clone = new BookShop();
            for(Book book: this.listOfBooks){
                clone.getListOfBooks().add(book);
            }
            return clone;
    }
}
