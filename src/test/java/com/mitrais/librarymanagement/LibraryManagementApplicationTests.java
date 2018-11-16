package com.mitrais.librarymanagement;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibraryManagementApplicationTests {

	@Autowired TestRestTemplate restTemplate;
	
	@Test
	public void getBooksStatus() throws JSONException {
		String response = restTemplate.getForObject("/get-book-status?status=false", String.class);
		
		JSONAssert.assertEquals("[{}]", response, false);
	}
	
	@Test
	public void getBooksStatusTitle() throws JSONException {
		String response = restTemplate.getForObject("/get-book-status-title?status=true&title=Naruto", String.class);
		
		JSONAssert.assertEquals("[{}]", response, false);
	}
	
	@Test
	public void getShelfId() throws JSONException {
		String response = restTemplate.getForObject("/get-shelf?id=1", String.class);
		
		JSONAssert.assertEquals("{shelf_id=1}", response, false);
	}
	
	@Test
	public void addBook() throws JSONException {
		String response = restTemplate.getForObject("/add-book?bookid=11&shelfid=1", String.class);
		
		JSONAssert.assertEquals("{shelf_id=1}", response, false);
	}
	
	@Test
	public void removeBook() throws JSONException {
		String response = restTemplate.getForObject("/add-book?bookid=11&shelfid=1", String.class);
		
		JSONAssert.assertEquals("{shelf_id=1}", response, false);
	}
}
