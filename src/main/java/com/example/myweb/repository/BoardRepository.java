package com.example.myweb.repository;

import java.util.List;

import com.example.myweb.model.Board;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

public interface BoardRepository extends JpaRepository<Board, Long>  {
    
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title,String content);
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
