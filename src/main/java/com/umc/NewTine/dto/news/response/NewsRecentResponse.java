package com.umc.NewTine.dto.news.response;

import com.umc.NewTine.domain.news.News;

public class NewsRecentResponse {
    private long id;
    private String title;
    private long press_id;

    public NewsRecentResponse(News news) {
        this.id = news.getId();
        this.title = news.getTitle();
        this.press_id = news.getPress_id();

    }
}
