package com.example.myweb.contoller;

import java.util.List;

import javax.persistence.OrderBy;

import com.example.myweb.model.Board;
import com.example.myweb.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired 
    private BoardRepository boardRepository; 

    @GetMapping("/list")
    public String list(Model model){
        List<Board> boards = boardRepository.findAll(Sort.by(Sort.Direction.ASC, "seq"));
        model.addAttribute(("boards"), boards);
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
    public String formSubmit(@ModelAttribute Board board){
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
