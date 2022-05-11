package com.example.materialproject.services;

import com.example.materialproject.Repository.UsersDao;

import com.example.materialproject.pojo.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private final UsersDao usersRepository;

    public MyUserDetailsService(UsersDao usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= usersRepository.findUsersByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }

        return user;
    }
}
