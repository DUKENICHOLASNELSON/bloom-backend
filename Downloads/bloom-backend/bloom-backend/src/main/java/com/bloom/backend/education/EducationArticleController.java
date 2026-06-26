package com.bloom.backend.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education")
public class EducationArticleController {

    @Autowired
    private EducationArticleService articleService;

    @PostMapping
    public ResponseEntity<EducationArticle> createArticle(@RequestBody EducationArticle article) {
        return ResponseEntity.ok(articleService.createArticle(article));
    }

    @GetMapping
    public ResponseEntity<List<EducationArticle>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<EducationArticle>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(articleService.getArticlesByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationArticle> getById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}