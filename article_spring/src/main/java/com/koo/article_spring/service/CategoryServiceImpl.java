package com.koo.article_spring.service;

import com.koo.article_spring.domain.CategoryDTO;
import com.koo.article_spring.repository.mybatis.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryMapper categoryMapper;

    /**
     * @CacheEvict는 변경소요가 현재 계획에는 없어서 이용안함
     * @Cacheable(value = "category", key = "#root.methodName") 적용 메소드를 왜 다른 클래스에서 실행해야 동작되는지
     * -> AOP 동작방식에 따라서 프록시 객체를 거치지 않고 직접 호출되기 때문에
     * @return categories
     * @throws Exception
     */
    @Override
    public List<CategoryDTO> getCategoryAll() throws Exception {
        List<CategoryDTO> categories = this.categoryMapper.getAllCategories();
        return categories;
    }

    @Override
    public Integer getCategoryId(String categoryName) throws Exception {
        Integer categoryId = null;
        List<CategoryDTO> categories = this.getCategoryAll();
        for(CategoryDTO categoryDTO : categories){
            if(categoryDTO.getCategoryName().equals(categoryName)){
                categoryId = categoryDTO.getCategoryId();
            }
        }
        return categoryId;
    }

    @Override
    public String getCategoryName(Integer categoryId) throws Exception {
        String categoryName = null;
        List<CategoryDTO> categories = this.getCategoryAll();
        for(CategoryDTO categoryDTO : categories){
            if(categoryDTO.getCategoryId().equals(categoryId)){
                categoryName = categoryDTO.getCategoryName();
            }
        }
        return categoryName;
    }
}
