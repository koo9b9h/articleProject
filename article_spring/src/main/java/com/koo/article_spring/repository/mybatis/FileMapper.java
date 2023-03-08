package com.koo.article_spring.repository.mybatis;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.FileDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insertFile(FileDTO fileDTO) throws Exception;

    List<FileDTO> getFiles(Integer articleId) throws Exception;

    FileDTO getFile(String uuid) throws Exception;

    void deleteFile(String uuid) throws Exception;

    void deleteFiles(Integer articleId) throws Exception;
}
