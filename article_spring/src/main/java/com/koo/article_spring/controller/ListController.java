package com.koo.article_spring.controller;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryDTO;
import com.koo.article_spring.domain.Pagination;
import com.koo.article_spring.service.CategoryService;
import com.koo.article_spring.service.ListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class ListController {

    private final CategoryService categoryService;
    private final ListService listService;

    /**
     * TODO: currentPage 따로 받는게 아니라 pagination 으로 받게 고치기
     * 1.카테고리 목록 보이기
     * 2. 페이지(pagination) 세팅하기
     * 3. pagination 정보를 이용해 uri 만들기
     * 4. 세팅한 pagination 정보를 가지고 목록 가져오기
     * @param pagination
     * @param model
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    public String show(Pagination pagination,
                       Model model,
                       @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage)
            throws Exception {

        List<CategoryDTO> categoryNames = categoryService.getCategoryAll();
        model.addAttribute("categoryNames", categoryNames);

        pagination = listService.pageSetting(pagination, currentPage);
        model.addAttribute("pagiNation", pagination);

        String pageUri = listService.createUri(pagination);
        model.addAttribute("pageUri", pageUri);

        List<ArticleDTO> articles = listService.getList(pagination);
        model.addAttribute("articles", articles);

        return "list";
    }
}
