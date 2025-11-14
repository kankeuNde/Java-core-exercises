package com.rnk.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerExampleUsingBlockingQueue {

    static int counter = 0;

    public synchronized static void increment(){
        for(int i=0; i<1000; i++){
            counter = counter + 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> queue =  new ArrayBlockingQueue<>(20);



        for(int i =0; i<100; i++){
            new Thread(() -> {
                increment();
                System.out.println("Counter: " + counter);
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

        Thread.sleep(1000);
        System.out.println("final counter: " + counter);
        /*Thread producerThread = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    //queue.add(i); // throw exception for full queue
                    queue.put(i); // block the queue if queue is full
                    System.out.println("Produced: " + i);
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
                    //Integer value = queue.poll(); // returns null for empty queue
                    Integer value = queue.take();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        );

        consumerThread.start();
        producerThread.start();*/

    }
}
