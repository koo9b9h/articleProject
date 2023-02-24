package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryDTO;
import com.koo.article_spring.repository.mybatis.CategoryMapper;
import com.koo.article_spring.repository.mybatis.RegisterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final CategoryMapper categoryMapper;
    private final RegisterMapper registerMapper;

    @Override
    public Integer getCategoryId(String categoryName) throws Exception {
        List<CategoryDTO> categories = this.categoryMapper.getAllCategories();
        Integer categoryId = null;
        for (CategoryDTO category : categories) {
            if(category.getCategoryName().equals(categoryName)){
                categoryId = category.getCategoryId();
            }
        }
        if(categoryId == null){
            throw new IllegalStateException("카테고리를 입력 해야합니다.");
        }
        return categoryId;
    }

    @Override
    public void registerArticle(ArticleDTO articleDTO) throws Exception {
        Timestamp insertTime = new Timestamp(System.currentTimeMillis());
        articleDTO.setCreateTime(insertTime);
        articleDTO.setModifiedTime(insertTime);
        registerMapper.insertArtcle(articleDTO);
    }
}
