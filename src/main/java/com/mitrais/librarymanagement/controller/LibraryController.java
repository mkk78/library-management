package com.mitrais.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.librarymanagement.business.LibraryService;
import com.mitrais.librarymanagement.model.Shelf;


@RestController
public class LibraryController {

	@Autowired
	private LibraryService libraryService;
	
	@GetMapping("get-shelf")
	public Shelf getShelfById(@RequestParam("id") int id) {
		return libraryService.retrieveShelfById(id);
	}
	
	@GetMapping("add-book")
	public Shelf addBookToShelf(@RequestParam("bookid") int bookId, @RequestParam("shelfid") int shelfId) {
		return libraryService.addBookToShelf(bookId, shelfId);		
	}
	
	@GetMapping("remove-book")
	public Shelf removeBookFromShelf(@RequestParam("bookid") int bookId, @RequestParam("shelfid") int shelfId) {
		return libraryService.removeBookFromShelf(bookId, shelfId);		
	}
	
}
