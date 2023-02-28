package com.koo.article_spring.service;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CategoryDTO;
import com.koo.article_spring.repository.mybatis.CategoryMapper;
import com.koo.article_spring.repository.mybatis.RegisterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final RegisterMapper registerMapper;

    /**
     * 게시글 저장 로직
     * @param articleDTO
     * @throws Exception
     */
    @Override
    public void registerArticle(ArticleDTO articleDTO) throws Exception {
        Timestamp insertTime = new Timestamp(System.currentTimeMillis());
        articleDTO.setCreateTime(insertTime);
        articleDTO.setModifiedTime(insertTime);
        registerMapper.insertArtcle(articleDTO);
    }
}
