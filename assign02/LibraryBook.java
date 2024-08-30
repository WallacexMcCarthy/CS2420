package assign02;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LibraryBook extends Book{
    private int patron;
    private GregorianCalendar dueDate;
    public LibraryBook(long isbn, String authorSurname, String authorOtherName, String title){
        super(isbn, authorSurname, authorOtherName, title);
        this.patron = -1;
    }
    public int getPatron(){
        return this.patron;
    }

    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    public void checkOut(int patron, int year, int month, int day){
        this.patron = patron;
        dueDate = new GregorianCalendar(year, month, day);
    }
    public void checkIn(){
        this.patron = -1;
        dueDate = null;
    }
}
