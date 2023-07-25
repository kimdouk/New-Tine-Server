package com.umc.NewTine.domain.news;

import com.umc.NewTine.dto.news.response.NewsRecentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<NewsRecentResponse> findByIdOrderByDateDesc(Long userId);
}
