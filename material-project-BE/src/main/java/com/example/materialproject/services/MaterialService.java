package com.example.materialproject.services;

import com.example.materialproject.Repository.MaterialDao;
import com.example.materialproject.pojo.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.util.List;
import java.util.Optional;


@Service
public class MaterialService {

    private MaterialDao materialDao;
    @Value("${file}")
    private String img ;
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
        Material m = materialDao.save(material);


    }
//    public void addNewMaterial(Material material , MultipartFile file) {
//        Material m = materialDao.save(material);
//        try {
//            file.transferTo(new File(img + "//" +file.getOriginalFilename()));            //upload/1649098586898_Week 1 content.pdf
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//    }

    public void upDateMaterial( Material newMaterial){
         materialDao.findById(newMaterial.getId())
                .map(material -> {
                    material.setMonitorDate(newMaterial.getMonitorDate());
                    material.setSection(newMaterial.getSection());
                    material.setLanguage(newMaterial.getLanguage());
                    material.setAttachments(newMaterial.getAttachments());
                    material.setContent(newMaterial.getContent());
                    material.setAuthor(newMaterial.getAuthor());
                    material.setTitle(newMaterial.getTitle());
                    material.setUrl(newMaterial.getUrl());

                    return materialDao.save(material);
                })
                .orElseGet(() -> {
                    newMaterial.setId(newMaterial.getId());
                    return materialDao.save(newMaterial);
                });
    }

    public void deleteMaterial(Integer id) {
        materialDao.deleteById(id);
    }
}
