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
public class LibraryService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private ShelfRepository shelfRepo;
	
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
	
	public Shelf addBookToShelf(int b, int s) {
		Book book = bookRepo.getOne(b);
		Shelf shelf = shelfRepo.getOne(s);
		
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
	
	public Shelf removeBookFromShelf(int b, int s) {
		Book book = bookRepo.getOne(b);
		Shelf shelf = shelfRepo.getOne(s);

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
