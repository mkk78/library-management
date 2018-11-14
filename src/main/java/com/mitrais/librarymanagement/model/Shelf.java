package com.mitrais.librarymanagement.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shelf {

	@Id
	private int shelf_id;
	private int max_capacity;
	private int current_capacity;
	@ElementCollection
	private List<Book> books;

	public Shelf() {
		
	}
	
	
	public Shelf(int shelf_id, int max_capacity, int current_capacity, List<Book> books) {
		super();
		this.shelf_id = shelf_id;
		this.max_capacity = max_capacity;
		this.current_capacity = current_capacity;
		this.books = books;
	}

	public int getShelf_id() {
		return shelf_id;
	}

	public void setShelf_id(int shelf_id) {
		this.shelf_id = shelf_id;
	}

	public int getMax_capacity() {
		return max_capacity;
	}

	public void setMax_capacity(int max_capacity) {
		this.max_capacity = max_capacity;
	}

	public int getCurrent_capacity() {
		return current_capacity;
	}

	public void setCurrent_capacity(int current_capacity) {
		this.current_capacity = current_capacity;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
