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
