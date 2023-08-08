package com.company;


import com.company.ds.LinkedList;

public class Main {





    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.insertAtHead(3);
        list.insertAtEnd(2);
        list.insertAtEnd(1);
        list.insertAtEnd(0);

        list.deleteByValue(0);

        System.out.println(list.toString());
    }
}
