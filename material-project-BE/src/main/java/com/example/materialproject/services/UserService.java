package com.example.materialproject.services;

import com.example.materialproject.Repository.UsersDao;
import com.example.materialproject.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public List<Users> getUsers() {
        return usersDao.findAll();
    }
    public Optional<Users> getUser(Integer id) {
        return usersDao.findById(id);
    }


    public Users createDefault() {
        Users u = new Users();

        u.setUsername("super");
        u.setFullname("super");
        u.setRole("ADMIN");
        u.setEmail("test@test.com");
        u.setStatus("active");
        u.setPassword( bCryptPasswordEncoder.encode("123456789"));

        usersDao.saveAndFlush(u);
        return u;

    }
    public void addUser(Users users) {
//        String hashedPassword=new BCryptPasswordEncoder().encode(users.getPassword());
//        users.setPassword(hashedPassword);
        if (users.getUsername() != null && users.getEmail() != null && users.getPassword() !=null){
            if (usersDao.findByUsername(users.getUsername()) == null && usersDao.findByEmail(users.getEmail()) == null){
                users.setStatus("ACTIVE");
                usersDao.save(users);
            }
        }

    }
    public void upDateUser( Users newUsers){
//        String hashedPassword=new BCryptPasswordEncoder().encode(newUsers.getPassword());
//        newUsers.setPassword(hashedPassword);
        usersDao.findById(newUsers.getId())
                .map(User -> {
                    User.setFullname(newUsers.getFullname());
                    User.setUsername(newUsers.getUsername());
                    User.setEmail(newUsers.getEmail());
                    User.setPassword(newUsers.getPassword());
                    User.setStatus(newUsers.getStatus());

                    return usersDao.save(User);
                })
                .orElseGet(() -> {
                    newUsers.setId(newUsers.getId());
                    return usersDao.save(newUsers);
                });
    }


    public void deleteUser(Integer id) {
        Users users = usersDao.findById(id).orElse(null);
        users.setStatus("deleted");
        usersDao.save(users);
    }
}