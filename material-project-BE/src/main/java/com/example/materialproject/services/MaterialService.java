package com.example.materialproject.services;

import com.example.materialproject.Repository.MaterialDao;
import com.example.materialproject.pojo.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MaterialService {

    private MaterialDao materialDao;

    @Autowired
    public MaterialService(MaterialDao materialDao) {
        this.materialDao = materialDao;
    }

    public List<Material> getMaterials() {
        return materialDao.findAll();
    }
    public List<Material> getMaterialByTitle(String title) {
        System.out.println(title);
        return materialDao.findByTitle(title);
    }

    public Optional<Material> getMaterial(Integer id ) {
        return materialDao.findById(id );
    }

    public void addNewMaterial(Material material) {
        materialDao.save(material);
    }

    public void deleteMaterial(Integer id) {
        materialDao.deleteById(id);
    }
}
