package com.example.model1_article.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequiredDTO {
    private String categoryName;
    private String author;
    private String password;
    private String title;
    private String contents;
}
