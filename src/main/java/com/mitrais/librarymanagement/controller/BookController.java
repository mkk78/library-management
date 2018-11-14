package com.mitrais.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.librarymanagement.business.LibraryService;
import com.mitrais.librarymanagement.model.Book;

@RestController
public class BookController {
	
	@Autowired
	private LibraryService libraryService;
	
	@GetMapping("/dummy-book")
    public Book dummyBook(){
        return new Book(1, "aaaaa", "harry Potter", "J.K. Rowling", false);
    }
	
	@GetMapping("/get-all-book")
	public List<Book>  allBook() {
		return libraryService.retrieveAllBook();
	}
	
	@GetMapping("/get-book-status")
	public List<Book> bookByStatus(@RequestParam("status") boolean status) {
		return libraryService.retrieveBookByStatus(status);
	}
	
	@GetMapping("/get-book-status-title")
	public List<Book> bookByStatusTitle(@RequestParam("status") boolean status, @RequestParam("title") String title) {
		return libraryService.retrieveBookByStatusTitle(status, title);
	}
}