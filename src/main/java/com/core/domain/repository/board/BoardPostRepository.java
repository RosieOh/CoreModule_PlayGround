package com.core.domain.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.core.domain.model.board.BoardPost;

@Repository
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {
} 