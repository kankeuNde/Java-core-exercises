package com.rnk.generics;

import java.util.Objects;
import java.util.logging.Logger;

public class Pair<K, V>{
    private static final Logger logger = Logger.getLogger(Pair.class.getName());

    private K key;
    private V value;

    /**
     * Construct a pair of key - value objects
     * @param key Generic type
     * @param value Generic type
     */
    public Pair(K key, V value) throws IllegalArgumentException{
        if(key == null)
            throw new IllegalArgumentException("Cannot create a Pair with a null key");
        logger.info(() -> String.format("Pair construction with K: %s and V: %s", key, value));
        this.key = key;
        this.value = value;
    }

    /**
     * Returns the value of key K
     * @return K
     */
    public K getKey() {
        return this.key;
    }

    /**
     * Returns the value of value V
     * @return V
     */
    public V getValue() {
        return this.value;
    }

    /**
     * Sets the key K element
     * @param key of Generic type K to set
     */
    public void setKey(K key){
        if(key == null)
            throw new IllegalArgumentException("Cannot create a Pair with a null key");
        logger.info(() -> String.format("Setting key: %s", key));
        this.key = key;
    }

    /**
     * Sets the value V element
     * @param value of Generic type V to set
     */
    public void setValue(V value){
        logger.info(() -> String.format("Setting value: %s", value));
        this.value = value;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Pair<?, ?> pair)) return false;
        //Pair pair = (Pair) obj;
        return Objects.equals(key, pair.getKey()) && Objects.equals(value, pair.getValue());
    }

    @Override
    public int hashCode(){
        return Objects.hash(key, value);
    }

    @Override
    public String toString(){
        return String.format("Pair[key=%s, value=%s]", key, value);
    }
}
