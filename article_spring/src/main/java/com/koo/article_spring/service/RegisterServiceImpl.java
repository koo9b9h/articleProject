package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.FileDTO;
import com.koo.article_spring.repository.mybatis.ArticleMapper;
import com.koo.article_spring.repository.mybatis.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    @Value("${file.upload-dir}")
    private String uploadDir;
    private final ArticleMapper articleMapper;
    private final FileMapper fileMapper;

    /**
     *  1. 게시글정보와 첨부파일 정보를 등록한다.
     * @param articleDTO
     * @throws Exception
     */
    @Override
    public void registerArticle(ArticleDTO articleDTO, List<FileDTO> uploadFilesInformation) throws Exception {
        Timestamp insertTime = new Timestamp(System.currentTimeMillis());
        articleDTO.setCreateTime(insertTime);
        articleDTO.setModifiedTime(insertTime);
        articleMapper.insertArticle(articleDTO);

        Integer articleId = articleDTO.getArticleId();

        for (FileDTO fileDTO : uploadFilesInformation) {
            fileDTO.setArticleId(articleId);
            fileMapper.insertFile(fileDTO);
        }
    }

    /**
     * 1. 파일 업로드
     * 2. 업로드한 파일들의 정보를 넘겨준다.
     * @param uploadFiles
     * @return
     * @throws Exception
     */
    public List<FileDTO> fileUpload(List<MultipartFile> uploadFiles) throws Exception {
        List<FileDTO> uploadFilesInformation = new ArrayList<>();
        for (MultipartFile uploadFile : uploadFiles) {
            if (!uploadFile.isEmpty()) {
                FileDTO fileDTO = new FileDTO(uploadFile);
                fileDTO.setFilePath(uploadDir);
                uploadFilesInformation.add(fileDTO);
                Path path = Paths.get(fileDTO.getFilePath() + "/" + fileDTO.getUuid() + "." + fileDTO.getExtension());
                uploadFile.transferTo(path);
            }
        }
        return uploadFilesInformation;
    }
}
