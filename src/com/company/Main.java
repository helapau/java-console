package com.company;


import com.company.ds.LinkedList;
import com.company.ds.Queue;
import com.company.ds.Stack;


import java.util.*;

public class Main {




    public static void main(String[] args) {
        Queue<Character> q = new Queue<>();
        q.enqueue('A');
        q.dequeue();
        q.enqueue('b');
        q.enqueue('c');
        q.enqueue('d');
        q.enqueue('Z');
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        q.enqueue('1');
        System.out.println();











    }
}
