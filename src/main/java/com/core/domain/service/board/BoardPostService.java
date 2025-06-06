package com.core.domain.service.board;

import java.util.List;
import java.util.Optional;

import com.core.domain.model.board.BoardPost;

public interface BoardPostService {
    List<BoardPost> findAll();
    Optional<BoardPost> findById(Long id);
    BoardPost save(BoardPost post);
    BoardPost update(Long id, BoardPost updatedPost);
    void deleteById(Long id);
} 