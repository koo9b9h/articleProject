package com.koo.article_spring.controller;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryDTO;
import com.koo.article_spring.domain.PageDTO;
import com.koo.article_spring.domain.SearchDTO;
import com.koo.article_spring.service.CategoryService;
import com.koo.article_spring.service.ListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


/**
 * 예외처리를 어떻게 해야할지? 감이 안잡힙니당
 * 다 서비스와 리포지토리쪽에서 Exception으로 다 던져서 Controller부분에서 catch하였습니다.
 * (Exception파일에 각 데이터의 맞게 커스텀하여 처리하라고 배웠는데 어디서는 Controller에 몰아줘야한다고 감이 안잡혔습니다.)
 * (뭔가 명확하게 이해하지 못한 상태)
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class ListController {

    private final CategoryService categoryService;
    private final ListService listService;

    /**
     * 검색조건 받기
     * 카테고리 목록 보여지게 모델 보내기
     * 페이징 처리(페이지 넘겨도 검색조건 유지되게)
     * 검색조건에 맞춘 리스트 목록
     *
     * @param searchDTO
     * @param model
     * @param currentPage
     * @returng
     */
    @RequestMapping("/list")
    public String show(SearchDTO searchDTO,
                       Model model,
                       @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage)
            throws Exception {

        searchDTO.setCategoryId(categoryService.getCategoryId(searchDTO.getCategoryName()));

        List<CategoryDTO> categoryNames = categoryService.getCategoryAll();
        model.addAttribute("categoryNames", categoryNames);

        PageDTO pageDTO = listService.pageSetting(searchDTO, currentPage);
        model.addAttribute("pageUri", createUri(searchDTO));
        model.addAttribute("pageDTO", pageDTO);

        List<ArticleDTO> articles = listService.selectList(searchDTO, pageDTO);
        articles = articleNameSet(articles);
        model.addAttribute("articles", articles);

        return "list";
    }

    private String createUri(SearchDTO SearchDTO) throws Exception {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/boards/free/list")
                .queryParam("category", categoryService.getCategoryName(SearchDTO.getCategoryId()))
                .queryParam("searchTerm", SearchDTO.getSearchTerm())
                .queryParam("startDate", SearchDTO.getStartDate())
                .queryParam("endDate", SearchDTO.getEndDate());
        return uriBuilder.build().encode().toUriString();
    }

    private List<ArticleDTO> articleNameSet(List<ArticleDTO> articles) throws Exception {
        for (ArticleDTO article : articles) {
            String categoryName = categoryService.getCategoryName(article.getCategoryId());
            article.setCategoryName(categoryName);
        }
        return articles;
    }
}
