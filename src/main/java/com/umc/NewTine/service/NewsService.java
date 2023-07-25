package com.umc.NewTine.service;

import com.umc.NewTine.domain.news.News;
import com.umc.NewTine.domain.news.NewsRepository;
import com.umc.NewTine.domain.user.User;
import com.umc.NewTine.domain.user.UserRepository;
import com.umc.NewTine.domain.usernewshistory.UserNewsHistoryRepository;
import com.umc.NewTine.dto.news.response.NewsRecentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final UserNewsHistoryRepository userNewsHistoryRepository;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository,UserNewsHistoryRepository userNewsHistoryRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.userNewsHistoryRepository = userNewsHistoryRepository;
    }

    @Transactional
    public List<NewsRecentResponse> getRecentNews(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(IllegalArgumentException::new);
        List<Long> newsIds = userNewsHistoryRepository.findNewsIdsByUserIdOrderByViewdAtdesc(user.getId())
                .orElse(List.of());
        return newsRepository.findAllById(newsIds).stream()
                .map(NewsRecentResponse::new)
                .collect(Collectors.toList());

//        userNewsHistoryRepository.findAllById(userId);
//        newsRepository.findbyId
//        userNewsHistoryRepository.findByIdOrderByDateDesc(user.getId()).stream()
//                .map(u->u.getNews_id())
//                .collect(Collectors.toList());
    }

}
