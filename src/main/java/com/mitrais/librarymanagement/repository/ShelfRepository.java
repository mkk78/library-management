package com.mitrais.librarymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mitrais.librarymanagement.model.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Integer>{

}
