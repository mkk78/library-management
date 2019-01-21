package com.mitrais.librarymanagement.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.librarymanagement.model.Book;
import com.mitrais.librarymanagement.model.Shelf;
import com.mitrais.librarymanagement.repository.BookRepository;
import com.mitrais.librarymanagement.repository.ShelfRepository;

@Service
public class LibraryService implements ILibraryService{
	
	private BookRepository bookRepo;
	private ShelfRepository shelfRepo;
	
	@Autowired
	public LibraryService(BookRepository bookRepo, ShelfRepository shelfRepo) {
		this.bookRepo = bookRepo;
		this.shelfRepo = shelfRepo;
	}

	public List<Book> retrieveAllBook() {
		
		return bookRepo.findAll();
	}
	
	public List<Shelf> retrieveAllShelf() {
		return shelfRepo.findAll();
	}
	
	public List<Book> retrieveBookByStatus(boolean status) {
		return bookRepo.findByStatus(status);
	}
	
	public List<Book> retrieveBookByStatusTitle(boolean status, String title) {
		return bookRepo.findByStatusTitle(status, title);
	}
	
	public Shelf retrieveShelfById(int id) {
		return shelfRepo.findById(id).get();
	}
	
	/*
	 * This method add book to shelf
	 * @param b book id
	 * @param s shelf id
	 * find book by book id
	 * find shelf by shelf id
	 * if shelf current capacity is not more than or equals max capacity
	 * then it can be processed
	 * if books in shelf is null then set books new array list
	 * if book is not in shelf then it can be processed
	 * save book and shelf
	 * @return shelf whether added the book or not
	 */
	public Shelf addBookToShelf(int b, int s) {
		Book book = bookRepo.findById(b).get();
		Shelf shelf = shelfRepo.findById(s).get();
		
		if (shelf.getCurrent_capacity() < shelf.getMax_capacity()) {
			if (shelf.getBooks()==null) shelf.setBooks(new ArrayList<>());
			if (!shelf.getBooks().stream().filter(Idb -> Idb.getId() == b).findFirst().isPresent()) {
				
				book.setStatus(true);
				shelf.getBooks().add(book);
				shelf.setCurrent_capacity(shelf.getBooks().size());
				
				bookRepo.save(book);
				shelfRepo.save(shelf);
			}			
		}
		
		return shelf;
	}
	
	/*
	 * This method remove book from shelf
	 * @param b book id
	 * @param s shelf id
	 * find book and shelf by its id
	 * if book is in shelf then it can be processed
	 * save book and shelf
	 * @return shelf whether removed the book or not
	 */
	public Shelf removeBookFromShelf(int b, int s) {
		Book book = bookRepo.findById(b).get();
		Shelf shelf = shelfRepo.findById(s).get();

		if (shelf.getBooks()==null) shelf.setBooks(new ArrayList<>());
		if (shelf.getBooks().stream().filter(Idb -> Idb.getId() == b).findFirst().isPresent()) {
			
			book.setStatus(false);
			shelf.getBooks().removeIf(id -> id.getId() == b);
			shelf.setCurrent_capacity(shelf.getBooks().size());
			
			bookRepo.save(book);
			shelfRepo.save(shelf);
		}
		return shelf;
	}
	

}
