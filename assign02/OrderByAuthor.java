package assign02;

import java.util.Comparator;

public class OrderByAuthor<Type> implements Comparator<LibraryBookGeneric<Type>> {


    @Override
    public int compare(LibraryBookGeneric<Type> firstBook, LibraryBookGeneric<Type> secondBook) {
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
