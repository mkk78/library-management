package com.mitrais.librarymanagement.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
/*
 * This class represents shelf information that books can be added
 */
@Entity
public class Shelf {

	@Id
	private int shelfId;
	/*
	 * maximum capacity of books
	 */
	private int maxCapacity;
	/*
	 * current capacity of books
	 */
	private int currentCapacity;
	/*
	 * collection of books that added into shelf
	 */
	@ElementCollection
	private List<Book> books;

	public Shelf() {
		
	}

	public Shelf(int shelfId, int maxCapacity, int currentCapacity, List<Book> books) {
		super();
		this.shelfId = shelfId;
		this.maxCapacity = maxCapacity;
		this.currentCapacity = currentCapacity;
		this.books = books;
	}

	public int getShelfId() {
		return shelfId;
	}

	public void setShelfId(int shelfId) {
		this.shelfId = shelfId;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public int getCurrentCapacity() {
		return currentCapacity;
	}

	public void setCurrentCapacity(int currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
	
}
