package com.bloom.backend.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EducationArticleService {

    @Autowired
    private EducationArticleRepository articleRepository;

    public EducationArticle createArticle(EducationArticle article) {
        return articleRepository.save(article);
    }

    public List<EducationArticle> getAllArticles() {
        return articleRepository.findAll();
    }

    public List<EducationArticle> getArticlesByCategory(String category) {
        return articleRepository.findByCategory(category);
    }

    public Optional<EducationArticle> getArticleById(Long id) {
        return articleRepository.findById(id);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}