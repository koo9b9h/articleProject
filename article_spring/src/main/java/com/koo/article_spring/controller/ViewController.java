package com.koo.article_spring.controller;

import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.CommentDTO;
import com.koo.article_spring.domain.FileDTO;
import com.koo.article_spring.service.ViewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * 1. article에 대한 정보를(조회수 +1해서) view단에 넘겨준다.
 * 2. article에 맞는 file들 정보를 view 넘겨준다.
 * 3. 정보에 맞는 file을 다운로드
 * 4. comment에 대해 추가한다.
 * 5. comment에 대한 전체조회 정보를 넘겨준다.
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class ViewController {

    private final ViewService viewService;

    @RequestMapping("/view/{article}")
    public String show(@PathVariable("article") Integer articleId, Model model, HttpServletRequest request) throws Exception {

        ArticleDTO articleDTO = viewService.getArticleInformation(articleId);
        model.addAttribute("article", articleDTO);

        List<FileDTO> filesInformation = viewService.getFilesInformation(articleId);
        if (filesInformation.size() > 0) {
            model.addAttribute("files", filesInformation);
        }

        List<CommentDTO> comments = viewService.getComments(articleId);
        model.addAttribute("comments", comments);

        model.addAttribute("list", request.getHeader("Referer"));
        return "view";
    }

    @RequestMapping("/download/{uuid}")
    public void download(@PathVariable("uuid") String uuid, HttpServletResponse response) throws Exception {

        FileDTO fileDTO = viewService.getFileInformation(uuid);
        File file = new File(fileDTO.getFilePath());

        String fileName = fileDTO.getFileName();
        String encodedFileName = URLEncoder.encode(fileName, "UTF-8");

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
        response.setContentLength((int) file.length());

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }

    @RequestMapping("/comment")
    public String registerComment(CommentDTO commentDTO, HttpServletRequest request) throws Exception {
        Timestamp insertTime = new Timestamp(System.currentTimeMillis());
        commentDTO.setCreateTime(insertTime);

        viewService.registerComment(commentDTO);

        return "redirect:" + request.getHeader("Referer");
    }
}
