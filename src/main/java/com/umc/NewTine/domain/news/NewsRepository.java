package com.umc.NewTine.domain.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findById(Long id);

    Optional<List<News>> findAllByOrderByViewsDesc();

//    Optional<List<News>> findByTitleContaining(String word);
    @Query("SELECT n FROM News n WHERE n.title LIKE %:word%")
    Optional<List<News>> findNewsByTitleContaining(@Param("word") String word);
}

