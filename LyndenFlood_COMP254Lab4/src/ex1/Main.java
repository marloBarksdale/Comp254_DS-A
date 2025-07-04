package ex1;

public class Main{

    public static void main(String[] args) {

        // Create a new positional list to store book titles
        PositionalList<String> bookList = new LinkedPositionalList<>();

        // Add books to the list
        bookList.addLast("1984");
        bookList.addLast("Brave New World");
        bookList.addLast("Fahrenheit 451");
        bookList.addLast("The Handmaid's Tale");
        bookList.addLast("The Catcher in the Rye");

        // Print the current list of books
        System.out.println("Book List: " + bookList);

        // Test 1: Search for an existing element
        String search1 = "Fahrenheit 451";
        Position<String> pos1 = findPosition(bookList, search1);
        if (pos1 != null) {
            System.out.println("Found '" + search1 + "' at position: " + pos1.getElement());
        } else {
            System.out.println(search1 + "' not found in list.");
        }

        // Test 2: Search for a non-existing element
        String search2 = "Moby Dick";
        Position<String> pos2 = findPosition(bookList, search2);
        if (pos2 != null) {
            System.out.println("Unexpectedly found '" + search2 + "'.");
        } else {
            System.out.println("Correctly did not find '" + search2 + "'.");
        }

        // Test 3: Search for null (optional edge case)
        Position<String> pos3 = findPosition(bookList, null);
        if (pos3 != null) {
            System.out.println("Unexpectedly found null.");
        } else {
            System.out.println("Correctly did not find null.");
        }
    }

    /**
     * Searches for the first position in the list containing the given element.
     * @param list the positional list to search
     * @param element the element to find
     * @return the Position containing the element, or null if not found
     */
    public static <E> Position<E> findPosition(PositionalList<E> list, E element) {
        for (Position<E> p : list.positions()) {
            // Use equals to compare elements
            if (p.getElement().equals(element)) {
                return p;
            }
        }
        return null; // Element not found
    }
}