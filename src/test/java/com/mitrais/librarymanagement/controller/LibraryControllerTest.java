package com.mitrais.librarymanagement.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mitrais.librarymanagement.business.LibraryService;
import com.mitrais.librarymanagement.model.Book;
import com.mitrais.librarymanagement.model.Shelf;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(LibraryController.class)
public class LibraryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LibraryService libraryService;
	
	@Test
	public void getShelfById() throws Exception{
		when(libraryService.retrieveShelfById(anyInt())).thenReturn(new Shelf(1, 3, 0, null));
		
		
		RequestBuilder request = MockMvcRequestBuilders
				 .get("/get-shelf?id=1")
	             .accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{shelf_id=1, current_capacity=0}"));
	}
	
	@Test
	public void addBookIntoShelf() throws Exception {
		
		List<Book> lBooks = new ArrayList<>();
		lBooks.add(new Book(5, "55555", "One Piece", "Eiichiro Oda", true));
		lBooks.add(new Book(6, "88888", "Naruto", "Masashi Kishimoto", true));
		
		when(libraryService.addBookToShelf(anyInt(), anyInt())).thenReturn(new Shelf(1, 5, 2, lBooks));
		
		RequestBuilder request = MockMvcRequestBuilders
				 .get("/add-book?bookid=1&shelfid=1")
	             .accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{shelf_id=1, current_capacity=2}"));
	}
	
	@Test
	public void removeBookFromShelf() throws Exception {
		List<Book> lBooks = new ArrayList<>();
		lBooks.add(new Book(5, "55555", "One Piece", "Eiichiro Oda", true));
		
		when(libraryService.removeBookFromShelf(anyInt(), anyInt())).thenReturn(new Shelf(1, 5, 1, lBooks));
		
		RequestBuilder request = MockMvcRequestBuilders
				 .get("/remove-book?bookid=1&shelfid=1")
	             .accept(MediaType.APPLICATION_JSON);
		
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{shelf_id=1, current_capacity=1}"));
	
	}

}
