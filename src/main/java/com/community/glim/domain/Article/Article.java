package com.community.glim.domain.Article;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 게시글 엔티티 클래스
 *
 * 게시글의 title, content를 저장하며, 게시글 정보를 수정할 수 있다.
 */
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    /**
     * 게시글의 고유 ID
     *
     * 자동 생성된다.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    /**
     * 게시글의 제목
     *
     * 필수로 입력해야 한다.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * 게시글의 본문
     *
     * 필수로 입력해야 한다.
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * 게시글을 생성하는 빌더 패턴을 제공하는 생성자
     *
     * @param title - 게시글의 제목
     * @param content - 게시글의 본문
     */
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * 게시글을 수정하는 함수
     *
     * @param title - 수정할 게시글의 제목
     * @param content - 수정할 게시글의 본문
     */
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
