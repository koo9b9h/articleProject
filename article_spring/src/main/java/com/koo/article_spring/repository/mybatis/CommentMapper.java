package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentDTO> getComments(Integer articleId) throws Exception;

    void insertComment(CommentDTO commentDTO) throws Exception;
}
