package com.koo.article_spring.controller;

import com.koo.article_spring.controller.validation.ArticleInputData;
import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryDTO;
import com.koo.article_spring.service.CategoryService;
import com.koo.article_spring.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class WriteController {

    private final CategoryService categoryService;
    private final RegisterService registerService;


    /**
     * 카테고리 목록 보여지게 모델에 담아보내기
     *
     * @param model
     * @return
     */
    @RequestMapping("/write")
    public String showCategoryNames(Model model) throws Exception {

        List<CategoryDTO> categoryNames = categoryService.getCategoryAll();
        model.addAttribute("categoryNames", categoryNames);


        return "write";
    }

    /**
     * 저장 버튼 눌렀을 때 DTO에 담음.
     */
    @PostMapping("/register")
    public String register(ArticleDTO articleDTO,
                           @RequestParam String confirm_password,
                           @RequestParam MultipartFile attachment1, @RequestParam MultipartFile attachment2,
                           @RequestParam MultipartFile attachment3) throws Exception {

        ArticleInputData articleInputData = new ArticleInputData();
        articleDTO.setCategoryId(categoryService.getCategoryId(articleDTO.getCategoryName()));
        if (!articleInputData.availableCheck(articleDTO, confirm_password)) {
            return "redirect:/boards/free/write";
        }

        registerService.registerArticle(articleDTO);

        return "redirect:/boards/free/list";
    }
}
