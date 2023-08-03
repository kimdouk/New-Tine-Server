package com.umc.NewTine.domain.news;

import com.umc.NewTine.domain.usernewshistory.UserNewsHistory;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;


    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;


    @Column(nullable = false)
    private long category_id;


    @Column(nullable = false)
    private long press_id;


    @Column(nullable = false)
    private int views;


    @OneToMany(mappedBy = "news")
    private List<UserNewsHistory> userNewsHistories;

    public News(Long id, String title, String content, long category_id, long press_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category_id = category_id;
        this.press_id = press_id;
        this.views = 0;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getViews() {
        return views;
    }

    public long getPress_id() {
        return press_id;
    }

    public void setViews(int views) {
        this.views = views;
    }
}