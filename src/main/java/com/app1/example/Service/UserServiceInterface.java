package com.app1.example.Service;

import com.app1.example.Entity.User;

public interface UserServiceInterface {
    User getByLogin(String login);
}
