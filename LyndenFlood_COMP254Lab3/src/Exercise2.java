import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Exercise2 {



    static Set<Character> vowels = Set.of('a','e','i','o','u');


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        System.out.println("Vowel count: " + countVowels(input, 0));
    }


    public static int countVowels(String s, int i){

        if(i==s.length())
            return 0;
        char c = Character.toLowerCase(s.charAt(i));

        return (vowels.contains(c)?1:0) + countVowels(s, i + 1);


    }



//
//    public static boolean isVowel(char c){
//
//        return vowels.contains(Character.toLowerCase(c));
//
//    }

}
