package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.repository.mybatis.ArticleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final ArticleMapper articleMapper;

    /**
     *
     * @param articleDTO
     * @throws Exception
     */
    @Override
    public void registerArticle(ArticleDTO articleDTO) throws Exception {
        Timestamp insertTime = new Timestamp(System.currentTimeMillis());
        articleDTO.setCreateTime(insertTime);
        articleDTO.setModifiedTime(insertTime);
        articleMapper.insertArticle(articleDTO);
    }
}
