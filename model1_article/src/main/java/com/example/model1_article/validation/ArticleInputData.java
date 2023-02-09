package com.example.model1_article.validation;

import com.example.model1_article.DTO.RegisterRequiredDTO;

public class ArticleInputData {
    public boolean availableCheck(RegisterRequiredDTO registerRequiredDTO, String confirm_password) {

        if (registerRequiredDTO.getAuthor().length() < 3 || registerRequiredDTO.getAuthor().length() >= 5) {
            System.out.println(registerRequiredDTO.getAuthor() + "/" + registerRequiredDTO.getAuthor().length());
            return false;
        }

        if (registerRequiredDTO.getPassword().length() < 4 || registerRequiredDTO.getPassword().length() >= 16) {
            return false;
        }

        if (!(registerRequiredDTO.getPassword().equals(confirm_password))) {
            return false;
        }

        if (registerRequiredDTO.getTitle().length() < 4 || registerRequiredDTO.getTitle().length() >= 100) {
            System.out.println(registerRequiredDTO.getTitle() + "/" + registerRequiredDTO.getTitle().length());
            return false;
        }

        if (registerRequiredDTO.getContents().length() < 4 || registerRequiredDTO.getContents().length() >= 2000) {
            return false;
        }
        return true;
    }
}
