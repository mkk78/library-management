package com.mitrais.librarymanagement.business;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.mitrais.librarymanagement.model.Book;
import com.mitrais.librarymanagement.model.Shelf;
import com.mitrais.librarymanagement.repository.BookRepository;
import com.mitrais.librarymanagement.repository.ShelfRepository;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {
	@InjectMocks
	private LibraryService libraryService;
	@Mock
	private BookRepository bookRepo;
	@Mock
	private ShelfRepository shelfRepo;

	@Test
	public void retrieveBookByStatus() {
		when(bookRepo.findByStatus(false)).thenReturn(Arrays.asList(
				new Book(1, "12345", "Harry Potter", "J.K. Rowling", false),
				new Book(2, "4321", "Naruto", "Masashi Kishimoto", false)));

		List<Book> books = libraryService.retrieveBookByStatus(false);
		books.stream().forEach(s -> assertFalse(s.isStatus()));

	}
	
	@Test
	public void retrieveBookByStatusTitle() {
		when(bookRepo.findByStatusTitle(anyBoolean(), anyString())).thenReturn(Arrays.asList(
				new Book(1, "12345", "Harry Potter", "J.K. Rowling", false)));
		
		List<Book> books = libraryService.retrieveBookByStatusTitle(false, "Harry Potter");
		
		books.stream().forEach(s -> assertFalse(s.isStatus()));
		books.stream().forEach(s -> assertEquals("Harry Potter", s.getTitle()));
	}

	@Test
	public void retrieveShelfById() {
		when(shelfRepo.findById(anyInt())).thenReturn(Optional.of(new Shelf(1, 1, 0, null)));
		Shelf shelf = libraryService.retrieveShelfById(1);
		assertEquals(1, shelf.getShelf_id());
	}
	
	@Test
	public void addBookIntoShelf() {
		when(bookRepo.findById(anyInt())).thenReturn(Optional.of(new Book(1, "12345", "Harry Potter", "J.K. Rowling", false)));
		
		List<Book> lBooks = new ArrayList<>();
		lBooks.add(new Book(5, "55555", "One Piece", "Eiichiro Oda", true));
		lBooks.add(new Book(6, "88888", "Naruto", "Masashi Kishimoto", true));
		
		when(shelfRepo.findById(anyInt())).thenReturn(Optional.of(new Shelf(1, 3, 2, lBooks)));
		
		Shelf s = libraryService.addBookToShelf(11, 1);
		
		assertEquals(3, s.getCurrent_capacity());
		assertEquals(3, s.getBooks().size());
		assertEquals(5, s.getBooks().get(0).getId());
		assertEquals(1, s.getBooks().get(s.getBooks().size()-1).getId());
	}
	
	@Test
	public void removeBookFromShelf() {
		
		when(bookRepo.findById(anyInt())).thenReturn(Optional.of(new Book(1, "12345", "Harry Potter", "J.K. Rowling", true)));
		
		List<Book> lBooks = new ArrayList<>();
		lBooks.add(new Book(5, "55555", "One Piece", "Eiichiro Oda", true));
		lBooks.add(new Book(6, "88888", "Naruto", "Masashi Kishimoto", true));
		lBooks.add(new Book(1, "12345", "Harry Potter", "J.K. Rowling", true));
		
		when(shelfRepo.findById(anyInt())).thenReturn(Optional.of(new Shelf(1, 3, 3, lBooks)));
		
		Shelf s = libraryService.removeBookFromShelf(1, 1);
		
		assertEquals(2, s.getCurrent_capacity());
		assertEquals(2, s.getBooks().size());
		
	}
}
