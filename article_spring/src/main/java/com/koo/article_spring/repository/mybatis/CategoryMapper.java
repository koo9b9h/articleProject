package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM categorys")
    public abstract List<CategoryVO> getAllCategories();
}
