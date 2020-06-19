package com.sap.cc.books;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookStorageTest {

	private BookStorage bookStorage = new InMemoryBookStorage();

	private final Book SONG_OF_ICE_AND_FIRE = createBookWithAuthor("George R.R. Martin");
	private final Book HITCHHIKERS_GUIDE_TO_THE_GALAXY = createBookWithAuthor("Douglas Adams");

	@BeforeEach
	public void beforeEach() {
		bookStorage.deleteAllBooks();
	}

	@Test
	public void testRetrieveBookByIdNonExistingBook() {
		Optional<Book> returnedBook = bookStorage.retrieveBookById(1L);
		assertThat(returnedBook.isPresent()).isEqualTo(false);
	}

	@Test
	public void testSaveBook() {
		Book returnedBook = bookStorage.saveBook(SONG_OF_ICE_AND_FIRE);

		assertThat(returnedBook.getAuthor()).isEqualTo(SONG_OF_ICE_AND_FIRE.getAuthor());
		assertThat(returnedBook.getId()).isEqualTo(1L);
	}

	@Test
	public void testSaveTwoBooks() {
		bookStorage.saveBook(SONG_OF_ICE_AND_FIRE);

		Book returnedBook = bookStorage.saveBook(HITCHHIKERS_GUIDE_TO_THE_GALAXY);

		assertThat(returnedBook.getAuthor()).isEqualTo(HITCHHIKERS_GUIDE_TO_THE_GALAXY.getAuthor());
		assertThat(returnedBook.getId()).isEqualTo(2L);
	}

	@Test
	public void testSaveBookTryToForceId() {
		Book book = SONG_OF_ICE_AND_FIRE;
		book.setId(10L);
		Book returnedBook = bookStorage.saveBook(book);

		assertThat(returnedBook.getId()).isEqualTo(1L);
	}

	@Test
	public void testSaveAndRetrieveBookById() {
		bookStorage.saveBook(SONG_OF_ICE_AND_FIRE);

		Optional<Book> returnedBook = bookStorage.retrieveBookById(1L);

		assertThat(returnedBook.isPresent()).isEqualTo(true);
		assertThat(returnedBook.get().getId()).isEqualTo(1L);
		assertThat(returnedBook.get().getAuthor()).isEqualTo(SONG_OF_ICE_AND_FIRE.getAuthor());

	}

	@Test
	public void testUpdateTitleOfExistingBook() {
		Book returnedBook = bookStorage.saveBook(SONG_OF_ICE_AND_FIRE);
		assertThat(returnedBook.getId()).isEqualTo(1L);

		final String newAuthor = "Bruce Schneier";
		returnedBook.setAuthor(newAuthor);

		bookStorage.saveBook(returnedBook);

		assertThat(returnedBook.getAuthor()).isEqualTo(newAuthor);
		assertThat(returnedBook.getId()).isEqualTo(1L);

	}

	@Test
	public void testGetAllEmpty() {
		List<Book> returnedBooks = bookStorage.retrieveAllBooks();
		assertThat(returnedBooks.size()).isEqualTo(0);
	}

	@Test
	public void testGetAllFirstOneThenTwoEntries() {

		bookStorage.saveBook(SONG_OF_ICE_AND_FIRE);

		List<Book> returnedBooks = bookStorage.retrieveAllBooks();
		assertThat(returnedBooks.size()).isEqualTo(1);
		assertThat(returnedBooks.iterator().next().getAuthor()).isEqualTo(SONG_OF_ICE_AND_FIRE.getAuthor());
		assertThat(returnedBooks.iterator().next().getId()).isEqualTo(1L);

		bookStorage.saveBook(HITCHHIKERS_GUIDE_TO_THE_GALAXY);

		returnedBooks = bookStorage.retrieveAllBooks();
		assertThat(returnedBooks.size()).isEqualTo(2);

	}

	@Test
	public void testDeleteSingle() {

		bookStorage.saveBook(SONG_OF_ICE_AND_FIRE);
		bookStorage.saveBook(HITCHHIKERS_GUIDE_TO_THE_GALAXY);

		List<Book> returnedBooks = bookStorage.retrieveAllBooks();
		assertThat(returnedBooks.size()).isEqualTo(2);

		bookStorage.deleteBook(1L);

		returnedBooks = bookStorage.retrieveAllBooks();

		assertThat(returnedBooks.size()).isEqualTo(1);
		assertThat(returnedBooks.iterator().next().getAuthor()).isEqualTo(HITCHHIKERS_GUIDE_TO_THE_GALAXY.getAuthor());
		assertThat(returnedBooks.iterator().next().getId()).isEqualTo(2L);

	}

	@Test
	public void testDeleteAll() {

		bookStorage.saveBook(SONG_OF_ICE_AND_FIRE);
		bookStorage.saveBook(HITCHHIKERS_GUIDE_TO_THE_GALAXY);

		List<Book> returnedBooks = bookStorage.retrieveAllBooks();
		assertThat(returnedBooks.size()).isEqualTo(2);

		bookStorage.deleteAllBooks();

		returnedBooks = bookStorage.retrieveAllBooks();
		assertThat(returnedBooks.size()).isEqualTo(0);
	}

	private Book createBookWithAuthor(String title) {
		Book book = new Book();
		book.setAuthor(title);
		return book;
	}

}
