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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Matchers.*;


import com.mitrais.librarymanagement.business.LibraryService;
import com.mitrais.librarymanagement.model.Book;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LibraryService libraryService;
	
	@Test
	public void getBookStatus() throws Exception {
		
		when(libraryService.retrieveBookByStatus(anyBoolean())).thenReturn(
				Arrays.asList(new Book(1, "12345", "Harry Potter", "J.K. Rowling", false),
						new Book(2, "4321", "Naruto", "Masashi Kishimoto", false)));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/get-book-status").param("status", "false").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("[{},{}]"));
	}
	
	@Test
	public void getBookStatusTitle() throws Exception {
		when(libraryService.retrieveBookByStatusTitle(anyBoolean(), anyString())).thenReturn(
				Arrays.asList(new Book(1, "12345", "Harry Potter", "J.K. Rowling", false),
						new Book(2, "4321", "Naruto", "Masashi Kishimoto", false)));
		
		RequestBuilder request = MockMvcRequestBuilders.get("/get-book-status-title?status=false&title=1111").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().isOk()).andExpect(content().json("[{id=1},{id=2}]"));
	}
}
