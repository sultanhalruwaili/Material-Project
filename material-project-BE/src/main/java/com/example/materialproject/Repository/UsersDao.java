package com.example.materialproject.Repository;

import com.example.materialproject.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersDao extends JpaRepository<Users,Integer> {

    Object findByUsername(String username);
    Object findByEmail(String email);
    Users findUsersByUsername(String username);

}
