package com.rnk.collections;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LinkedListqueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("John");
        queue.add("Jane");
        queue.add("Doe");
        System.out.println("Queue: "+queue);

        //Accessing the head of the queue
        String head = queue.peek();
        System.out.println("Head of queue: "+head);

        //Removing elements from the queue
        String removedElement = queue.poll();
        System.out.println("Removed Element: "+removedElement);
        System.out.println("Queue after removal: "+queue);

        //======================== Priority Queue ===============================
        Queue<Integer> nums = new PriorityQueue<>();
        nums.add(10);
        nums.add(20);
        nums.add(5);
        System.out.println("Priority Queue: "+nums);

        //Accessing the head of the queue
        Integer headNum = nums.peek();
        System.out.println("Head of Priority Queue: "+headNum);

        // Remove elements from the queue
        Integer removedNum = nums.poll();
        System.out.println("Removed Num: "+removedNum);
        System.out.println("Priority Queue after removal: "+nums);

        //===================Dequeue===================================
        Queue<String> deque = new ArrayDeque<>();
        deque.add("John");
        deque.add("Jane");
        deque.add("Doe");
        System.out.println("Deque: "+deque);

        //Accessing the head of the deque
        String headdeque = deque.peek();
        System.out.println("Head of deque:" + headdeque);

        //Removing elements from deque
        String remDeque = deque.poll();
        System.out.println("Removed element:" + remDeque);
        System.out.println("Deque after removal: " + deque);
    }
}
