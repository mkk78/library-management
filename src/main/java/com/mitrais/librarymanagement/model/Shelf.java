package com.mitrais.librarymanagement.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shelf {

	@Id
	private int shelfId;
	private int maxCapacity;
	private int currentCapacity;
	@ElementCollection
	private List<Book> books;

	public Shelf() {
		
	}
	
	
	public Shelf(int shelf_id, int max_capacity, int current_capacity, List<Book> books) {
		super();
		this.shelfId = shelf_id;
		this.maxCapacity = max_capacity;
		this.currentCapacity = current_capacity;
		this.books = books;
	}

	public int getShelf_id() {
		return shelfId;
	}

	public void setShelf_id(int shelf_id) {
		this.shelfId = shelf_id;
	}

	public int getMax_capacity() {
		return maxCapacity;
	}

	public void setMax_capacity(int max_capacity) {
		this.maxCapacity = max_capacity;
	}

	public int getCurrent_capacity() {
		return currentCapacity;
	}

	public void setCurrent_capacity(int current_capacity) {
		this.currentCapacity = current_capacity;
	}


	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
