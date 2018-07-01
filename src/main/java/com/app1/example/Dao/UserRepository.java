package com.app1.example.Dao;

import com.app1.example.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByLogin(String login);
}
