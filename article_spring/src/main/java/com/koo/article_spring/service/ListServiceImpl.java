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
    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryDTO> getCategoryNames() throws Exception {
        List<CategoryDTO> categories = this.categoryMapper.getAllCategories();
        log.trace(categories.toString());
        return categories;
    }

    @Override
    public Integer getCategoryId(String categoryName) throws Exception {
        return categoryMapper.getCategoryId(categoryName);
    }

    @Override
    public SearchDTO changeSearchData(InputSearchDTO inputSearchDTO, SearchDTO searchDTO) throws Exception {
        Integer categoryId = this.getCategoryId(inputSearchDTO.getCategory());
        String searchTerm = "";
        if(inputSearchDTO.getSearchTerm() != null){
            searchTerm = inputSearchDTO.getSearchTerm().replaceAll(" ", "");// split?..
        }
        String startDate = inputSearchDTO.getStartDate();
        String endDate = inputSearchDTO.getEndDate();

        Timestamp start = null;
        Timestamp end = null;

        if (startDate != null && endDate != null && (!"".equals(startDate)) && (!"".equals(endDate))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            start = new Timestamp(dateFormat.parse(startDate).getTime());
            end = new Timestamp(dateFormat.parse(endDate).getTime());
        }

        searchDTO.setCategoryId(categoryId);
        searchDTO.setSearchTerm(searchTerm);
        searchDTO.setStartDate(start);
        searchDTO.setEndDate(end);

        return searchDTO;
    }

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
