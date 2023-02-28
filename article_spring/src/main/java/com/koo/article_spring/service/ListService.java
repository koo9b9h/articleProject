package com.koo.article_spring.service;


import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.PageDTO;
import com.koo.article_spring.domain.SearchDTO;

import java.util.List;

public interface ListService {
    List<ArticleDTO> selectList(SearchDTO searchDTO, PageDTO pageDTO) throws Exception;

    PageDTO pageSetting(SearchDTO searchDTO, Integer currentPage) throws Exception;

}
