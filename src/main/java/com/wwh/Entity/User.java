package com.wwh.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String nickname;

    private String userName;

    private String password;

    private String email;
}
