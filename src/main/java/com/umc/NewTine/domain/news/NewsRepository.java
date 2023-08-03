package com.umc.NewTine.domain.news;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    Optional<News> findById(Long id);

}
