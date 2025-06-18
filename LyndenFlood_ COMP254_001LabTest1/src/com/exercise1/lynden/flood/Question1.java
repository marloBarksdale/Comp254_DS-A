package com.exercise1.lynden.flood;
//Lynden Flood
//301413453
public class Question1 {



    public static void main(String[] args) {
        int a = 20;
        int b = 82;
        System.out.println("GCD of " + a + " and " + b + " is: " + gcd(a, b));
    }

    public static int gcd(int a, int b) {
        if (b == 0) { //base case
            return a;
        }
        return gcd(b, a % b); //recursive call
    }
}
