package com.company.ds.stackAndQueue;

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
}
