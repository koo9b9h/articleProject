package com.koo.article_spring.controller;

import com.koo.article_spring.domain.CategoryVO;
import com.koo.article_spring.service.WriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class WriteController {

    private final WriteService writeService;

    @RequestMapping("/write")
    public String showCategoryNames(Model model){
        List<CategoryVO> categoryNames = writeService.getCategoryNames();
        model.addAttribute("categoryNames",categoryNames);
        return "write";
    }
}
