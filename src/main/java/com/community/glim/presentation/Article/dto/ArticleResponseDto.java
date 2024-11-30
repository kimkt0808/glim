package com.community.glim.presentation.Article.dto;

import com.community.glim.domain.Article.Article;
import lombok.Getter;

/**
 * 게시글 응답 데이터 DTO
 *
 * Article 객체에서 id, title, content를 추출하여 ArticleResponseDto 객체로 변환하여 클라이언트에 전달한다.
 */
@Getter
public class ArticleResponseDto {

    /**
     * 게시글 고유 ID
     */
    private final Long id;

    /**
     * 게시글 제목
     */
    private final String title;

    /**
     * 게시글 본문
     */
    private final String content;

    /**
     * Article 엔티티 객체를 ArticleResponseDto로 변환하는 생성자
     *
     * @param article - 변환할 Article 엔티티 객체
     */
    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
