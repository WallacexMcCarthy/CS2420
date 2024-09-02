package assign02;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains some example tests for LibraryGeneric.
 * Students are expected to add additional tests to this class to thoroughly 
 * check the correctness of LibraryGeneric.
 * 
 * @author Erin Parker
 * @version August 20, 2024
 */
public class LibraryGenericTest {
	
	// library that uses names to identify patrons
	private LibraryGeneric<String> patronByNameLibrary; 
	// library that uses phone numbers to identify patrons	
	private LibraryGeneric<PhoneNumber> patronByPhoneLibrary;
	
	@BeforeEach
	void setUp() throws Exception {
		patronByNameLibrary = new LibraryGeneric<String>();
		patronByNameLibrary.add(9780374292799L, "Friedman", "Thomas L.", "The World is Flat");
		patronByNameLibrary.add(9780330351690L, "Krakauer", "Jon", "Into the Wild");
		patronByNameLibrary.add(9780446580342L, "Baldacci", "David", "Simple Genius");
		patronByNameLibrary.add(9780446580342L, "Baldacci", "David", "Simple Genius");
		patronByNameLibrary.add(9780446580342L, "Baldacci", "David", "Complex Genius");
		patronByNameLibrary.add(9780446580342L, "Baldacci", "Dave", "Complex Genius");

		patronByPhoneLibrary = new LibraryGeneric<PhoneNumber>();
		patronByPhoneLibrary.add(9780374292799L, "Friedman", "Thomas L.", "The World is Flat");
		patronByPhoneLibrary.add(9780330351690L, "Krakauer", "Jon", "Into the Wild");
		patronByPhoneLibrary.add(9780446580342L, "Baldacci", "David", "Simple Genius");
		patronByPhoneLibrary.add(9780330351690L, "Krakauer", "James", "Into the Normal");
	}

