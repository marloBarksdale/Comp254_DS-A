//301413453 Lynden Flood
public class Main {


    public static void main(String[] a) {


        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> doublyLinkedList2 = new DoublyLinkedList<>();


        doublyLinkedList.addLast(1);
        doublyLinkedList.addLast(2);
        doublyLinkedList.addLast(3);
        doublyLinkedList.addLast(4);
        doublyLinkedList.addLast(5);
        doublyLinkedList.addLast(6);


        doublyLinkedList2.addLast(7);
        doublyLinkedList2.addLast(8);
        doublyLinkedList2.addLast(9);
        doublyLinkedList2.addLast(10);
        doublyLinkedList2.addLast(11);


        System.out.println(doublyLinkedList);
        System.out.println(doublyLinkedList2);


        System.out.println(doublyLinkedList.concatList(doublyLinkedList2));

        System.out.println(doublyLinkedList);
        System.out.println(doublyLinkedList2);



    }
}
