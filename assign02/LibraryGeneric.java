package assign02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.*;

/**
 * This class represents a library, which is a collection of library books.
 *
 * @author CS 2420 course staff and ??
 * @version August 29, 2024
 */
public class LibraryGeneric<Type> {

    private ArrayList<LibraryBookGeneric<Type>> library;

    /**
     * Creates an empty library.
     */
    public LibraryGeneric() {
        this.library = new ArrayList<LibraryBookGeneric<Type>>();
    }

    /**
     * Adds the book, with the given ISBN, author, and title information, to this
     * library. Assumes there is no possibility of duplicate library books.
     *
     * @param isbn - ISBN of the book to be added
     * @param authorSurname - surname of the author of the book to be added
     * @param authorOtherName - other name of the author of the book to be added
     * @param title - title of the book to be added
     */
    public void add(long isbn, String authorSurname, String authorOtherName, String title) {
        this.library.add(new LibraryBookGeneric<Type>(isbn, authorSurname, authorOtherName, title));
    }

    /**
     * Adds a list of library books to the library. Assumes there is no possibility
     * of duplicate library books.
     *
     * @param list - list of library books to be added
     */
    public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
        this.library.addAll(list);
    }

    /**
     * Adds the books specified by an input file to the library. Assumes the input
     * files specifies one book per line with ISBN, author, and title separated by
     * tabs, and the author surname before a comma.
     *
     * If file does not exist or a formatting rule is violated, prints an error
     * message and does not change the library.
     *
     * @param filename - name of the file containing information for the books to be
     *                 added
     */
    public void addAll(String filename) {
        ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

        try (Scanner fileIn = new Scanner(new File(filename))) {
            int lineNum = 1;

            while(fileIn.hasNextLine()) {
                String line = fileIn.nextLine();

                try (Scanner lineIn = new Scanner(line)) {
                    lineIn.useDelimiter("\\t");

                    if(!lineIn.hasNextLong())
                        throw new ParseException("ISBN", lineNum);
                    long isbn = lineIn.nextLong();

                    if(!lineIn.hasNext())
                        throw new ParseException("Author", lineNum);
                    String author = lineIn.next();
                    String[] authorNames = author.split(", ");
                    if(authorNames.length != 2)
                        throw new ParseException("Author", lineNum);

                    if(!lineIn.hasNext())
                        throw new ParseException("Title", lineNum);
                    String title = lineIn.next();

                    toBeAdded.add(new LibraryBookGeneric<Type>(isbn, authorNames[0], authorNames[1], title));

                    lineNum++;
                }
                catch (ParseException e) {
                    System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
                            + ". Nothing added to the library.");
                    return;
                }
            }
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage() + " Nothing added to the library.");
            return;
        }

        this.library.addAll(toBeAdded);
    }

    /**
     * Gets the id of the patron who has the library book with the specified ISBN
     * checked out of the library. If there is no such patron, because no book with
     * the specified ISBN is in the library or the library book is not checked out,
     * returns -1.
     *
     * @param isbn - ISBN of the book to be looked up
     */
    public Type lookup(long isbn) {
        for (int i = 0; i < library.size(); i++) {
            if(library.get(i).getIsbn() == isbn){
                return library.get(i).getPatron();
            }
        }
        return null;
    }

    /**
     * Gets the list of library books checked out to the specified patron. If the
     * patron does not exist or has no books checked out of the library, returns an
     * empty list.
     *
     * @param patron - id of patron whose list of checked out books is being
     *               accessed
     */
    public ArrayList<LibraryBookGeneric<Type>> lookup(Type patron) {
        ArrayList<LibraryBookGeneric<Type>> arr = new ArrayList<>();
        for (int i = 0; i < library.size(); i++) {
            if(library.get(i).getPatron() != null &&library.get(i).getPatron().equals(patron)){
                arr.add(library.get(i));
            }
        }
        return arr;
    }

    /**
     * Checks out of the library the book with the specified ISBN, by setting the
     * patron and due date. If no book with the specified ISBN is in the library,
     * returns false. If the book with the specified ISBN is already checked out,
     * returns false. Otherwise, returns true.
     *
     * @param isbn - ISBN of the library book to be checked out
     * @param patron - id of the patron who checking this book out of the library
     * @param month - month (as number) when this book is due to be returned to
     *                the library
     * @param day - day when this book is due to be returned to the library
     * @param year - year when this book is due to be returned to the library
     */
    public boolean checkOut(long isbn, Type patron, int month, int day, int year) {
        for (int i = 0; i < library.size(); i++) {
            if(library.get(i).getIsbn() == isbn){
                if(library.get(i).getPatron() == null) {
                    library.get(i).checkOut(patron, year, month, day);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gives the book with the specified ISBN back to the library by setting the
     * patron and due date to their default values. (I.e., returns the books, checks
     * the books back into the library)
     *
     * If no book with the specified ISBN is in the library, returns false. If the
     * book with the specified ISBN is not checked out, returns false. Otherwise,
     * returns true.
     *
     * @param isbn - ISBN of the book to be given back to the library
     */
    public boolean checkIn(long isbn) {
        for (int i = 0; i < library.size(); i++) {
            if(library.get(i).getIsbn() == isbn){
                library.get(i).checkIn();
                return true;
            }
        }
        return false;
    }

    /**
     * Gives all book checked out by the specified patron back to the library by
     * setting the patron and due date to their default values. (I.e., returns all
     * books, checks all books back into the library)
     *
     * If no library books are checked out by the patron, returns false; Otherwise,
     * returns true.
     *
     * @param patron - id of the patron returning all books to the library
     */
    public boolean checkIn(Type patron) {
        boolean flag = false;
        for (int i = 0; i < library.size(); i++) {
            if(library.get(i).getPatron() != null && library.get(i).getPatron().equals(patron)){
                library.get(i).checkIn();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * Performs a SELECTION SORT on a given list of library books.
     *
     * 1. Finds the smallest item in the list.
     * 2. Swaps the smallest item with the first item in the list.
     * 3. Reconsiders the list to be the remaining unsorted portion (second item to Nth item) and repeats steps 1, 2, and 3.
     *
     * @param list - list of library books to be sorted
     * @param cmp - Comparator defining how to compare library books
     */
    private static <InnerType> void sort(List<LibraryBookGeneric<InnerType>> list, Comparator<LibraryBookGeneric<InnerType>> cmp) {
        for(int i = 0; i < list.size() - 1; i++) {
            int j, minIndex;
            for(j = i + 1, minIndex = i; j < list.size(); j++)
                if(cmp.compare(list.get(j), list.get(minIndex)) < 0)
                    minIndex = j;
            LibraryBookGeneric<InnerType> temp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

    /**
     * Gets a list of books in this library sorted by ISBN, smallest to largest.
     * If the library is empty, returns an empty list.
     *
     * @return list of all library books sorted by ISBN
     */
    public List<LibraryBookGeneric<Type>> getListSortedByIsbn() {
        List<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
        libraryCopy.addAll(this.library);

        OrderByIsbn<Type> comparator = new OrderByIsbn<Type>();
        sort(libraryCopy, comparator);

        return libraryCopy;
    }

    /**
     * Gets a list of books in this library sorted by author surname, lexicographically.
     * For books with the same author surname, break ties using book titles.
     * If the library is empty, returns an empty list.
     *
     * @return list of all library books sorted by author surname
     */
    public List<LibraryBookGeneric<Type>> getListSortedByAuthor(){
        this.library.sort(new OrderByAuthor<Type>());
        return this.library;
    }

    /**
     * Gets the list of books in this library with a due date older than the input date.
     * The list is sorted by date, oldest to most recent.
     * If no library books are overdue, returns an empty list.
     *
     * @param month - month (as number) for the input date
     * @param day - day for the input date
     * @param year - year for the input date
     * @return list of all library books that are overdue, sorted by due date
     */
    public List<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year){
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        ArrayList<LibraryBookGeneric<Type>> overdueList = new ArrayList<>();
        this.library.sort(new OrderByDueDate<Type>());
        for (int i = library.size() - 1; i > 0; i--) {
            if(library.get(i).getDueDate().compareTo(calendar) > 0) {
                overdueList.add(library.get(i));
            }
            else {break;}
        }
        return overdueList;
    }
}
