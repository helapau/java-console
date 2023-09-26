package com.company.ds;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class LinkedList<T> {

    public static class Node<T> {
        public T data;
        public Node next;
        Node previous;

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o.getClass() != getClass()) return false;
            Node<?> node = (Node<?>) o;
            return data.equals(node.data) && Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(data, next);
        }

        public boolean hasNext() {
            return this.next != null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public Node head;
    Node tail;
    int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        Node node = this.head;
        StringBuilder s = new StringBuilder();
        while (node != null) {
            s.append(""+ node.data);
            if (node.hasNext()) {
                s.append(" <-> ");
            }
            node = node.next;
        }
        return s.toString();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtHead(T data) {
        if (isEmpty()) {
            this.head = new Node(data, null);
            this.tail = this.head;
        } else {
            Node previousHead = this.head;
            this.head = new Node(data, previousHead);
            previousHead.previous = this.head;
        }
        size++;
    }

    public void insertAtEnd(T data) {
        if (isEmpty()) {
            insertAtHead(data);
            return;
        }
        Node newTail = new Node(data, null);
        newTail.previous = this.tail;
        this.tail.next = newTail;
        this.tail = newTail;
        this.size++;
    }

    public void insertAfter(T data, T previous) {
        // find first occurrence of previous and insert data after it
        if (isEmpty()) {
            insertAtHead(data);
            return;
        }

        Node current = this.head;
        while (current.hasNext() && !current.data.equals(previous)) {
            current = current.next;
        }

        Node newNode = new Node(data, current.next);
        current.next = newNode;
        newNode.previous = current;
        size++;
    }

    public void deleteHead() {
        if (isEmpty()) return;
        if (this.tail.equals(this.head)) {
            this.head = null;
            this.tail = null;
            size--;
            return;
        }
        Node newHead = this.head.next;
        this.head = newHead;
        this.head.previous = null;
        size--;
    }

    public void deleteByValue(T data) {
        if (isEmpty()) return;

        Node currentNode = this.head;
        while (currentNode.hasNext()) {
            if (currentNode.data.equals(data)) {
                break;
            }
            currentNode = currentNode.next;
        }

        if (currentNode.previous == null) {
            deleteHead();
        } else {
            currentNode.previous.next = currentNode.next;
            if (currentNode.hasNext()) {
                currentNode.next.previous = currentNode.previous;
            }
            size--;
        }
    }

    public Node findMiddle() {
        Node node = this.head;
        Node fast = node;
        while (fast != null && fast.hasNext()) {
            fast = fast.next.next;
            if (fast != null) {
                node = node.next;
            }

        }
        return node;
    }

    public Node nthNodeFromEnd(int n) {
        if (isEmpty()) {
            return null;
        }
        if (n == size) {
            return this.head;
        }
        if (n > size) return null;
        
        Node current = this.head;
        int visited = 1;
        int stop = size - n;
        while (visited < stop) {
            current = current.next;
            visited++;
        }
        return current.next;

    }

    public static <T> void removeDuplicates(LinkedList<T> list) {
        Set<Object> visited = new HashSet<>();
        Node current = list.head;
        Node previous = null;

        while (current != null) {
            if (visited.contains(current.data)) {
                previous.next = current.next;
                list.size--;
            } else {
                visited.add(current.data);
            }
            previous = current;
            current = current.next;
        }
    }

    public static <T> LinkedList<T> union(LinkedList<T> list1, LinkedList<T> list2) {
        // if A is not empty, then union with an empty set is A
        if (list1.isEmpty()) return list2;
        if (list2.isEmpty()) return list1;

        LinkedList<T> result = list1;
        Node list1Tail = result.head;
        while (list1Tail.hasNext()) {
            list1Tail = list1Tail.next;
        }
        Node otherListHead = list2.head;
        list1Tail.next = otherListHead;
        LinkedList.removeDuplicates(result);
        return result;
    }

    public static <T> LinkedList<T> intersection(LinkedList<T> list1, LinkedList<T> list2) {
        LinkedList<T> result = new LinkedList<T>();
        if (list1.isEmpty() || list2.isEmpty()) {
            // intersection with an empty set is the empty set
            return result;
        }

        Node node1 = list1.head;
        while(node1 != null) {
            Node node2 = list2.head;
            while (node2 != null) {
                if (node2.data.equals(node1.data)) {
                    result.insertAtEnd((T) node1.data);
                }
                node2 = node2.next;
            }
            node1 = node1.next;
        }
        LinkedList.removeDuplicates(result);
        return result;
    }
}
