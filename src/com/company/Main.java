package com.company;


import com.company.ds.stackAndQueue.Challenges;
import com.company.ds.stackAndQueue.Queue;

import java.util.Arrays;

public class Main {




    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        for (int i = 1; i < 6; i++) {
            q.enqueue(i);
        }
        Challenges.reverseK(q, 3);
        System.out.println();











    }
}
