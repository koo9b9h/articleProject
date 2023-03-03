package com.koo.article_spring.service;


import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CommentDTO;
import com.koo.article_spring.domain.FileDTO;

import java.util.List;

public interface ViewService {
    ArticleDTO getArticleInformation(Integer articleId) throws Exception;

    List<FileDTO> getFilesInformation(Integer articleId) throws Exception;

    FileDTO getFileInformation(String uuid) throws Exception;

    List<CommentDTO> getComments(Integer articleId) throws Exception;

    void registerComment(CommentDTO commentDTO) throws Exception;
}
