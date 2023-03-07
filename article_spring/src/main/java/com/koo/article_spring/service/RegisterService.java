package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface RegisterService {
    void registerArticle(ArticleDTO articleDTO, List<FileDTO> files) throws Exception;

    List<FileDTO> fileUpload(List<MultipartFile> uploadFiles) throws Exception;

    void registerFileInformation(Integer articleId,List<FileDTO> uploadFilesInformation) throws Exception;
}
