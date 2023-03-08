package com.koo.article_spring.service;

import com.koo.article_spring.domain.FileDTO;
import com.koo.article_spring.repository.mybatis.ArticleMapper;
import com.koo.article_spring.repository.mybatis.CommentMapper;
import com.koo.article_spring.repository.mybatis.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteServiceImpl implements DeleteService {

    private final ArticleMapper articleMapper;
    private final FileMapper fileMapper;
    private final CommentMapper commentMapper;

    public Boolean isPasswordCheck(Integer articleId, String password) throws Exception {
        Boolean isPasswordCheck = articleMapper.getPassword(articleId).equals(password);
        return isPasswordCheck;
    }

    /**
     * article과 관련하 정보들 다 삭제
     * @param articleId
     * @throws Exception
     */
    public void deleteAllArticleInfo(Integer articleId) throws Exception {
        deleteFiles(articleId);
        commentMapper.deleteComments(articleId);
        articleMapper.deleteArticle(articleId);
    }

    /**
     *  파일 삭제, 파일정보 삭제
     *  코드가 길어져서 따로 선언해서 분리했다.
     * @param articleId
     * @throws Exception
     */
    public void deleteFiles(Integer articleId) throws Exception {
        List<FileDTO> files = fileMapper.getFiles(articleId);
        List<String> fileUuids = new ArrayList<>();
        for(FileDTO file : files){
            if(file != null){
                fileUuids.add(file.getUuid());
            }
        }

        //file 자체 삭제
        for (String fileUuid : fileUuids) {
            FileDTO fileDTO = fileMapper.getFile(fileUuid);
            File file = new File(fileDTO.getFilePath());
            file.delete();
        }

        //DB 데이터 삭제
        fileMapper.deleteFiles(articleId);
    }
}
