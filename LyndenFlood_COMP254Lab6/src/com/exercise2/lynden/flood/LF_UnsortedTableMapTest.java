package com.exercise2.lynden.flood;


public class LF_UnsortedTableMapTest {

    public static void main(String[] args) {
        System.out.println("=== UnsortedTableMap containKey() Test ===\n");

        LF_UnsortedTableMap<String, String> map = new LF_UnsortedTableMap<>();

        // Add some entries
        map.put("key1", "value1");
        map.put("key2", null);  // Null value - this is the critical case
        map.put("key3", "value3");

        System.out.println("Map contents:");
        System.out.println("  key1 -> value1");
        System.out.println("  key2 -> null");
        System.out.println("  key3 -> value3");
        System.out.println();

        // Test the containKey method
        System.out.println("Testing containKey() vs get():");
        testKey(map, "key1");     // Exists with value
        testKey(map, "key2");     // Exists with null value
        testKey(map, "missing");  // Doesn't exist

        System.out.println("\n=== Key Point ===");
        System.out.println("containKey() resolves the ambiguity when get() returns null:");
        System.out.println("- If containKey() = true and get() = null → key exists with null value");
        System.out.println("- If containKey() = false and get() = null → key doesn't exist");
    }

    private static void testKey(LF_UnsortedTableMap<String, String> map, String key) {
        boolean contains = map.containKey(key);
        String value = map.get(key);

        System.out.printf("  %-8s: containKey=%-5s, get()=%s%n",
                key, contains, value == null ? "null" : value);
    }
}