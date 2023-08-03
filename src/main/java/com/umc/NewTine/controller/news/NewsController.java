package com.umc.NewTine.controller.news;

import com.umc.NewTine.dto.news.request.NewsRecentRequest;
import com.umc.NewTine.dto.news.response.NewsRankingResponse;
import com.umc.NewTine.dto.news.response.NewsRecentResponse;
import com.umc.NewTine.service.news.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news/{userId}/recent")
    public List<NewsRecentResponse> getRecentNews(@PathVariable Long userId) {
        return newsService.getRecentNews(userId);
    }

    @GetMapping("/news/ranking")
    public List<NewsRankingResponse> getRankingNews() {
        return newsService.getRankingNews();
    }

    @PostMapping("/news")
    public void saveRecentViewTime(@RequestBody NewsRecentRequest request) {
        newsService.saveRecentViewTime(request);
    }

}
