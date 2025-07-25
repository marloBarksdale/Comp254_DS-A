package com.exercise3.lynden.flood;

import com.AbstractPriorityQueue;
import com.Entry;

import java.util.Comparator;


public class ArraySortedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    private static final int DEFAULT_CAPACITY = 16;

    private Entry<K,V>[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public ArraySortedPriorityQueue() {
        super();
        this.data = (Entry<K,V>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArraySortedPriorityQueue(Comparator<K> comp) {
        super(comp);
        this.data = (Entry<K,V>[]) new Entry[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public int size() { return size; }

    @Override
    public Entry<K,V> min() {
        if (isEmpty()) return null;
        return data[0]; // Min is always at index 0
    }

    @Override
    public Entry<K,V> removeMin() {
        if (isEmpty()) return null;

        Entry<K,V> minEntry = data[0];

        // Shift all elements one position to the left
        for (int i = 1; i < size; i++) {
            data[i - 1] = data[i];
        }

        data[size - 1] = null;
        size--;

        return minEntry;
    }

    @Override
    public Entry<K,V> insert(K key, V value) throws IllegalArgumentException {
        checkKey(key);

        if (size >= data.length) resize();

        Entry<K,V> newEntry = new PQEntry<>(key, value);

        // Find insertion position using binary search
        int insertPos = findInsertionPosition(key);

        // Shift elements right to make room
        for (int i = size; i > insertPos; i--) {
            data[i] = data[i - 1];
        }

        data[insertPos] = newEntry;
        size++;

        return newEntry;
    }

    private int findInsertionPosition(K key) {
        int left = 0, right = size;

        while (left < right) {
            int mid = left + (right - left) / 2;
            Entry<K,V> keyEntry = new PQEntry<>(key, null);

            if (compare(keyEntry, data[mid]) < 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K,V>[] newData = (Entry<K,V>[]) new Entry[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(data[i].getValue());
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {


        ArraySortedPriorityQueue<Integer, String> pq = new ArraySortedPriorityQueue<>();

        System.out.println("Testing ArraySortedPriorityQueue");
        System.out.println("=================================");

        // Test insertions
        pq.insert(5, "Five");
        pq.insert(2, "Two");
        pq.insert(8, "Eight");
        pq.insert(1, "One");
        pq.insert(7, "Seven");
        pq.insert(3, "Three");

        System.out.println("After insertions: " + pq);
        System.out.println("Size: " + pq.size());
        System.out.println("Min: " + pq.min().getValue());

        // Test removeMin
        System.out.println("\nRemoving elements:");
        while (!pq.isEmpty()) {
            Entry<Integer, String> removed = pq.removeMin();

             System.out.println("Removed: " + removed.getValue()+ ", Remaining: " + pq);
        }

        // Test edge cases
        System.out.println("\nTesting edge cases:");
        ArraySortedPriorityQueue<Integer, String> empty = new ArraySortedPriorityQueue<>();
        System.out.println("Empty PQ min(): " + empty.min());
        System.out.println("Empty PQ removeMin(): " + empty.removeMin());

        // Test with custom comparator (reverse order)
        System.out.println("\nTesting with reverse comparator:");
        ArraySortedPriorityQueue<Integer, String> reversePQ =
                new ArraySortedPriorityQueue<>((a, b) -> b.compareTo(a));

        reversePQ.insert(5, "Five");
        reversePQ.insert(2, "Two");
        reversePQ.insert(8, "Eight");
        reversePQ.insert(1, "One");

        System.out.println("Reverse PQ: " + reversePQ);
        while (!reversePQ.isEmpty()) {
            System.out.println("Removed: " + reversePQ.removeMin().getValue());
        }

        System.out.println("\nTime Complexities:");
        System.out.println("insert(k, v): O(n) - find position O(log n) + shift O(n)");
        System.out.println("removeMin(): O(1) - remove from front");
        System.out.println("min(): O(1) - access first element");
        System.out.println("size(): O(1) - return instance variable");
        System.out.println("isEmpty(): O(1) - check size == 0");
    }
}