package com.koo.article_spring.service;

import com.koo.article_spring.domain.*;
import com.koo.article_spring.repository.mybatis.ArticleListMapper;
import com.koo.article_spring.repository.mybatis.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {

    private final ArticleListMapper articleListMapper;

    /*
    getList( Pagination pagination ){

        int totalCount = service.getCount(pagination.getSearchParams() );

        pagination.setTotalCount(totalCount);
        pagination.initialize();

        List<Article> articles = service.getArticles( pagination );

        setAttribute(pagination);
        setAttribute(articles);

        //in view
        pagination.getQueryString();    // searchTerm=xx&sdfwef=xx&
    }*/


    @Override
    public List<ArticleDTO> selectList(SearchDTO searchDTO, PageDTO pageDTO) throws Exception {
        return articleListMapper.getArticleList(searchDTO, pageDTO);
    }

    @Override
    public PageDTO pageSetting(SearchDTO searchDTO, Integer currentPage) throws Exception {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setArticleTotalCount(articleListMapper.getArticleTotalCount(searchDTO));
        pageDTO.setTotalPages((int) Math.ceil(pageDTO.getArticleTotalCount() * 1.0 / pageDTO.getRecordsPerPage()));
        if (currentPage != null) {
            pageDTO.setCrrentPage(currentPage);
        }
        pageDTO.setStart((pageDTO.getCrrentPage() - 1) * pageDTO.getRecordsPerPage());

        return pageDTO;
    }
}
