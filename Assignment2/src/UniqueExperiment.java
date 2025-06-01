
import java.util.*;

public class UniqueExperiment {

    static final int MAX_SIZE = 200_000_000;
     // size of pre-generated array
    static final int TIME_LIMIT_MS = 60_000;

    static int[] masterArray;

    public static void main(String[] args) {
        generateMasterArray();


        System.out.println("Testing unique4...");
        int max4 = findMaxN(Uniqueness::unique4);
        System.out.println("Max n for unique4: " + max4);

        System.out.println("Testing unique3...");
        int max3 = findMaxN((int[]data)->Uniqueness.unique3(data));
        System.out.println("Max n for unique3: " + max3);

        System.out.println("Testing unique2...");
        int max2 = findMaxN(Uniqueness::unique2);
        System.out.println("Max n for unique2: " + max2);

        System.out.println("Testing unique1...");
        int max1 = findMaxN(Uniqueness::unique1);
        System.out.println("Max n for unique1: " + max1);


    }

    // Step 1: Pre-generate array with unique values
    static void generateMasterArray() {
        masterArray = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            masterArray[i] = i;
        }
    }

    // Step 2: Binary search for max n within time limit
    static int findMaxN(UniqueFunction func) {
        int low = 1_000; // Minimum array size to begin testing with
        int high = MAX_SIZE;
        int best = -1; // Largest value of n that runs within the time limit

        while (low <= high) {
            int mid = (low + high) / 2;
            int[] sub = Arrays.copyOf(masterArray, mid); // Copy the first 'mid' elements to create a test array of size n

            long start = System.currentTimeMillis(); // Record the start time before running the function
            func.apply(sub); // Run the uniqueness function on the array of size 'mid'
            long duration = System.currentTimeMillis() - start;

            System.out.println("n = " + mid + " took " + duration + " ms"); // Print the time taken for the current input size

            if (duration <= TIME_LIMIT_MS) {
                best = mid;  // Update best if this size ran within the allowed time
                low = mid + 1; // Try a larger size
            } else {
                high = mid - 1; // Try a smaller size
            }
        }
        return best;
    }

    // Interfaces for lambda-friendly method reference
    interface UniqueFunction {
        boolean apply(int[] data);
    }








}
