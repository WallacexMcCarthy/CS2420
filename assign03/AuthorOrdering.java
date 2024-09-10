package assign03;
import assign02.LibraryBookGeneric;

import java.util.Comparator;

/**
 * This class is a Comparator for the LibraryBookGeneric Class
 * It sorts the books by the author's surname lexicographically
 * If authors have the same surname, the authors first names are compared
 * If authors has the same first name as well, the books titles are compared
 * If all cases are equal, then books are assumed to be equal
 *
 * @author Isaac Buehner and Wallace McCarthy
 * @version September 2, 2024
 */
public class AuthorOrdering<Type> implements Comparator<assign03.LibraryBookGeneric<Type>> {

    /**
     * Compares 2 books to determine which should be sorted higher by the authors names
     *      If authors have the same surname, the authors first names are compared
     *      If authors has the same first name as well, the books titles are compared
     *      If all cases are equal, then books are assumed to be equal
     *
     * @param firstBook the first object to be compared.
     * @param secondBook the second object to be compared.
     * @return an int obtained through the compareTo method for strings
     */
    @Override
    public int compare(assign03.LibraryBookGeneric<Type> firstBook, assign03.LibraryBookGeneric<Type> secondBook) {
        if(firstBook.getAuthorSurname().compareTo(secondBook.getAuthorSurname()) != 0) {
            return firstBook.getAuthorSurname().compareTo(secondBook.getAuthorSurname());
        }
        else {
            if(firstBook.getAuthorOtherName().compareTo(secondBook.getAuthorOtherName()) != 0) {
                return firstBook.getAuthorOtherName().compareTo(secondBook.getAuthorOtherName());
            }
            else {
                return firstBook.getTitle().compareTo(secondBook.getTitle());
            }
        }
    }
}
