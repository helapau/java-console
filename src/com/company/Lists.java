package com.company;

import java.util.Iterator;
import java.util.LinkedList;

public class Lists {

    public static void linkedListAsQueue() {
        // first in first out
        LinkedList<String> names = new LinkedList<>();
        names.add("Caleb");
        names.add("Sue");
        names.add("Sally");

        // LinkedList as a queue data structure
        // think of people in a line (in order they were added) queueing for roller coaster -> Caleb comes first, Sally is last
        System.out.println(names.remove()); // Caleb
        System.out.println(names.remove()); // Sue
        // note that ArrayList does not have .remove() method!
    }

    public static void linkedListAsStack() {
        LinkedList<String> names = new LinkedList<>(); // ArrayList does not have a push method
        names.push("Caleb");
        names.push("Sue");
        names.push("Sally");

        names.add(2, "Susan");



        Iterator<String> it = names.iterator();
        for (String n: names) {
            System.out.println(n);
        }

    }
}
