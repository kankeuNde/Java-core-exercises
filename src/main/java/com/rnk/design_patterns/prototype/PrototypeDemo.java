package com.rnk.design_patterns.prototype;

public class PrototypeDemo {
    public static void main(String[] args) {
        BookShop bookShop = new BookShop();
        bookShop.setTitle("New Bookshop");
        bookShop.loadData();

        BookShop nBookshop = new BookShop();
        nBookshop = bookShop.clone();
        nBookshop.setTitle("Another Bookshop");

        bookShop.getListOfBooks().remove(0);
        System.out.println(bookShop);
        System.out.println(nBookshop);
    }
}
