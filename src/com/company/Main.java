package com.company;


import com.company.ds.Stack;
import com.company.ds.stackAndQueue.Challenges;
import com.company.ds.stackAndQueue.Queue;

import java.util.Arrays;

public class Main {




    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        // 12, 13, 14, 10
        // -25, -35, -49, -1
        s.push(10);
        s.push(-49);
        s.push(-35);
        s.push(-25);

        Challenges.sortStack(s);
        while (!s.isEmpty()) {
            System.out.println(s.pop());
        }











    }
}
