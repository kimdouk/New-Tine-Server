package com.umc.NewTine.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umc.NewTine.config.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user")
public class User extends BaseEntity{
    @Id @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String user_id;


    @Column(columnDefinition = "TEXT")
    private String image;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String provider; //어떤 OAuth인지

    @Column
    private String providerId;

    @Builder
    public User(Long id, String user_id, String image, Role role, String password, String provider, String providerId) {
        this.id = id;
        this.user_id = user_id;
        this.image = image;
        this.role = role;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }

    public User update(String username, String image){
        this.username = username;
        this.image = image;

        return this;
    }
}