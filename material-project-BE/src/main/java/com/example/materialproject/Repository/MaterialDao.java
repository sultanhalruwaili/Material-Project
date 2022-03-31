package com.example.materialproject.Repository;

import com.example.materialproject.pojo.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialDao extends JpaRepository<Material,Integer> {


    public List<Material> findByTitle(String title);

}
