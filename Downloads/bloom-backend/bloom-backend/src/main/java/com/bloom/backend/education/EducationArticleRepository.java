package com.bloom.backend.education;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EducationArticleRepository extends JpaRepository<EducationArticle, Long> {
    List<EducationArticle> findByCategory(String category);
}