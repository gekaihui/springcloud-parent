package com.gkh.springboot.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name="GKH_USER")
public class User implements Serializable {

    @Id
    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select SEQ_USER_ID.nextval from dual")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "AGE")
    private String age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }
}