package com.umc.NewTine.service.news;

import com.umc.NewTine.domain.news.News;
import com.umc.NewTine.domain.news.NewsRepository;
import com.umc.NewTine.domain.user.User;
import com.umc.NewTine.domain.user.UserRepository;
import com.umc.NewTine.domain.usernewshistory.UserNewsHistory;
import com.umc.NewTine.domain.usernewshistory.UserNewsHistoryRepository;
import com.umc.NewTine.dto.base.BaseException;
import com.umc.NewTine.dto.base.BaseResponse;
import com.umc.NewTine.dto.news.request.NewsRecentRequest;
import com.umc.NewTine.dto.news.response.NewsRankingResponse;
import com.umc.NewTine.dto.news.response.NewsRecentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.umc.NewTine.dto.base.BaseResponseStatus.NO_NEWS_YET;
import static com.umc.NewTine.dto.base.BaseResponseStatus.NO_USER_ID;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    private final UserNewsHistoryRepository userNewsHistoryRepository;

    public NewsService(NewsRepository newsRepository,
                       UserRepository userRepository,
                       UserNewsHistoryRepository userNewsHistoryRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
        this.userNewsHistoryRepository = userNewsHistoryRepository;
    }

    @Transactional
    public List<NewsRecentResponse> getRecentNews(Long userId) throws BaseException {

        User user = userRepository.findById(userId)
                .orElseThrow(()->new BaseException(NO_USER_ID));
        List<News> newsList = userNewsHistoryRepository.findNewsByUserOrderByRecentViewTimeDesc(user)
                .orElse(List.of());
        return newsList.stream()
                .map(NewsRecentResponse::new)
                .limit(10)
                .collect(Collectors.toList());

    }

    @Transactional
    public List<NewsRankingResponse> getRankingNews() throws BaseException {
        List<News> newsList = newsRepository.findAllByOrderByViewsDesc()
                .orElse(List.of());
        return newsList.stream()
                .map(NewsRankingResponse::new)
                .limit(3)
                .collect(Collectors.toList());
    }


    @Transactional
    public boolean saveRecentViewTime(NewsRecentRequest request) throws BaseException{
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new BaseException(NO_USER_ID));
        News news = newsRepository.findById(request.getNewsId())
                .orElseThrow(()->new BaseException(NO_NEWS_YET));
        LocalDateTime recentViewTime = LocalDateTime.now();
        boolean isDuplicate = userNewsHistoryRepository.existsByUserAndNewsAndRecentViewTimeBetween(user, news, recentViewTime.minusMinutes(1), recentViewTime);

        if (!isDuplicate) {
            news.setViews(news.getViews() + 1);
        }

        UserNewsHistory userNewsHistory = userNewsHistoryRepository.findByUserAndNews(user, news)
                .orElseGet(() -> new UserNewsHistory(user, news, recentViewTime));
        userNewsHistory.setRecentViewTime(recentViewTime);
        userNewsHistoryRepository.save(userNewsHistory);
        return true;




    }


}