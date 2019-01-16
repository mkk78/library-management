package com.mitrais.librarymanagement.business;

import java.util.List;

import com.mitrais.librarymanagement.model.Book;
import com.mitrais.librarymanagement.model.Shelf;

public interface ILibraryService {
	public List<Book> retrieveAllBook();
	
	public List<Shelf> retrieveAllShelf();
	
	public List<Book> retrieveBookByStatus(boolean status);
	
	public List<Book> retrieveBookByStatusTitle(boolean status, String title);
	
	public Shelf retrieveShelfById(int id);
	
	public Shelf addBookToShelf(int b, int s);
	
	public Shelf removeBookFromShelf(int b, int s);
}
