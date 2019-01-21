package com.mitrais.librarymanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;
/*
 * This class is represents book information
 */
@Entity
public class Book {
	@Id
	private int id;
	private String isbn;
	private String title;
	private String author;
	/*
	 * used to indicate if book already in the shelf or not
	 */
	private boolean status;
	
	public Book() {
		
	}
	
	public Book(int id, String isbn, String title, String author, boolean status) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
