package com.example.myweb.contoller;


import javax.validation.Valid;

import com.example.myweb.model.Board;
import com.example.myweb.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired 
    private BoardRepository boardRepository; 

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2 ) Pageable pageable, 
                       @RequestParam(required = false, defaultValue = "") String searchText ){
        // Page <Board> boards = boardRepository.findAll(pageable);
        Page<Board>boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        model.addAttribute("boards", boards);
        model.addAttribute("endPage" , boards.getTotalPages());
        model.addAttribute("startPage", 1);
        return "board/list";
    }

    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long seq){
        if(seq == null){
            model.addAttribute("board", new Board());
        }else{
            model.addAttribute("board", boardRepository.findById(seq).orElse(null));
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String formSubmit(@Valid Board board , BindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            return "board/form";
		}
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
