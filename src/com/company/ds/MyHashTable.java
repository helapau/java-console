package com.company.ds;

import java.util.Objects;

public class MyHashTable<K, V> {

    private static class HashNode<K, V> {
        K key;
        V value;
        final int hashCode;

        HashNode<K, V> next;

        HashNode(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
    }

    private HashNode<K, V>[] bucketArray;
    private int capacity;
    private int size; // how many buckets are filled

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        bucketArray = new HashNode[capacity];
    }

    public MyHashTable() {
        this(16);
    }

    public int size() {
        return size;
    }

    private int getBucketIndex(int hashCode) {
        int index = hashCode % capacity;
        return Math.abs(index);
    }

    private void checkLoadAndRehash() {
        double load = (1.0 * size) / capacity;
        if ( load >= 0.7) {
            HashNode<K, V>[] temp = bucketArray;
            capacity *= 2;
            size = 0;
            bucketArray = new HashNode [capacity];
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public void put(K key, V value) {
        int hashCode = Objects.hashCode(key);
        int index = getBucketIndex(hashCode);
        HashNode<K, V> node = bucketArray[index];

        while (node != null) {
            if (node.key.equals(key) && node.hashCode == hashCode) {
                node.value = value;
                return;
            }
            node = node.next;
        }

        size++;
        HashNode<K, V> newNode = new HashNode<>(key, value, hashCode);
        node = bucketArray[index];
        if (node == null) {
            bucketArray[index] = newNode;
        } else {
            while (node.next != null) {
                node = node.next;
            }
            node.next = newNode;
        }

        checkLoadAndRehash();

    }

    public V get(K key) {
        int hashCode = Objects.hashCode(key);
        int index = getBucketIndex(hashCode);
        HashNode<K, V> node = bucketArray[index];
        while (node != null) {
            if (node.key.equals(key) && node.hashCode == hashCode) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    public V remove(K key) {
        int hashCode = Objects.hashCode(key);
        int index = getBucketIndex(hashCode);
        HashNode<K, V> node = bucketArray[index];
        HashNode<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key) && node.hashCode == hashCode) {
                break;
            }
            prev = node;
            node = node.next;
        }
        if (node == null) {
            return null;
        }
        if (prev != null) {
            prev.next = node.next;
        } else {
            bucketArray[index] = node.next;
        }
        size--;
        return node.value;

    }

    public boolean isEmpty() {
        return size == 0;
    }


    public static void test() {
        MyHashTable<String, Integer> map = new MyHashTable<>(4);
        map.put("ab", 1);
        map.put("cd", 2);
        map.put("ef", 3);
        System.out.println(map.isEmpty());
        System.out.println(map.get("cd"));
        System.out.println(map.remove("ab"));
        System.out.println(map.size());
    }












}
