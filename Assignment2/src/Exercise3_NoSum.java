import java.util.Arrays;
import java.util.function.Function;

public class Exercise3_NoSum {


    public static void main(String[] args) {
        int[] data = generateArray(20);
        System.out.println(Arrays.toString(data));
        System.out.println(findNoSum(data));
    }

    //Generate an array of size with random integers between 0 and 999
    private static int[] generateArray(int size) {
        int[] arr = new int[size];
        java.util.Random rand = new java.util.Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000); // random integers between 0 and 999
        }
        return arr;
    }

    //Returns a number that cannot be formed from any two integers in the array
    public static int findNoSum(int[] data) {
        int max = 0;

        for (int i : data) { // Find the maximum of the array.
            max = Math.max(max, i);
        }
        return 2 * max + 1; //No two values of the array can be summed to be larger than 2 times its max
    }


}
