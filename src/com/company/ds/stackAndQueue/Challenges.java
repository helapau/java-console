package com.company.ds.stackAndQueue;

import com.company.ds.Stack;

public class Challenges {

    // generate binary numbers from 1 to n using a queue
    public static String[] generateBinary(int n) {
        String[] result = new String[n];
        Queue<String> q = new Queue<>(n + 1);
        q.enqueue("1");
        for (int i = 0; i < n; i++) {
            result[i] = q.dequeue();
            String c = result[i];
            String n1 = c + "0";
            String n2 = c + "1";
            q.enqueue(n1);
            q.enqueue(n2);
        }

        return  result;
    }

    public static <V> void reverseK(Queue<V> queue, int k) {
        if (queue.isEmpty() || k <= 0) return;

        int size = queue.getCurrentSize();
        V[] toReverese = (V[]) new Object[k];
        V[] toCopy = (V[]) new Object[size - k];

        for (int i = 0; i < k; i++) {
            V e = queue.dequeue();
            toReverese[i] = e;
        }
        for (int i = 0; i < toCopy.length; i++) {
            V e = queue.dequeue();
            toCopy[i] = e;
        }

        for (int i = k - 1; i >= 0; i--) {
            queue.enqueue(toReverese[i]);
        }

        for (int i = 0; i < toCopy.length; i++) {
            queue.enqueue(toCopy[i]);
        }

    }

    public static class QueueWithStack <V> {

        Stack<V> st1;
        Stack<V> st2;
        V topQ;

        public QueueWithStack(int maxSize){
            st1 = new Stack<V>(maxSize);
            st2 = new Stack<V>(maxSize);
            topQ = null;
        }

        public void enqueue(V value) {
            if (st1.isFull()) {
                System.out.println("Queue is full!");
                return;
            }
            st1.push(value);
            if (topQ == null) {
                topQ = value;
            }
        }
        public V dequeue() {
            V previous = null;
            while (true) {
                V e = st1.pop();
                if (e.equals(topQ)) {
                    topQ = previous;
                    while(!st2.isEmpty()) {
                        V v = st2.pop();
                        st1.push(v);
                    }
                    return e;
                }
                st2.push(e);
                previous = e;
            }
        }
        public boolean isEmpty(){
            return topQ == null;
        }
    }

    public class QueueWithStackImproved<V> {
        // improved performance of dequeue() so it's not O(n) on **every** dequeue
        Stack<V> st1;
        Stack<V> st2;

        public QueueWithStackImproved(int maxSize) {
            st1 = new Stack<V>(maxSize);
            st2 = new Stack<V>(maxSize);
        }

        public boolean isEmpty() {
            return st1.isEmpty() && st2.isEmpty();
        }

        public void enqueue(V value) {
            if (!st1.isFull()) {
                st1.push(value);
            }
        }

        public V dequeue() {
            if (isEmpty()) {
                return null;
            } else if (st2.isEmpty()) {
                // worst case O(n) - transfer of all elements between stack will take place only if stack2 is empty
                while (!st1.isEmpty()) {
                    V v = st1.pop();
                    st2.push(v);
                }
                return st2.pop();
            } else {
                // most of the time, we're doing st2.pop()
                return st2.pop();
            }
        }
    }

    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        Stack<Integer> sorted = new Stack<>(stack.getCurrentSize());
        Stack<Integer> temp = new Stack<>(stack.getCurrentSize());

        sorted.push(stack.pop());
        while(!stack.isEmpty()) {
            if (!sorted.isEmpty() && stack.top() >= sorted.top()) {
                sorted.push(stack.pop());
            } else {
                while(!sorted.isEmpty() && stack.top() < sorted.top()) {
                    temp.push(sorted.pop());
                }
                sorted.push(stack.pop());
                while(!temp.isEmpty()) {
                    sorted.push(temp.pop());
                }
            }
        }

        // copy values
        while(!sorted.isEmpty()) {
            stack.push(sorted.pop());
        }
    }
}
