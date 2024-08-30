package assign02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book{
    private int patron;
    private GregorianCalendar dueDate;
    public LibraryBook(long isbn, String authorSurname, String authorOtherName, String title){
        super(isbn, authorSurname, authorOtherName, title);
        this.patron = -1;
    }
    public int getPatron(){
        return 0;
    }

    public GregorianCalendar getDueDate(){
        return dueDate;
    }

    public void checkOut(){
        this.patron = 1;
        dueDate = new GregorianCalendar();
    }
    public void checkIn(){
        this.patron = -1;
        dueDate = null;
    }
}
