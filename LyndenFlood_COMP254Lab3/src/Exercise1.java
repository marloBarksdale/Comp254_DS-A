import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");

        int userInput = scanner.nextInt();


        printStars(0, userInput);

        scanner.close();
    }



    public static void printStars(int x, int n){

        if(n==x)
            return;

        if(x>0)
            print(x); // print the top half


        printStars(++x, n );// recursive step. move x closer to n

        print(x);// print bottom half


    }



    public static void print(int n){



        for(int i=0; i <n; i++ ){


            System.out.print("*");
        }


        System.out.println();
    }
}
