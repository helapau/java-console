package com.company.ds.stackAndQueue;

public class Queue<E> {

    private int capacity;
    private E[] arr;
    int front;
    int back;
    private int size;

    public Queue() {
        capacity = 4;
        arr = (E[]) new Object[capacity];
        front = -1;
        back = -1;
    }

    public Queue(int capacity) {
        this();
        capacity = capacity;
    }

    public int getCurrentSize() {
        return size;
    }

    public void enqueue(E e) {
        if (isFull()) {
            increaseCapacity();
        }
        arr[back + 1] = e;
        if (isEmpty()) {
            front++;
        }
        back++;
        size++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E e = arr[front];
        arr[front] = null;
        front++;
        if (front == capacity || front > back) {
            front = -1;
            back = -1;
        }
        size--;
        return e;
    }

    public boolean isEmpty() {
        return front == back  && front == -1;
    }

    private boolean isFull() {
        return back == arr.length - 1;
    }

    private void increaseCapacity() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        this.arr = newArray;
    }

    public E top() {
        return arr[front];
    }


}
