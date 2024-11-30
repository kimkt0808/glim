package com.community.glim.presentation.Article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 수정 요청 데이터 DTO
 *
 * 클라이언트로부터 받은 title, content이다.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdateArticleRequestDto {

    /**
     * 게시글 제목
     */
    private String title;

    /**
     * 게시글 본문
     */
    private String content;
}
