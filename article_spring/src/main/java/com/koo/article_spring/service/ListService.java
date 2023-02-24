package com.koo.article_spring.service;


import com.koo.article_spring.domain.*;

import java.util.List;

public interface ListService {
    public List<ArticleDTO> selectList(SearchDTO searchDTO, PageDTO pageDTO) throws Exception;

    public PageDTO pageSetting(SearchDTO searchDTO, Integer currentPage) throws Exception;

    public List<CategoryDTO> getCategoryNames() throws Exception;

    public Integer getCategoryId(String categoryName) throws Exception;

    public SearchDTO changeSearchData(InputSearchDTO inputSearchDTO, SearchDTO searchDTO) throws Exception;
}
