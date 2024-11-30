package com.community.glim.application.Article;

import com.community.glim.domain.Article.Article;
import com.community.glim.domain.Article.ArticleRepository;
import com.community.glim.presentation.Article.dto.CreateArticleRequestDto;
import com.community.glim.presentation.Article.dto.UpdateArticleRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;


    /**
     * 새로운 게시글을 저장하는 함수
     *
     * @param createArticleRequestDto - 생성 요청 데이터가 담긴 DTO
     * @return 저장된 게시글 엔티티 객체
     */
    public Article save(CreateArticleRequestDto createArticleRequestDto) {
        Article savedArticle = articleRepository.save(createArticleRequestDto.toEntity());
        return savedArticle;
    }

    /**
     * 모든 게시글을 조회하는 함수
     *
     * @return 게시글 엔티티 목록
     */
    public List<Article> findAll() {
        List<Article> findAllarticles = articleRepository.findAll();
        return findAllarticles;
    }

    /**
     * ID를 기준으로 특정 게시글을 조회하는 함수
     *
     * @param id - 조회할 게시글의 고유 ID
     * @return 조회된 게시글의 엔티티 객체
     * @throws IllegalArgumentException - 게시글이 존재하지 않을 경우 예외 발생
     */
    public Article findById(Long id) {
        Article findArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + id));

        return findArticle;
    }

    /**
     * 특정 게시글을 수정하는 함수
     *
     * @param updateArticleRequestDto - 수정 요청 데이터가 담긴 DTO
     * @param id - 수정할 게시글의 고유 ID
     * @return - 수정된 게시글의 엔티티 객체
     * @throws IllegalArgumentException - 게시글이 존재하지 않을 경우 예외 발생
     */
    @Transactional
    public Article update(UpdateArticleRequestDto updateArticleRequestDto, Long id) {
        Article findArticle = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Article not found: " + id));

        findArticle.update(updateArticleRequestDto.getTitle(), updateArticleRequestDto.getContent());

        return findArticle;
    }

    /**
     * 특정 게시글을 삭제하는 함수
     *
     * @param id - 삭제할 게시글의 고유 ID
     */
    public void delete(Long id) {
        articleRepository.deleteById(id);
    }
}
