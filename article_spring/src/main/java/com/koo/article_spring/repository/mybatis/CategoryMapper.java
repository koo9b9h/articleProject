package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM categorys")
    public abstract List<CategoryDTO> getAllCategories() throws Exception;

    @Select("SELECT category_id FROM categorys WHERE category_name = #{categoryName}")
    public abstract Integer getCategoryId(String categoryName) throws Exception;

}
