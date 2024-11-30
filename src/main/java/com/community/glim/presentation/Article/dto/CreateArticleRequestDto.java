package com.community.glim.presentation.Article.dto;

import com.community.glim.domain.Article.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 생성 요청 데이터 DTO
 *
 * 클라이언트로부터 받은 title, content를 Article 엔티티 객체로 변환한다.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateArticleRequestDto {

    /**
     * 게시글 제목
     */
    private String title;

    /**
     * 게시글 본문
     */
    private String content;

    /**
     * CreateArticleRequestDto 객체를 Article 엔티티 객체로 변환하는 함수
     *
     * @return Article 엔티티 객체
     */
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
