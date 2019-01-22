package com.mitrais.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.librarymanagement.business.LibraryService;
import com.mitrais.librarymanagement.model.Shelf;


@RestController
public class LibraryController {

	private LibraryService libraryService;

	@Autowired
	public LibraryController(LibraryService libraryService) {
		this.libraryService = libraryService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("get-all-shelves")
	public List<Shelf> getAllShelves() {
		return libraryService.retrieveAllShelf();
	}
	
	/*
	 * get shelf by shelf id
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("get-shelf")
	public Shelf getShelfById(@RequestParam("id") int id) {
		return libraryService.retrieveShelfById(id);
	}
	
	/*
	 * add book int shelf by book id and shelf id
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("add-book")
	public Shelf addBookToShelf(@RequestParam("bookid") int bookId, @RequestParam("shelfid") int shelfId) {
		return libraryService.addBookToShelf(bookId, shelfId);		
	}
	
	/*
	 * remove book from shelf by book id and shelf id
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("remove-book")
	public Shelf removeBookFromShelf(@RequestParam("bookid") int bookId, @RequestParam("shelfid") int shelfId) {
		return libraryService.removeBookFromShelf(bookId, shelfId);		
	}
	
}
