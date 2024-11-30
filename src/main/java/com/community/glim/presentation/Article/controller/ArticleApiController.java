package com.community.glim.presentation.Article.controller;

import com.community.glim.application.Article.ArticleService;
import com.community.glim.domain.Article.Article;
import com.community.glim.presentation.Article.dto.ArticleResponseDto;
import com.community.glim.presentation.Article.dto.CreateArticleRequestDto;
import com.community.glim.presentation.Article.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 게시글 REST API
 *
 * 게시글의 CRUD API를 구현한 RestController이다.
 */
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;

    /**
     * 게시글을 생성하는 API
     *
     * @param createArticleRequestDto - 게시글 생성에 필요한 요청 데이터를 담은 DTO
     * @return 생성된 게시글 정보를 포함한 ResponseEntity 객체
     *          - 201(Created)
     */
    @PostMapping("/articles")
    public ResponseEntity<Article> addArticle(@RequestBody CreateArticleRequestDto createArticleRequestDto) {
        Article savedArticle = articleService.save(createArticleRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    /**
     * 특정 게시글을 조회하는 API
     *
     * @param id - 특정 게시글을 조회하기 위한 게시글의 고유 ID
     * @return 게시글 정보를 담은 ResponseEntity 객체
     *          - 200(OK)
     */
    @GetMapping("/articles/{id}")
    public ResponseEntity<ArticleResponseDto> getArticle(@PathVariable Long id) {
        Article findArticle = articleService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponseDto(findArticle));
    }

    /**
     * 모든 게시글을 조회하는 API
     *
     * @return 게시글 목록을 담은 ResponseEntity 객체
     *          - 200(OK)
     */
    @GetMapping("/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticles() {
        List<ArticleResponseDto> articles = articleService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .toList();

        return ResponseEntity.ok(articles);
    }

    /**
     * 특정 게시글을 수정하는 API
     *
     * @param id - 수정할 게시글의 고유 ID
     * @param updateArticleRequestDto - 수정할 내용이 담긴 DTO
     * @return 수정된 게시글의 정보를 담은 ResponseEntity 객체
     *          - 200(OK)
     */
    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleRequestDto updateArticleRequestDto) {
        Article updateArticle = articleService.update(updateArticleRequestDto, id);

        return ResponseEntity.ok()
                .body(updateArticle);
    }

    /**
     * 특정 게시글을 삭제하는 API
     *
     * @param id - 삭제할 게시글의 고유 ID
     * @return 빈 ResponseEntity 객체
     *          - 200(OK)
     */
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.delete(id);

        return ResponseEntity.ok()
                .build();
    }
}
