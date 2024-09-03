package assign02;
import java.util.GregorianCalendar;

/**
 * This class represents a library book, in which the ISBN (unique), author,
 * due date, and title information cannot change once the book is created.
 * A library book also contains a status of whether it is checked in or out that can be changed
 * A library book also contains a patron value to represent the patron that has checked the book out
 *
 * @author Isaac Buehner and Wallce McCarthy
 * @version September 2, 2024
 */
public class LibraryBook extends Book{
    private int patron;
    private GregorianCalendar dueDate;

    /**
     *  Creates a library book with the given isbn, author surname and other name, and title
     *
     * @param isbn a unique ID to represent the book
     * @param authorSurname the authors surname or lastname
     * @param authorOtherName the authors first name or other name
     * @param title the title of the book
     */
    public LibraryBook(long isbn, String authorSurname, String authorOtherName, String title){
        super(isbn, authorSurname, authorOtherName, title);
        // patron is -1 since the book has not been checked out
        this.patron = -1;
    }

    /**
     * Returns the patrons ID number
     * @return int representing the patron
     */
    public int getPatron(){
        return this.patron;
    }

    /**
     * Returns the due date assigned to the book after it is checked out
     * @return GregorianCalender object representing the books due date
     */
    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    /**
     * Assigns the book a patron value and a due date to be used in a library system
     * @param patron the patrons ID number
     * @param year the year for the due date
     * @param month the month for the due date
     * @param day the day for the due date
     */
    public void checkOut(int patron, int year, int month, int day){
        this.patron = patron;
        dueDate = new GregorianCalendar(year, month, day);
    }

    /**
     * Reassigns the patron value to -1 and removes the due date
     * to indicate that the book is no longer checked in
     */
    public void checkIn(){
        this.patron = -1;
        dueDate.clear();
    }
}
