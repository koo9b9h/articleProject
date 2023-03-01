package com.koo.article_spring.controller;

import com.koo.article_spring.controller.validation.ArticleInputData;
import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryDTO;
import com.koo.article_spring.domain.FileDTO;
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

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class WriteController {

    private final CategoryService categoryService;
    private final RegisterService registerService;


    /**
     * 카테고리 목록 보여주기
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
     * 1. 들어온 데이터 유효성 검증
     * 2. 첨부파일 업로드 및 첨부파일 정보생성
     * 3. 게시글 및 첨부파일 정보 등록
     * @param articleDTO
     * @param confirm_password
     * @param attachment1
     * @param attachment2
     * @param attachment3
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    public String register(ArticleDTO articleDTO, @RequestParam String confirm_password,
                           @RequestParam MultipartFile attachment1, @RequestParam MultipartFile attachment2, @RequestParam MultipartFile attachment3)
            throws Exception {

        ArticleInputData articleInputData = new ArticleInputData();
        if (!articleInputData.availableCheck(articleDTO, confirm_password)) {
            return "redirect:/boards/free/write";
        }

        List<MultipartFile> uploadFiles = Arrays.asList(attachment1, attachment2, attachment3);
        List<FileDTO> uploadFilesInformation = registerService.fileUpload(uploadFiles);


        articleDTO.setCategoryId(categoryService.getCategoryId(articleDTO.getCategoryName()));
        registerService.registerArticle(articleDTO, uploadFilesInformation);

        return "redirect:/boards/free/list";
    }
}
