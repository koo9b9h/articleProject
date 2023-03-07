package com.koo.article_spring.controller;


import com.koo.article_spring.controller.validation.ArticleInputData;
import com.koo.article_spring.domain.ArticleDTO;
import com.koo.article_spring.domain.FileDTO;
import com.koo.article_spring.service.ModifyService;
import com.koo.article_spring.service.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * TODO:
 * article정보 view에 넘겨주기
 * view에서 수정되는 form 정보들 받아서 service로직대로 처리
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class ModifyController {

    private final ModifyService modifyService;
    private final RegisterService registerService;

    @RequestMapping("/modify")
    public String modify(@RequestParam("modify") Integer articleId, Model model) throws Exception {

        ArticleDTO articleDTO = modifyService.getArticleInformation(articleId);
        List<FileDTO> files = modifyService.getAttachedFileList(articleId);

        model.addAttribute("article", articleDTO);
        model.addAttribute("files", files);

        return "modify";
    }

    /**
     * TODO:
     * 게시글 변경
     * 파일 삭제,
     * 추가된 파일 파일 추가/업로드 (추가 할 때 article ID 넣어주기)
     *
     * @param articleDTO
     * @param formdata
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyRegister")
    public String modify(ArticleDTO articleDTO, @RequestParam Map<String, String> formdata, Model model,
                         MultipartFile attachment1, MultipartFile attachment2, MultipartFile attachment3) throws Exception {

        // 유효성 검사
        ArticleInputData articleInputData = new ArticleInputData();
        String articlePassword = modifyService.getArticlePassword(articleDTO.getArticleId());
        if (!articleInputData.availableCheck(articleDTO, articlePassword)) {
            return "forward:/boards/free/list";
        }

        //게시글 수정(repository 영역에서 기존 캐시도 삭제)
        modifyService.modifyArticle(articleDTO);

        // 첨부파일 수정(삭제)
        List<String> deleteFilesUuid = getUuids(formdata);
        modifyService.deleteFiles(deleteFilesUuid);

        //첨부파일 수정(등록)
        List<MultipartFile> uploadFiles = Arrays.asList(attachment1, attachment2, attachment3);
        List<FileDTO> uploadFilesInformation = registerService.fileUpload(uploadFiles);
        registerService.registerFileInformation(articleDTO.getArticleId(), uploadFilesInformation);

        return "redirect:/boards/free/list";
    }

    /**
     * form 으로부터 삭제할 첨부파일의 정보를 받은 뒤 uuid를 추출한다.
     *
     * @param formdata
     * @return
     */
    private List<String> getUuids(Map<String, String> formdata) {
        List<String> uuids = new ArrayList<>();
        for (String data : formdata.keySet()) {
            if (data.startsWith("deleteFileUuid")) {
                uuids.add(formdata.get(data));
            }
        }
        return uuids;
    }

}
