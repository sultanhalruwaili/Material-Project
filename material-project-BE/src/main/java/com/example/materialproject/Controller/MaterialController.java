package com.example.materialproject.Controller;

import com.example.materialproject.pojo.Material;
import com.example.materialproject.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class MaterialController {

    private MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    //api to fetch data from DB
    @GetMapping(path = "api/material")
    public List<Material> getMaterials() {
        return materialService.getMaterials();
    }

    //api to fetch data by title from DB
    @GetMapping(path = "api/material/{title}")
    public List<Material> getMaterialByTitle(@PathVariable String title) {
        System.out.println(title);
        return materialService.getMaterialByTitle(title);
    }

    //api to fetch data by id from DB
    @GetMapping(path = "api/materialById/{id}")
    public Optional<Material> getMaterialByTitle(@PathVariable Integer id) {
        return materialService.getMaterial(id);
    }
    //api to send data to DB
    @PostMapping("api/material/add")
    public void registerNewMaterial(@RequestBody Material material) {

        materialService.addNewMaterial(material);
//        if(  getFileExtension(material.getAttachments()).equals("pdf") || getFileExtension(material.getAttachments()).equals("png")){
//            materialService.addNewMaterial(material);
//        }

    }

    //api to delelte data by id from DB
    @DeleteMapping(path = "api/material/delete/{id}")
    public void deleteStudent(@PathVariable("id") Integer id) {
        materialService.deleteMaterial(id);
    }

    //Method to Find the File Extension
    private static String getFileExtension(String fileName ) {
        if (fileName.lastIndexOf('.') != -1 && fileName.lastIndexOf('.') != 0) {
            return fileName.substring(fileName.lastIndexOf('.') + 1);
        } else {
            return "File don't have extension";
        }
    }


}
