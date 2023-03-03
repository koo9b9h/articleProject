package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CommentDTO;
import com.koo.article_spring.domain.FileDTO;
import com.koo.article_spring.repository.mybatis.ArticleMapper;
import com.koo.article_spring.repository.mybatis.CategoryMapper;
import com.koo.article_spring.repository.mybatis.CommentMapper;
import com.koo.article_spring.repository.mybatis.FileMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 1. article에 대한 정보를 repository에서 받아온다.
 * 2. file에 대한 정보를 repository에서 받아온다.
 * 3. comment 대한 정보를 DB에 저장한다.
 * 4. comment에 대해 repository에서 전체 조회한다.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ViewServiceImpl implements ViewService {

    private final ArticleMapper articleMapper;
    private final FileMapper fileMapper;
    private final CommentMapper commentMapper;
    private final CacheManager cacheManager;

    /**
     * 캐시 이용  키값으로 article 정보에 대해서 캐시 저장
     * 캐시의 값은 +1 씩 계속 반환되고 10으로 나눠 떨어질때만 DB 접근하게끔
     * 처음에 조회랑 조회수 업데이트를 분리했다가
     * 합쳐서 두가지를 품었는데 2가지 기능을 가지고 있는 것은 객체지향적이지 못한게 아닌지.
     * 아니면 유사한 성격과 주기여서 상관없을지
     * @param articleId
     * @return
     * @throws Exception
     */
    @CachePut(value = "article" ,key = "#articleId")
    @Override
    public ArticleDTO getArticleInformation(Integer articleId) throws Exception {

        ArticleDTO articleDTO = articleMapper.getArticle(articleId);
        articleDTO.setViews(articleDTO.getViews()+1);

        if((articleDTO.getViews() % 10 == 0)) {
            articleMapper.updateViews(articleDTO);
        }

        return articleDTO;
    }

    @Override
    public List<FileDTO> getFilesInformation(Integer articleId) throws Exception {
        List<FileDTO> files = fileMapper.getFiles(articleId);
        return files;
    }

    @Override
    public FileDTO getFileInformation(String uuid) throws Exception {
        FileDTO fileDTO = fileMapper.getFile(uuid);
        return fileDTO;
    }

    @Override
    public List<CommentDTO> getComments(Integer articleId) throws Exception {
        List<CommentDTO> comments = commentMapper.getComments(articleId);
        return comments;
    }

    @Override
    public void registerComment(CommentDTO commentDTO) throws Exception {
        commentMapper.insertComment(commentDTO);
    }
}
