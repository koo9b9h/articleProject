package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.ArticleDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper {
    @Insert("INSERT INTO articles (category_id, author, password, title, contents, create_time, modified_time) " +
            "VALUES (#{categoryId}, #{author}, #{password}, #{title}, #{contents}, #{createTime}, #{modifiedTime})")
    void insertArtcle(ArticleDTO articleDTO) throws Exception;
}
