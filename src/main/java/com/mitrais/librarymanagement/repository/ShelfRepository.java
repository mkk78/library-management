package com.mitrais.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mitrais.librarymanagement.model.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Integer>{
	@Modifying
	@Query("UPDATE Shelf set current_capacity = current_capacity + ?2 where shelf_id = ?1")
	public int setCurrentCap(int id, int current);
}
