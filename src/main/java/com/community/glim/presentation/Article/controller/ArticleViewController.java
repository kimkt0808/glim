package com.community.glim.presentation.Article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 게시글 VIEW API
 *
 * 게시글 CRUD VIEW API를 구현한 Controller이다.
 */
@Controller
public class ArticleViewController {

    /**
     * 홈페이지를 요청할 때, index.html로 포워딩하는 함수
     *
     * @return index.html 페이지로 포워딩
     */
    @GetMapping("/")
    public String index() {
        return "forward:/article/index.html";
    }

    /**
     * 게시글 작성 페이지를 요청할 때, create-article.html로 포워딩하는 함수
     *
     * @return create-article.html 페이지로 포워딩
     */
    @GetMapping("/articles/create")
    public String createArticleView() {
        return "forward:/article/create-article.html";
    }

    /**
     * 게시글 상세보기 페이지를 요청할 때, detail-article.html로 포워딩하는 함수
     *
     * @param id - 게시글의 고유 ID
     * @return detail-article.html 페이지로 포워딩
     */
    @GetMapping("/articles/detail/{id}")
    public String articleDetailView(@PathVariable Long id) {
        return "forward:/article/detail-article.html";
    }

    /**
     * 게시글 수정 페이지를 요청할 때, update-article.html로 포워딩하는 함수
     *
     * @param id - 게시글의 고유 ID
     * @return update-article.html 페이지로 포워딩
     */
    @GetMapping("articles/update/{id}")
    public String udpateArticleView(@PathVariable Long id) {
        return "forward:/article/update-article.html";
    }
}
