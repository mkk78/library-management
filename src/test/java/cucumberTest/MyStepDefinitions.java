package cucumberTest;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mitrais.librarymanagement.controller.BookController;
import com.mitrais.librarymanagement.model.Book;
import com.mitrais.librarymanagement.model.Shelf;
import com.mitrais.librarymanagement.repository.BookRepository;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;



public class MyStepDefinitions extends CucumberRoot{
	
	private ResponseEntity<String> response;

	@Autowired
	BookController bookController;
	
	@Autowired
	BookRepository bookRepo;
	
	@When("^user is call \"([^\"]*)\" page$")
    public void user_is_call_dummy_book_page(String url) throws Throwable {
		response = template.getForEntity(url, String.class);
	}

	@Then("^user receives response status code of (\\d+)$")
    public void user_receives_status_code_of(int statusCode) throws Throwable {
    	HttpStatus currentStatusCode = response.getStatusCode();
    	assertThat("status code is incorrect : " + response.getBody(), currentStatusCode.value(), Matchers.is(200));
    }

	@And("^user get dummy data$")
    public void user_get_dummy_data() throws Throwable {
    	JSONAssert.assertEquals("{\"id\":1,\"isbn\":\"aaaaa\",\"title\":\"harry Potter\",\"author\":\"J.K. Rowling\",\"status\":false}", response.getBody(), false);
    }
	
	@And("^user get all book$")
    public void user_get_all_book() throws Throwable {
    	JSONArray jsonArray = new JSONArray(response.getBody());
    	assertThat("array length is incorrect : " + response.getBody(), jsonArray.length(), Matchers.is(bookRepo.findAll().size()));
     }
	
	
	@And("^book id (.+) is added in shelf$")
    public void book_is_added_in_shelf_by_id(String bookId) throws Throwable {
        System.out.println(response.getBody());
        JSONObject jsonObject = new JSONObject(response.getBody());
        
        // Parsing shelf's books json into Books list
        List<Book> books = new ArrayList<Book>();
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (int i = 0; i < jsonArray.length(); i++) {
        	JSONObject object = new JSONObject(jsonArray.get(i).toString());
        	books.add(new Book(object.getInt("id"), object.getString("isbn"), object.getString("title"), object.getString("author"), object.getBoolean("status")));
        }
        // Parsing shelf json into Shelf object
        Shelf myShelf = new Shelf(jsonObject.getInt("shelfId"), jsonObject.getInt("maxCapacity"), jsonObject.getInt("currentCapacity"), books);
        
        // Compare last book id from shelf with inputed book id
        if (!myShelf.getBooks().isEmpty()) {
        	assertThat("book is not in shelf: " + response.getBody(), myShelf.getBooks().get(myShelf.getBooks().size()-1).getId(), Matchers.is(Integer.parseInt(bookId)));
        }
    }
	

}
