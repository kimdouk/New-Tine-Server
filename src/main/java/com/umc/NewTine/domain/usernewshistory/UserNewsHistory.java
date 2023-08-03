package com.umc.NewTine.domain.usernewshistory;

import com.sun.istack.NotNull;
import com.umc.NewTine.domain.news.News;
import com.umc.NewTine.domain.user.User;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
public class UserNewsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @NotNull
    @ManyToOne
    private User user;

    @NotNull
    @ManyToOne
    private News news;

    @Column
    private LocalDateTime recentViewTime;

    public UserNewsHistory(User user, News news, LocalDateTime recentViewTime) {
        this.user = user;
        this.news = news;
        this.recentViewTime = recentViewTime;
    }

    public void setRecentViewTime(LocalDateTime recentViewTime) {
        this.recentViewTime = recentViewTime;
    }

}
