package com;

import com.exercise1.lynden.flood.LF_ChainHashMapTest;
import com.exercise2.lynden.flood.LF_UnsortedTableMapTest;


public class LF_LabRunner {

    public static void main(String[] args) {
        System.out.println("COMP254 Lab Assignment 6 - Maps and Hash Tables");
        System.out.println("=".repeat(50));

        // Run Exercise 1
        LF_ChainHashMapTest.main(args);

        System.out.println("=".repeat(50));

        // Run Exercise 2
        LF_UnsortedTableMapTest.main(args);
    }
}