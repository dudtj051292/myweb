package com.example.myweb.contoller;

import com.example.myweb.model.House;
import com.example.myweb.repository.HouseRepository;
import org.springframework.ui.Model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.web.PageableDefault;

@Controller
@RequestMapping("/maps")
public class MapController {
    
    @Autowired
    private HouseRepository houseRepository;
    
    @GetMapping("/map")
    public String maps(Model model, @PageableDefault(size = 10 ) Pageable pageable){
        Page<House>maps = houseRepository.findAll(pageable);
        model.addAttribute("maps", maps);
        model.addAttribute("endPage" , maps.getTotalPages());
        model.addAttribute("startPage", 1);
        return "maps/map";
    }

}
