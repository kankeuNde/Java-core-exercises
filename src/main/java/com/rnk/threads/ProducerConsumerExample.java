package com.rnk.threads;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerExample {

    private int capacity;
    private Queue<Integer> queue = new LinkedList<>();

    public ProducerConsumerExample(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int value) throws InterruptedException {
        while(queue.size() == capacity){
            System.out.println("Queue Capacity full!");
            wait();
        }
        queue.add(value);
        System.out.println("Produced: " + value);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while(queue.isEmpty()){
            System.out.println("Empty Queue!");
            wait();
        }
        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notifyAll();
        return value;
    }

    public static void main(String[] args) {
        ProducerConsumerExample pce = new ProducerConsumerExample(5);

        Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pce.produce(i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        );

        Thread consumerThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    pce.consume();
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        );

        consumerThread.start();
        producerThread.start();

    }
}
