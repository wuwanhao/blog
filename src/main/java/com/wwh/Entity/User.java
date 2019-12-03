package com.wwh.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "t_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

//    @Column(nullable = false)
//    private String nickname;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private String avatar;

    //用户类型
    private int type;

    //角色
    @Column
    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

    //时间戳
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //时间戳
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Blog> blogs;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
