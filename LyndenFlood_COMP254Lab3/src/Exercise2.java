import java.util.Scanner;
import java.util.Set;

public class Exercise2 {

    // Set of lowercase vowels for fast lookup
    static Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) {
        // Create Scanner to read input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Call the recursive method and display the result
        System.out.println("Vowel count: " + countVowels(input, 0));
    }


    public static int countVowels(String s, int i) {
        // Base case: if index reaches the end of the string, return 0
        if (i == s.length())
            return 0;

        // Convert the current character to lowercase for case-insensitive comparison
        char c = Character.toLowerCase(s.charAt(i));

        // If it's a vowel, add 1; otherwise, add 0.
        return (vowels.contains(c) ? 1 : 0) + countVowels(s, i + 1);
    }


//    public static boolean isVowel(char c) {
//        return vowels.contains(Character.toLowerCase(c));
//    }
//
}