package com.koo.article_spring.controller;

import com.koo.article_spring.controller.validation.ArticleInputData;
import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryVO;
import com.koo.article_spring.service.RegisterService;
import com.koo.article_spring.service.WriteService;
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

    private final WriteService writeService;
    private final RegisterService registerService;

    @RequestMapping("/write")
    public String showCategoryNames(Model model) {
        List<CategoryVO> categoryNames = writeService.getCategoryNames();
        model.addAttribute("categoryNames", categoryNames);
        return "write";
    }

    @PostMapping("/register")
    public String register(@RequestParam String category, @RequestParam String author, @RequestParam String password,
                           @RequestParam String confirm_password, @RequestParam String title, @RequestParam String content,
                           @RequestParam MultipartFile attachment1, @RequestParam MultipartFile attachment2,
                           @RequestParam MultipartFile attachment3) {
        ArticleDTO articleDTO = new ArticleDTO();
        ArticleInputData articleInputData = new ArticleInputData();

        articleDTO.setCategoryId(registerService.getCategoryId(category));
        articleDTO.setAuthor(author);
        articleDTO.setPassword(password);
        articleDTO.setTitle(title);
        articleDTO.setContents(content);

        if (!articleInputData.availableCheck(articleDTO, confirm_password)) {
            return "redirect: /write";
        }

        registerService.registerArticle(articleDTO);
        return "redirect: /list";
    }
}
