package com.app1.example.Service;

import com.app1.example.Dao.UserRepository;
import com.app1.example.Entity.Role;
import com.app1.example.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login)
    {
        User user = this.userRepository.findByLogin(login);

        Set<GrantedAuthority> ga = new HashSet<>();

        for (Role role : user.getRoles()) {
            ga.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                ga
        );
    }
}
