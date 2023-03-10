package com.koo.article_spring.controller.validation;

import com.koo.article_spring.domain.ArticleDTO;


/**
 * TODO
 * validation 어노테이션으로 대체하기
 */
public class ArticleInputData {
    public boolean availableCheck(ArticleDTO articleDTO, String confirm_password) {

        if (articleDTO.getAuthor().length() < 3 || articleDTO.getAuthor().length() >= 5) {
            return false;
        }

        if (articleDTO.getPassword().length() < 4 || articleDTO.getPassword().length() >= 16) {
            return false;
        }

        if (!(articleDTO.getPassword().equals(confirm_password))) {
            return false;
        }

        if (articleDTO.getTitle().length() < 4 || articleDTO.getTitle().length() >= 100) {
            return false;
        }

        if (articleDTO.getContents().length() < 4 || articleDTO.getContents().length() >= 2000) {
            return false;
        }
        return true;
    }
}
