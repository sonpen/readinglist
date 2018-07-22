package com.manning;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by sonikju on 2018-06-30.
 */
public interface ReadinglistRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(Reader reader);
}
