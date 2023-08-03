package com.umc.NewTine.domain.user;

//import com.umc.NewTine.domain.category.Category;

import com.umc.NewTine.domain.usernewshistory.UserNewsHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;
//    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserNewsHistory> userNewsHistories;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
