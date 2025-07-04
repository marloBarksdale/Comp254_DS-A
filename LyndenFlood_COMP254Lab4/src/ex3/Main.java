package ex3;

public class Main {
    public static void main(String[] args) {


        // Create two singly linked lists of strings
        SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>();

        // Add elements to list1
        list1.addLast("Alpha");
        list1.addLast("Beta");

        // Add elements to list2
        list2.addLast("Gamma");
        list2.addLast("Delta");

        // Print the contents before joining
        System.out.println("Before join:");
        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);

        // Join list2 into list1 (should be O(1))
        list1.join(list2);

        // Print the contents after joining
        System.out.println("\nAfter join:");
        System.out.println("List 1: " + list1);  // Should contain Alpha, Beta, Gamma, Delta
        System.out.println("List 2: " + list2);  // Should be empty

        // Validate final size and emptiness
        System.out.println("\nValidation:");
        System.out.println("List 1 size: " + list1.size()); // Should be 4
        System.out.println("List 2 is empty: " + list2.isEmpty()); // Should be true
    }
}
