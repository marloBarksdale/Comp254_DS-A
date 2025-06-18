package com.exercise2.lynden.flood;

import java.util.Arrays;
import java.util.Scanner;
//Lynden Flood
//301413453
public class Question2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] arr = {10, 5, 20, 8, 30, 15};

        System.out.print("Enter value X: ");
        int X = scanner.nextInt();

        int[] result = findGreaterElements(arr, X);

        System.out.println(Arrays.toString(result));
    }

    public static int[] findGreaterElements(int[] arr, int X) {
        Arrays.sort(arr); // Sort the array to enable binary search

        int index = binarySearch(arr, X);// Find the index of the first element greater than X

        //If no elements are greater than X, return an empty array
        if (index == arr.length) {
            return new int[0];
        }

        return Arrays.copyOfRange(arr, index, arr.length);
    }

    public static int binarySearch(int[] arr, int X) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;

            // Check if the middle element is less than or equal to X
            if (arr[mid] <= X) {
                left = mid + 1; //Move to the right half if the middle element is less than or equal to X
            } else {
                right = mid; //Move to the left half if the middle element is greater than X
            }
        }
        return left;
    }
}