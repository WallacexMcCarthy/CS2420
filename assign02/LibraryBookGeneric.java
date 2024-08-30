package assign02;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{
    private Type patron;
    private GregorianCalendar dueDate;
    public LibraryBookGeneric(long isbn, String authorSurname, String authorOtherName, String title){
        super(isbn, authorSurname, authorOtherName, title);
        this.patron = null;
    }
    public Type getPatron(){
        return this.patron;
    }

    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    public void checkOut(Type patron, int year, int month, int day){
        this.patron = patron;
        dueDate = new GregorianCalendar(year, month, day);
    }
    public void checkIn(){
        this.patron = null;
        dueDate = null;
    }
}
