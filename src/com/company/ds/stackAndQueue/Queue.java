package com.company.ds.stackAndQueue;

public class Queue<E> {
    int maxSize;
    int currentSize;
    int front;
    int back;
    E[] arr;

    public Queue() {
        maxSize = 4;
        front = -1;
        back = -1;
        arr = (E[]) new Object[maxSize];
        currentSize = 0;
    }

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        front = -1;
        back = -1;
        arr = (E[]) new Object[maxSize];
        currentSize = 0;
    }

    public boolean isFull() {
        return (back + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return back == front && back == -1;
    }

    private void increaseCapacity() {
        maxSize *= 2;
        E[] newArray = (E[]) new Object[maxSize];
        for (int i = 0; i < arr.length; i++) {
            newArray[i] = arr[i];
        }
        this.arr = newArray;
    }

    public void enqueue(E e) {
        if (isFull()) {
            increaseCapacity();
        }
        if (isEmpty()) {
            front += 1;
        }
        back = (back  + 1) % maxSize;
        arr[back] = e;
        currentSize++;
    }

    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E e = arr[front];
        arr[front] = null;
        front = (front + 1) % maxSize;
        if (front == 0 && back == 0) {
            front = -1;
            back = -1;
        }
        currentSize--;
        return e;
    }

    public int getCurrentSize() {
        return currentSize;
    }
}