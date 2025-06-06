package com.core.domain.service.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.domain.model.board.BoardPost;
import com.core.domain.repository.board.BoardPostRepository;

@Service
public class BoardPostServiceImpl implements BoardPostService {
    private final BoardPostRepository boardPostRepository;

    @Autowired
    public BoardPostServiceImpl(BoardPostRepository boardPostRepository) {
        this.boardPostRepository = boardPostRepository;
    }

    @Override
    public List<BoardPost> findAll() {
        return boardPostRepository.findAll();
    }

    @Override
    public Optional<BoardPost> findById(Long id) {
        return boardPostRepository.findById(id);
    }

    @Override
    public BoardPost save(BoardPost post) {
        return boardPostRepository.save(post);
    }

    @Override
    public BoardPost update(Long id, BoardPost updatedPost) {
        return boardPostRepository.findById(id).map(post -> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setAuthor(updatedPost.getAuthor());
            return boardPostRepository.save(post);
        }).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    @Override
    public void deleteById(Long id) {
        boardPostRepository.deleteById(id);
    }
} 