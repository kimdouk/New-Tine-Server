package com.umc.NewTine.domain.user;

//import com.umc.NewTine.domain.category.Category;

import com.umc.NewTine.domain.usernewshistory.UserNewsHistory;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;

    @OneToMany(mappedBy = "user")
    private List<UserNewsHistory> userNewsHistories;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}