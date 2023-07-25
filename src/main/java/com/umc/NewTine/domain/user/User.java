package com.umc.NewTine.domain.user;

//import com.umc.NewTine.domain.category.Category;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private String name;
//    private List<Category> categories = new ArrayList<>();
}
