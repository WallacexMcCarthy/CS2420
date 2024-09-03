package assign02;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * This class contains some example tests for Library.
 * Students are expected to add additional tests to this class to thoroughly 
 * check the correctness of Library.
 * 
 * @author Erin Parker and Isaac Buehner and Wallace McCarthy
 * @version August 20, 2024
 */
public class LibraryTest {

	private Library emptyLibrary, tinyLibrary, smallLibrary;
	
	@BeforeEach
	void setUp() throws Exception {
		emptyLibrary = new Library();
		
		tinyLibrary = new Library();
		tinyLibrary.add(9780374292799L, "Friedman", "Thomas L.", "The World is Flat");
		tinyLibrary.add(9780330351690L, "Krakauer", "Jon", "Into the Wild");
		tinyLibrary.add(9780446580342L, "Baldacci", "David", "Simple Genius");

		smallLibrary = new Library();
		smallLibrary.addAll("Mushroom_Publishing.txt");
	}

	@Test
	public void testEmptyLookupISBN() {
		assertEquals(-1, emptyLibrary.lookup(978037429279L));
	}
	
	@Test
	public void testEmptyLookupPatron() {
		ArrayList<LibraryBook> booksCheckedOut = emptyLibrary.lookup(123);
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());
	}
	
	@Test
	public void testEmptyCheckOut() {
		assertFalse(emptyLibrary.checkOut(978037429279L, 123, 10, 1, 2024));
	}

	@Test
	public void testEmptyCheckInISBN() {
		assertFalse(emptyLibrary.checkIn(978037429279L));
	}
	
	@Test
	public void testEmptyCheckInPatron() {
		assertFalse(emptyLibrary.checkIn(123));
	}

	@Test
	public void testTinyLibraryLookupISBN() {
		assertEquals(-1, tinyLibrary.lookup(9780330351690L));
	}
	
	@Test
	public void testTinyLibraryLookupPatron() {
		tinyLibrary.checkOut(9780330351690L, 123, 10, 1, 2024);
		ArrayList<LibraryBook> booksCheckedOut = tinyLibrary.lookup(123);
		
		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9780330351690L, "Krakauer", "Jon", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals(123, booksCheckedOut.get(0).getPatron());
	}

	@Test
	public void testTinyLibraryCheckOut() {
		assertTrue(tinyLibrary.checkOut(9780330351690L, 123, 10, 1, 2024));
	}

	@Test
	public void testTinyLibraryCheckInISBN() {
		tinyLibrary.checkOut(9780330351690L, 123, 10, 1, 2024);
		assertTrue(tinyLibrary.checkIn(9780330351690L));
	}

	@Test
	public void testTinyLibraryCheckInPatron() {
		assertFalse(tinyLibrary.checkIn(123));
	}

	@Test
	public void addAllByArrayList() {
		ArrayList<LibraryBook> books = new ArrayList<>();
		books.add(new LibraryBook(9780330351690L, "clark", "kent", "superman"));
		books.add(new LibraryBook(9780330327690L, "bruce", "wayne", "batman"));

		Library library = new Library();
		library.addAll(books);
		assertEquals(-1, library.lookup(9780330351690L));
	}

	@Test
	public void testSmallLibraryLookupISBN() {
		assertEquals(-1, tinyLibrary.lookup(9781843190004L));
	}

	@Test
	public void testSmallLibraryLookupPatron() {
		smallLibrary.checkOut(9781843190004L, 123, 10, 1, 2024);
		ArrayList<LibraryBook> booksCheckedOut = smallLibrary.lookup(123);

		assertNotNull(booksCheckedOut);
		assertEquals(1, booksCheckedOut.size());
		assertEquals(new Book(9781843190004L, "Caldecott", "Moyra", "Weapons of the Wolfhound"), booksCheckedOut.get(0));
		assertEquals(123, booksCheckedOut.get(0).getPatron());
	}

	@Test
	public void testSmallLibraryCheckOut() {
		assertTrue(smallLibrary.checkOut(9781843190004L, 123, 10, 1, 2024));
	}

	@Test
	public void testSmallLibraryCheckInISBN() {
		smallLibrary.checkOut(9781843190004L, 123, 10, 1, 2024);
		assertTrue(smallLibrary.checkIn(9781843190004L));
	}

	@Test
	public void testSmallLibraryCheckInPatron() {
		assertFalse(smallLibrary.checkIn(123));
	}
}