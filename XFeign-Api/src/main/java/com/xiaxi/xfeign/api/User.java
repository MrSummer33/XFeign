package com.xiaxi.xfeign.api;

import lombok.Data;

/**
 * Created by BaoCai on 18/1/20.
 * Description:
 */
@Data
public class User {

    private String name;

    private int age;

    public User(){}

    public User(String name , int age){
        this.age = age;
        this.name = name;
    }

}
