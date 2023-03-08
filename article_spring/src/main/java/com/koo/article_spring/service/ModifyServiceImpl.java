package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.FileDTO;
import com.koo.article_spring.repository.mybatis.ArticleMapper;
import com.koo.article_spring.repository.mybatis.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ModifyServiceImpl implements ModifyService {

    private final ArticleMapper articleMapper;
    private final CategoryService categoryService;
    private final FileMapper fileMapper;

    /**
     * 게시글 패스워드 얻기 (따로 놓은이유)
     * 해당 반환값은 따로 비교하며 유효성 검사하고 사라지고 DTO에 담은 다른정보들은
     * 그대로 view로 이동 된다.
     * @param articleId
     * @return
     * @throws Exception
     */
    public String getArticlePassword(Integer articleId) throws Exception {
        String articlePassword = articleMapper.getPassword(articleId);
        return articlePassword;
    }

    /**
     * 게시글 정보 얻기
     * @param articleId
     * @return
     * @throws Exception
     */
    public ArticleDTO getArticleInformation(Integer articleId) throws Exception {
        ArticleDTO articleDTO = articleMapper.getArticle(articleId);
        String categoryName = categoryService.getCategoryName(articleDTO.getCategoryId());
        articleDTO.setCategoryName(categoryName);
        return articleDTO;
    }

    /**
     * 게시글 정보 바꾸기
     * @param articleDTO
     * @throws Exception
     */
    public void modifyArticle(ArticleDTO articleDTO) throws Exception {
        Timestamp insertTime = new Timestamp(System.currentTimeMillis());
        articleDTO.setModifiedTime(insertTime);
        articleMapper.updateArticle(articleDTO);
    }

    /**
     * 첨부된 파일들의 정보 반환
     * @param articleId
     * @return
     * @throws Exception
     */
    public List<FileDTO> getAttachedFileList(Integer articleId) throws Exception {
        List<FileDTO> files = fileMapper.getFiles(articleId);
        return files;
    }

    /**
     * 파일 삭제와 정보 삭제
     *
     * @param fileUuids
     * @throws Exception
     */
    public void deleteFiles(List<String> fileUuids) throws Exception {
        for (String fileUuid : fileUuids) {
                FileDTO fileDTO = fileMapper.getFile(fileUuid);
                File file = new File(fileDTO.getFilePath());
                file.delete();
                fileMapper.deleteFile(fileUuid);
        }
    }

}