	@Test
	public void testLookUpByISBN(){
		String patron = "patron";
		assertTrue(patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024));
		assertTrue(patronByNameLibrary.checkOut(9780374292799L, patron + "2", 10, 2, 2024));
		assertEquals("patron", patronByNameLibrary.lookup(9780330351690L));
		assertEquals("patron2", patronByNameLibrary.lookup(9780374292799L));
		assertEquals(null, patronByNameLibrary.lookup(111));
		assertEquals(null, patronByNameLibrary.lookup(9780446580342L));
	}

	@Test
	public void testLookUpByPatron(){
		String patron = "patron";
		assertTrue(patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024));
		assertTrue(patronByNameLibrary.checkOut(9780374292799L, patron + "2", 10, 2, 2024));
		assertTrue(patronByNameLibrary.checkOut(9780446580342L, patron + "3", 10, 2, 2024));
		ArrayList<LibraryBookGeneric<String>> arr = new ArrayList<>();
		arr.add(new LibraryBookGeneric<String>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));
		arr.add(new LibraryBookGeneric<String>(9780374292799L, "Friedman", "Thomas L.", "The World is Flat"));
		arr.add(new LibraryBookGeneric<String>(9780446580342L, "Baldacci", "David", "Simple Genius"));
		assertEquals(arr.get(0), patronByNameLibrary.lookup(patron).get(0));
		assertEquals(arr.get(1), patronByNameLibrary.lookup(patron + "2").get(0));
		assertEquals(arr.get(2), patronByNameLibrary.lookup(patron + "3").get(0));
		patronByNameLibrary.checkIn(9780446580342L);
		assertEquals(new ArrayList<LibraryBookGeneric<String>>(), patronByNameLibrary.lookup(patron + "3"));
	}

	
	@Test
	public void testNameCheckOut() {
		String patron = "Unique Patron Name";
		assertTrue(patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024));
		assertTrue(patronByNameLibrary.checkOut(9780374292799L, patron, 10, 1, 2024));
	}

	@Test
	public void testNameLookup() {
		String patron = "Unique Patron Name";
		patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByNameLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut = patronByNameLibrary.lookup(new String("Unique Patron Name"));
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Krakauer", "Jon", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Friedman", "Thomas L.", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getPatron());
		assertEquals(patron, booksCheckedOut.get(1).getPatron());
	}
	
	@Test
	public void testNameCheckIn() {
		String patron = "Unique Patron Name";
		patronByNameLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByNameLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		assertTrue(patronByNameLibrary.checkIn(new String("Unique Patron Name")));
	}

	@Test
	public void testPhoneCheckOut() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		assertTrue(patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024));
		assertTrue(patronByPhoneLibrary.checkOut(9780374292799L, patron, 10, 1, 2024));
	}

	@Test
	public void testPhoneLookup() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByPhoneLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut = patronByPhoneLibrary.lookup(new PhoneNumber("801.555.1234"));
		
		assertNotNull(booksCheckedOut);
		assertEquals(2, booksCheckedOut.size());
		assertTrue(booksCheckedOut.contains(new Book(9780330351690L, "Krakauer", "Jon", "Into the Wild")));
		assertTrue(booksCheckedOut.contains(new Book(9780374292799L, "Friedman", "Thomas L.", "The World is Flat")));
		assertEquals(patron, booksCheckedOut.get(0).getPatron());
		assertEquals(patron, booksCheckedOut.get(1).getPatron());
	}

	@Test
	public void testPhoneCheckIn() {
		PhoneNumber patron = new PhoneNumber("801.555.1234");
		patronByPhoneLibrary.checkOut(9780330351690L, patron, 10, 1, 2024);
		patronByPhoneLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		assertTrue(patronByPhoneLibrary.checkIn(new PhoneNumber("801.555.1234")));
	}

	@Test
	public void getSortedBooksByAuthorName() {
		ArrayList<LibraryBookGeneric<String>> comparing = new ArrayList<>();

		// this set of books checks for edge cases of sorting including:
		// same surname different other name
		// same surname and same other name but different title
		// same surname, other name, and title
		comparing.add(new LibraryBookGeneric<>(9780446580342L, "Baldacci", "Dave", "Complex Genius"));
		comparing.add(new LibraryBookGeneric<>(9780446580342L, "Baldacci", "David", "Complex Genius"));
		comparing.add(new LibraryBookGeneric<>(9780446580342L, "Baldacci", "David", "Simple Genius"));
		comparing.add(new LibraryBookGeneric<>(9780446580342L, "Baldacci", "David", "Simple Genius"));
		comparing.add(new LibraryBookGeneric<>(9780374292799L, "Friedman", "Thomas L.", "The World is Flat"));
		comparing.add(new LibraryBookGeneric<>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));

		for (int i = 0; i < comparing.size(); i++) {
			assertTrue(patronByNameLibrary.getListSortedByAuthor().get(i).equals(comparing.get(i)));
		}
	}

	@Test
	public void getSortedBooksByISBN() {
		ArrayList<LibraryBookGeneric<String>> comparing = new ArrayList<>();

		comparing.add(new LibraryBookGeneric<>(9780330351690L, "Krakauer", "Jon", "Into the Wild"));
		comparing.add(new LibraryBookGeneric<>(9780330351690L, "Krakauer", "James", "Into the Normal"));
		comparing.add(new LibraryBookGeneric<>(9780374292799L, "Friedman", "Thomas L.", "The World is Flat"));
		comparing.add(new LibraryBookGeneric<>(9780446580342L, "Baldacci", "David", "Simple Genius"));

		for (int i = 0; i < comparing.size(); i++) {
			assertTrue(patronByPhoneLibrary.getListSortedByIsbn().get(i).equals(comparing.get(i)));
		}
	}

	@Test
	public void testOverDueList() {
		String patron = "Calbert";

		patronByNameLibrary.checkOut(9780330351690L, patron, 10, 10, 2024);
		patronByNameLibrary.checkOut(9780374292799L, patron, 10, 1, 2024);
		patronByNameLibrary.checkOut(9780446580342L, patron, 10, 20, 2024);

		assertEquals(2, patronByNameLibrary.getOverdueList(10, 5, 2024).size());
		assertEquals(0, patronByNameLibrary.getOverdueList(10, 25, 2024).size());


	}

}