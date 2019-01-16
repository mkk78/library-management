package com.mitrais.librarymanagement.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mitrais.librarymanagement.model.Book;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	@Autowired
	private BookRepository bookRepo;
	
	@Test
	public void testFindBookByStatus() {
		List<Book> books = bookRepo.findByStatus(false);
		assertEquals(2, books.size());
		books.stream().forEach(s -> assertFalse(s.isStatus()));
		
		books = bookRepo.findByStatus(true);
		assertEquals(1, books.size());
		books.stream().forEach(s -> assertTrue(s.isStatus()));
	}
	
	@Test
	public void testFindBookByStatusTitle() {
		List<Book> books = bookRepo.findByStatusTitle(false, "Harry Potter");
		books.stream().forEach(s -> assertFalse(s.isStatus()));
		books.stream().forEach(s-> assertEquals("Harry Potter", s.getTitle()));
	}
}
