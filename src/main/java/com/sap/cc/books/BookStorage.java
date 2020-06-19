package com.sap.cc.books;

import java.util.List;
import java.util.Optional;

public interface BookStorage {

	public Book saveBook(Book book);

	public Optional<Book> retrieveBookById(Long id);

	public List<Book> retrieveAllBooks();

	public void deleteBook(Long id);

	public void deleteAllBooks();

}
