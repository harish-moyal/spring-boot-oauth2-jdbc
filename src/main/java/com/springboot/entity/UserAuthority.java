package com.springboot.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by harishmoyal on 30/05/17.
 */
@Entity
@Table(name = "authorities")
public class UserAuthority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id", updatable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "authority")
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserAuthority(){
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public UserAuthority(String username, String authority, User user) {
        this.username = username;
        this.authority = authority;
        this.user = user;
    }
}
