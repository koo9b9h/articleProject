package com.koo.article_spring.service;


import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.Pagination;

import java.util.List;

public interface ListService {
    List<ArticleDTO> getList(Pagination pagination) throws Exception;

    Pagination pageSetting(Pagination pagination,Integer currentPage) throws Exception;
    String createUri(Pagination pagination) throws Exception;
}
