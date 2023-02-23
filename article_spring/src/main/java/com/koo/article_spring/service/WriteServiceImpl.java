package com.koo.article_spring.service;

import com.koo.article_spring.domain.CategoryVO;
import com.koo.article_spring.repository.mybatis.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WriteServiceImpl implements WriteService {

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryVO> getCategoryNames() {
        List<CategoryVO> categories = this.categoryMapper.getAllCategories();
        return categories;
    }
}
