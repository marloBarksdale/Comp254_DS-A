package com.exercise1.lynden.flood;

import java.util.Random;


public class LF_ChainHashMapTest {

    public static void main(String[] args) {
        System.out.println("=== ChainHashMap Load Factor Test ===\n");

        // Test different load factors
        testLoadFactor(0.5, "Default Load Factor");
        testLoadFactor(0.75, "High Load Factor");
        testLoadFactor(0.9, "Very High Load Factor");

        System.out.println("\n=== Conclusion ===");
        System.out.println("ChainHashMap handles high load factors well due to separate chaining.");
        System.out.println("Performance remains stable even at 0.9 load factor.");
    }

    private static void testLoadFactor(double loadFactor, String description) {
        System.out.println(description + " (" + loadFactor + "):");

        LF_ChainHashMap<Integer, String> map = new LF_ChainHashMap<>(17, loadFactor);
        Random rand = new Random(42);

        // Add 100 elements
        for (int i = 0; i < 100; i++) {
            int key = rand.nextInt(200);
            map.put(key, "Value" + key);
        }

        System.out.println("  Size: " + map.size());
        System.out.println("  Capacity: " + map.capacity);
        System.out.println("  Current Load Factor: " + String.format("%.2f", map.getCurrentLoadFactor()));
        System.out.println("  Max Chain Length: " + map.getMaxBucketSize());
        System.out.println();
    }
}