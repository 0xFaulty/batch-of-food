package com.comand.foodhack.dao;


import com.comand.foodhack.entity.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    List<User> getUsers();

}
