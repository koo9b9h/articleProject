package com.koo.article_spring.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileDTO {

    private String filePath;
    private String uuid = UUID.randomUUID().toString();
    private Long fileSize;
    private String fileName;
    private Integer articleId;
    private String extension;

    public FileDTO(MultipartFile file) {
            this.fileName = file.getOriginalFilename();
            this.fileSize = file.getSize();
            this.extension = fileName.substring(fileName.lastIndexOf("."));
    }
}
