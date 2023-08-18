package com.company.ds;

public class Queue<E> {
    private int front;
    private int back;
    private E[] arr;
    private int capacity;
    public int size;

    public Queue() {
        capacity = 3;
        arr = (E[]) new Object[capacity];
        size = 0;
        front = -1;
        back = -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E getTop() {
        return arr[front];
    }

    private void increaseCapacity() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = arr[i];
        }
        this.arr = newArray;
    }

    // insert e at the back
    public void enqueue(E e) {
        if (size == capacity) {
            increaseCapacity();
        }

        arr[back + 1] = e;
        back += 1;
        if (isEmpty()) {
            front = back;
        }
        size += 1;
    }

    // remove e from the front
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        E e = arr[front];
        arr[front] = null;
        front += 1;
        size -= 1;
        return e;
    }

}
