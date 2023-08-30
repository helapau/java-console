package com.company.ds.stackAndQueue;

/**
 * Implement two stacks using one array
 * @param <V>
 */
public class TwoStacks<V> {
    private int maxSize;
    private V[] array;
    private int capacity1;
    private int capacity2;
    private int top1 = -1;
    private int top2;


    @SuppressWarnings("unchecked")
    public TwoStacks(int max_size) {
        this.maxSize = max_size;
        array = (V[]) new Object[max_size];//type casting Object[] to V[]

        capacity1 = Math.floorDiv(max_size, 2);
        capacity2 = max_size - capacity1;
        top2 = max_size;
    }

    private boolean isFull1() {
        return top1 == capacity1;
    }

    private boolean isFull2() {
        return (maxSize - capacity2) == top2;
    }


    //insert at top of first stack
    public void push1(V value) {
        if (isFull1()) {
            System.out.println("Stack 1 is full!");
            return;
        }
        top1++;
        array[top1] = value;
    }

    //insert at top of second stack
    public void push2(V value) {
        if (isFull2()) {
            System.out.println("Stack 2 is full!");
            return;
        }
        top2--;
        array[top2] = value;
    }

    private boolean isEmpty1() {
        return top1 == -1;
    }

    //remove and return value from top of first stack
    public V pop1() {
        if (isEmpty1()) return null;

        V element = array[top1];
        top1--;
        return element;
    }

    private boolean isEmpty2() {
        return top2 == maxSize;
    }
    //remove and return value from top of second stack
    public V pop2() {
        if (isEmpty2()) return null;

        V element = array[top2];
        top2++;
        return element;
    }
}
