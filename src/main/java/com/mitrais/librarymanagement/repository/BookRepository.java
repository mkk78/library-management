package com.mitrais.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mitrais.librarymanagement.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	@Query("SELECT t FROM Book t WHERE t.status = ?1")
	public List<Book> findByStatus(boolean status);
	
	@Query("SELECT t FROM Book t WHERE t.status = ?1 and t.title = ?2")
	public List<Book> findByStatusTitle(boolean status, String title);
	
}
