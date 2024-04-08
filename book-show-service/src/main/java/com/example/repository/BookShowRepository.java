package com.example.repository;

import com.example.entity.BookShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookShowRepository extends JpaRepository<BookShow, Integer> {
}
