package com.umc.NewTine.domain.usernewshistory;

import com.umc.NewTine.dto.news.response.NewsRecentResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserNewsHistoryRepository extends JpaRepository<UserNewsHistory, Long> {
    Optional<List<Long>> findNewsIdsByUserIdOrderByViewdAtdesc(Long userId);
}
