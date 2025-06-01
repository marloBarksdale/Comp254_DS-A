/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Demonstration of algorithms for testing element uniqueness.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class Uniqueness {

    /**
     * Returns true if there are no duplicate elements in the array.
     */
    public static boolean unique1(int[] data) {
        int n = data.length;
        for (int j = 0; j < n - 1; j++)
            for (int k = j + 1; k < n; k++)
                if (data[j] == data[k]) return false;                    // found duplicate pair
        return true;                           // if we reach this, elements are unique
    }

    /**
     * Returns true if there are no duplicate elements in the array.
     */
    public static boolean unique2(int[] data) {
        int n = data.length;
        int[] temp = Arrays.copyOf(data, n);   // make copy of data
        Arrays.sort(temp);                     // and sort the copy
        for (int j = 0; j < n - 1; j++)
            if (temp[j] == temp[j + 1])            // check neighboring entries
                return false;                      // found duplicate pair
        return true;                           // if we reach this, elements are unique
    }

    // Average-case time complexity: O(n)
    // Slower in practice due to autoboxing (int → Integer) and GC overhead from temporary object creation
    public static boolean unique3(int[] data) {

        Set<Integer> map = new HashSet<>();
        for (int i : data) {

            if (!map.add(i)) // Duplicate detected: value already exists in the set

                return false;
        }
        return true; //No duplicates
    }

    // Uses a boolean array to flag which values have been seen.
    // Assumes all values in 'data' are non-negative and max(data) is not too large.
    public static boolean unique4(int[] data) {
        int max = 0;
        for (int i : data) { //Find the maximum of the array
            max = Math.max(max, i);
        }

        // Create an array to record values of data[] that have been seen
        //Uses less memory than the copy in unique2 because boolean uses 1 byte and int uses 4
        boolean[] bucket = new boolean[max + 1];
        for (int i : data) {
            if (bucket[i]) { // If the value was already seen, return false
                return false;
            }
            bucket[i] = true; // Otherwise, mark it as seen
        }
        return true;
    }

}
