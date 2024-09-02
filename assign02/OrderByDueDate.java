package assign02;

import java.util.Comparator;

/**
 * This class defines a custom comparison for library books, using the book's due date.
 *
 * @author Isaac Buehner and Wallace McCarthy
 * @version September 2, 2024
 */
public class OrderByDueDate<Type> implements Comparator<LibraryBookGeneric<Type>> {

    /**
     * Compares two library books, using the book's due date.
     *
     * @return positive integer if the first library book's due date is newer,
     *         negative integer if the first library book's due date is older,
     *         0 if the due dates are the same
     */
    public int compare(LibraryBookGeneric<Type> firstBook, LibraryBookGeneric<Type> secondBook) {
        return firstBook.getDueDate().compareTo(secondBook.getDueDate());
    }
}
