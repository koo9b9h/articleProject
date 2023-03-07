package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.FileDTO;

import java.util.List;

public interface ModifyService {
    ArticleDTO getArticleInformation(Integer articleId) throws Exception;

    void modifyArticle(ArticleDTO articleDTO) throws Exception;

    String getArticlePassword(Integer articleId) throws Exception;

    List<FileDTO> getAttachedFileList(Integer articleId) throws Exception;

    void deleteFiles(List<String> deleteFilesUuid) throws Exception;
}
