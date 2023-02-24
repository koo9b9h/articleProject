package com.koo.article_spring.controller;

import com.koo.article_spring.domain.*;
import com.koo.article_spring.service.ListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;



@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class ListController {

    private final ListService listService;

    @RequestMapping("/list")
    public String show(InputSearchDTO InputSearchDTO, Model model, @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage) {
        try {
            SearchDTO searchDTO = new SearchDTO();
            if (InputSearchDTO != null) {
                listService.changeSearchData(InputSearchDTO,searchDTO);
            }

            List<CategoryDTO> categoryNames = listService.getCategoryNames();
            model.addAttribute("categoryNames", categoryNames);

            PageDTO pageDTO = listService.pageSetting(searchDTO, currentPage);
            model.addAttribute("pageUri", createUri(InputSearchDTO));
            model.addAttribute("pageDTO", pageDTO);

            List<ArticleDTO> articles = listService.selectList(searchDTO, pageDTO);
            model.addAttribute("articles", articles);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "list";
    }

    private String createUri(InputSearchDTO inputSearchDTO){
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/boards/free/list")
                .queryParam("category", inputSearchDTO.getCategory())
                .queryParam("searchTerm", inputSearchDTO.getSearchTerm())
                .queryParam("startDate", inputSearchDTO.getStartDate())
                .queryParam("endDate", inputSearchDTO.getEndDate());
        return uriBuilder.build().encode().toUriString();
    }
}
