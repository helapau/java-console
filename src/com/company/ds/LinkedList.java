package com.company.ds;

import java.util.Objects;

public class LinkedList<T> {

    class Node<T> {
        T data;
        Node next;

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

        protected boolean hasNext() {
            return this.next != null;
        }
    }

    Node head;
    int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void insertAtHead(T data) {
        // data will become the new head node
        Node previousHead = this.head;
        this.head = new Node(data, previousHead);
        this.size += 1;
    }

    public void insertAtEnd(T data) {
        Node newNode = new Node(data, null);
        if (isEmpty()) {
            this.head = newNode;
        } else {
            Node currentNode = this.head;
            while (currentNode.hasNext()) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
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
        size++;
    }

    public void deleteHead() {
        if (isEmpty()) return;
        Node newHead = this.head.next;
        this.head = newHead;
        size--;
    }

    public void deleteByValue(T data) {
        if (isEmpty()) return;

        Node currentNode = this.head;
        Node previousNode = null;
        while (currentNode.hasNext()) {
            if (currentNode.data.equals(data)) {
                break;
            }
            previousNode = currentNode;
            currentNode = previousNode.next;
        }
        if (previousNode == null) {
            deleteHead();
        } else {
            previousNode.next = currentNode.next;
            size--;
        }
    }
}