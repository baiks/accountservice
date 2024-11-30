package com.account.service;

import java.util.*;

public class Tests {

    public static void main(String[] args) {
        int nums[] = {1, 3, 4, 5, 6};
        int k = 3;
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.add(1);
        priorityQueue.add(4);
        priorityQueue.add(2);
        System.out.println(priorityQueue);
        for (int i = 0; i < nums.length; i++) {
            priorityQueue.add(nums[i]);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        int result = (int) priorityQueue.peek();
        System.out.println(result);
        System.currentTimeMillis();
    }
}
