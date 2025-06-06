package com.core.domain.controller.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.domain.model.board.BoardPost;
import com.core.domain.service.board.BoardPostService;

@Controller
@RequestMapping("/community")
public class BoardController {
    private final BoardPostService boardPostService;

    @Autowired
    public BoardController(BoardPostService boardPostService) {
        this.boardPostService = boardPostService;
    }

    @GetMapping
    public String list(Model model) {
        List<BoardPost> posts = boardPostService.findAll();
        model.addAttribute("posts", posts);
        return "community/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<BoardPost> post = boardPostService.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "community/detail";
        } else {
            return "redirect:/community";
        }
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("post", new BoardPost());
        return "community/form";
    }

    @PostMapping
    public String create(@ModelAttribute BoardPost post) {
        boardPostService.save(post);
        return "redirect:/community";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<BoardPost> post = boardPostService.findById(id);
        if (post.isPresent()) {
            model.addAttribute("post", post.get());
            return "community/form";
        } else {
            return "redirect:/community";
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute BoardPost post) {
        boardPostService.update(id, post);
        return "redirect:/community/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardPostService.deleteById(id);
        return "redirect:/community";
    }
}
