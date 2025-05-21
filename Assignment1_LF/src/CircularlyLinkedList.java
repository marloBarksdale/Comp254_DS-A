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


/**
 * An implementation of a circularly linked list.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class CircularlyLinkedList<E> {
    //---------------- nested Node class ----------------

    /**
     * Singly linked node, which stores a reference to its element and
     * to the subsequent node in the list.
     */
    private static class Node<E> {

        /**
         * The element stored at this node
         */
        private E element;     // an element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;  // a reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------

    // instance variables of the CircularlyLinkedList
    /**
     * The designated cursor of the list
     */
    private Node<E> tail = null;                  // we store tail (but not head)

    /**
     * Number of nodes in the list
     */
    private int size = 0;                         // number of nodes in the list

    /**
     * Constructs an initially empty list.
     */
    public CircularlyLinkedList() {
    }             // constructs an initially empty list

    // access methods

    /**
     * Returns the number of elements in the linked list.
     *
     * @return number of elements in the linked list
     */
    public int size() {
        return size;
    }

    /**
     * Tests whether the linked list is empty.
     *
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns (but does not remove) the first element of the list
     *
     * @return element at the front of the list (or null if empty)
     */
    public E first() {             // returns (but does not remove) the first element
        if (isEmpty()) return null;
        return tail.getNext().getElement();         // the head is *after* the tail
    }

    /**
     * Returns (but does not remove) the last element of the list
     *
     * @return element at the back of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
        if (isEmpty()) return null;
        return tail.getElement();
    }







    //What a waste of time!!!
    public boolean sameSequence(CircularlyLinkedList<E> list2) {

        if (this.size != list2.size) return false;

        return compareAlignments(this.tail.getNext(), list2.tail.getNext(), 0);

    }


    private boolean compareAlignments(Node<E> node1, Node<E> node2, int n) {

        if (n == size) return false;

        if (sameNode(node1, node2, 0)) {
            return true;
        } else {
            return compareAlignments(node1, node2.getNext(), ++n);
        }

    }


    private boolean sameNode(Node<E> node1, Node<E> node2, int n) {

        if (n == size) {
            return true;
        }


        if (!node1.getElement().equals(node2.getElement())) {
            return false;
        }

        return sameNode(node1.getNext(), node2.getNext(), ++n);

    }


    // update methods

    /**
     * Rotate the first element to the back of the list.
     */
    public void rotate() {         // rotate the first element to the back of the list
        if (tail != null)                // if empty, do nothing
            tail = tail.getNext();         // the old head becomes the new tail
    }

    /**
     * Adds an element to the front of the list.
     *
     * @param e the new element to add
     */
    public void addFirst(E e) {                // adds element e to the front of the list
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);                     // link to itself circularly
        } else {
            Node<E> newest = new Node<>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param e the new element to add
     */
    public void addLast(E e) { // adds element e to the end of the list
        addFirst(e);             // insert new element at front of list
        tail = tail.getNext();   // now new element becomes the tail
    }

    /**
     * Removes and returns the first element of the list.
     *
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {                   // removes and returns the first element
        if (isEmpty()) return null;              // nothing to remove
        Node<E> head = tail.getNext();
        if (head == tail) tail = null;           // must be the only node left
        else tail.setNext(head.getNext());       // removes "head" from the list
        size--;
        return head.getElement();
    }

    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
        if (tail == null) return "()";
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = tail;
        do {
            walk = walk.getNext();
            sb.append(walk.getElement());
            if (walk != tail)
                sb.append(", ");
        } while (walk != tail);
        sb.append(")");
        return sb.toString();
    }

    //main method
    public static void main(String[] args) {

        //(LAX, MSP, ATL, BOS)
        CircularlyLinkedList<Integer> circularList = new CircularlyLinkedList<>();
        CircularlyLinkedList<Integer> circularList2 = new CircularlyLinkedList<>();

        circularList.addLast(4);
        circularList.addLast(1);
        circularList.addLast(2);
        circularList.addLast(3);
        circularList.addLast(4);
        circularList.addLast(5);
        circularList.addLast(1);
        circularList.addLast(2);
        circularList.addLast(3);


        circularList2.addLast(1);
        circularList2.addLast(2);
        circularList2.addLast(3);
        circularList2.addLast(4);
        circularList2.addLast(1);
        circularList2.addLast(2);
        circularList2.addLast(3);
        circularList2.addLast(4);
        circularList2.addLast(5);


        System.out.println(circularList);
        System.out.println(circularList2);

        System.out.println(circularList.sameSequence(circularList2));

        System.out.println("=== Test 1: Matching rotation ===");
        CircularlyLinkedList<Integer> list1 = new CircularlyLinkedList<>();
        CircularlyLinkedList<Integer> list2 = new CircularlyLinkedList<>();

        // list1: 10 → 20 → 30 → 40
        list1.addLast(10);
        list1.addLast(20);
        list1.addLast(30);
        list1.addLast(40);

        // list2: 30 → 40 → 10 → 20 (rotation of list1)
        list2.addLast(30);
        list2.addLast(40);
        list2.addLast(10);
        list2.addLast(20);

        System.out.println("List 1: " + list1);
        System.out.println("List 2: " + list2);
        System.out.println("Same sequence? " + list1.sameSequence(list2));  // Expected: true


        System.out.println("\n=== Test 2: Same elements, different order ===");
        CircularlyLinkedList<Integer> list3 = new CircularlyLinkedList<>();
        CircularlyLinkedList<Integer> list4 = new CircularlyLinkedList<>();

        // list3: 1 → 2 → 3
        list3.addLast(1);
        list3.addLast(2);
        list3.addLast(3);

        // list4: 2 → 1 → 3 (not a rotation of list3)
        list4.addLast(2);
        list4.addLast(1);
        list4.addLast(3);

        System.out.println("List 3: " + list3);
        System.out.println("List 4: " + list4);
        System.out.println("Same sequence? " + list3.sameSequence(list4));  // Expected: false


        System.out.println("\n=== Test 3: Different sizes ===");
        CircularlyLinkedList<Integer> list5 = new CircularlyLinkedList<>();
        CircularlyLinkedList<Integer> list6 = new CircularlyLinkedList<>();

        // list5: 7 → 8 → 9
        list5.addLast(7);
        list5.addLast(8);
        list5.addLast(9);

        // list6: 7 → 8
        list6.addLast(7);
        list6.addLast(8);

        System.out.println("List 5: " + list5);
        System.out.println("List 6: " + list6);
        System.out.println("Same sequence? " + list5.sameSequence(list6));  // Expected: false

        //
    }
}
