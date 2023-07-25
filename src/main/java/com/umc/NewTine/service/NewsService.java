package com.umc.NewTine.service;

import com.umc.NewTine.domain.news.NewsRepository;
import com.umc.NewTine.domain.user.User;
import com.umc.NewTine.domain.user.UserRepository;
import com.umc.NewTine.dto.news.response.NewsRecentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public List<NewsRecentResponse> getRecentNews(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);

        return newsRepository.findByIdOrderByDateDesc(userId);
    }

}
