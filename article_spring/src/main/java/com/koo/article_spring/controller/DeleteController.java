package com.koo.article_spring.controller;

import com.koo.article_spring.service.DeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/boards/free")
@Controller
public class DeleteController {

    private final DeleteService deleteService;

    /**
     * 유효성 검사는 프론트에서 자리수만 맞게 검사하고 비밀번호는 서버쪽에서 확인
     * 한 uri에서 처리를 다하게끔 만듬. 나눠야하는 지?
     * 1. 처음 접근 할 때의 화면에서는 password 값이 없기 떄문에 체크해준다
     * 2. 비밀번호 값이 들어왔다면 이를 db에 있는 비밀번호와 일치여부를 파악한다.
     * 3. 비밀번호가 일치한다면 삭제하고 리스트로 반환한다.
     * 4. 일치하지 않는다면 javascript로 본래 화면으로 이동하게 처리
     * @param password
     * @param articleId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public String delete(@RequestParam(value = "password", required = false) String password,
                         @RequestParam("delete") Integer articleId, Model model) throws Exception {

        Boolean isPasswordCheckResult = false;

        if (password != null) {
            isPasswordCheckResult = deleteService.isPasswordCheck(articleId, password);
            model.addAttribute("isFail", isPasswordCheckResult);
        }

        if (isPasswordCheckResult) {
            deleteService.deleteAllArticleInfo(articleId);
        }

        model.addAttribute("articleId", articleId);
        return "delete";
    }
}
