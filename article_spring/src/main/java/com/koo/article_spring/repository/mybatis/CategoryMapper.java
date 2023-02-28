package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Cacheable(value = "category", key = "#root.methodName")
    @Select("SELECT * FROM categorys")
    public abstract List<CategoryDTO> getAllCategories() throws Exception;
}
