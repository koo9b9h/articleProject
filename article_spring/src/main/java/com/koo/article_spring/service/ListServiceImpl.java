package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.Pagination;
import com.koo.article_spring.repository.mybatis.ArticleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListServiceImpl implements ListService {

    private final ArticleMapper articleMapper;
    private final CategoryService categoryService;

    /**
     * 리뷰대로 page랑 search에 대한 데이터를 함께 가져가도록 수정하고
     * DTO생성자를 통해 한 번에 초기화 하고 정리하려고 하다가 고민!
     * getList에서
     * 1. pagination에 대한 초기화 작업이 이뤄진다.
     * 2. 게시글에 대한 목록을 불러오는 작업도 이뤄진다.
     * 2가지 기능에다가 getQeuryString을 만들어 넘기는 기능까지 더하면
     * 한 메소드에 너무 많은 기능을 담는 것은 아닌지
     * 또한 다른 service로직 끼리의 사용이
     * repository serivce controller 흐름과 상관이 없을까도 고민입니다.
     *
     * @param pagination
     * @return
     * @throws Exception
     */
    public List<ArticleDTO> getList(Pagination pagination) throws Exception {
        List<ArticleDTO> articles = articleMapper.getArticleList(pagination);
        return articleCategoryNameSet(articles);
    }

    @Override
    public Pagination pageSetting(Pagination pagination, Integer currentPage) throws Exception {
        pagination.setCategoryId(categoryService.getCategoryId(pagination.getCategoryName()));
        pagination.setArticleTotalCount(articleMapper.getArticleTotalCount(pagination));
        pagination.setTotalPages((int) Math.ceil(pagination.getArticleTotalCount() * 1.0 / pagination.getRecordsPerPage()));
        if (currentPage != null) {
            pagination.setCurrentPage(currentPage);
        }
        pagination.setStart((pagination.getCurrentPage() - 1) * pagination.getRecordsPerPage());

        return pagination;
    }

    public String createUri(Pagination pagination) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/boards/free/list")
                .queryParam("category", pagination.getCategoryName())
                .queryParam("searchTerm", pagination.getSearchTerm())
                .queryParam("startDate", pagination.getStartDate())
                .queryParam("endDate", pagination.getEndDate());
        return uriBuilder.build().encode().toUriString();
    }

    private List<ArticleDTO> articleCategoryNameSet(List<ArticleDTO> articles) throws Exception {
        for (ArticleDTO article : articles) {
            String categoryName = categoryService.getCategoryName(article.getCategoryId());
            article.setCategoryName(categoryName);
        }
        return articles;
    }
}
