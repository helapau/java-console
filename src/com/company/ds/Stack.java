package com.company.ds;

public class Stack<E> {

    private E[] arr;
    private int capacity;
    private int size;
    private int top;

    public Stack() {
        capacity = 2;
        size = 0;
        arr = (E[]) new Object[capacity];
        top = -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E getTop() {
        return arr[top];
    }

    private void increaseCapacity() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = arr[i];
        }
        this.arr = newArray;
    }

    public void push(E e) {
        if (size == capacity) {
            increaseCapacity();
        }

        arr[top + 1] = e;
        top += 1;
        size += 1;
    }

    public E pop() {
        top -= 1;
        E e = arr[top + 1];
        arr[top + 1] = null;
        size -= 1;
        return e;
    }

}
