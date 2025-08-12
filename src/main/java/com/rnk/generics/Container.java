package com.rnk.generics;

import java.util.logging.Logger;

/**
 * A generic container holding a single non-null item of type T.
 * @param <T>
 */
public class Container<T> {
    private static final Logger logger = Logger.getLogger(Container.class.getName());

    private T item;

    /**
     * Construct a Container of type T
     * @param item
     * @throws IllegalArgumentException
     */
    public Container(T item) throws IllegalArgumentException{
        if(item == null)
            throw new IllegalArgumentException("Item cannot be null");
        this.item = item;
    }

    /**
     * get the current item
     * @return
     */
    public T getItem(){
        logger.info("Getting item: " + item);
        return this.item;
    }

    /**
     * set the item
     * @param item
     * @throws IllegalArgumentException : Cannot set a null object
     */
    public void setItem(T item) throws IllegalArgumentException{
        if(item == null)
            throw new IllegalArgumentException("Item cannot be null");
        logger.info("Setting item to: " + item);
        this.item = item;
    }
}
