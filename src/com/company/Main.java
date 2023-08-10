package com.company;


import com.company.ds.LinkedList;


import java.util.*;

public class Main {




    public static void main(String[] args) {
    LinkedList<Integer> list1 = new LinkedList<>();
    list1.insertAtEnd(15);
    list1.insertAtEnd(22);
    list1.insertAtEnd(8);
    list1.insertAtEnd(22);


    System.out.println(list1.nthNodeFromEnd(4));










    }
}
