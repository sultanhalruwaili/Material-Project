package com.example.materialproject.Config;

import com.example.materialproject.Repository.MaterialDao;
import com.example.materialproject.pojo.Material;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaterialConfig {

    @Bean
    CommandLineRunner commandLineRunner(MaterialDao repository){
        return args -> {
            Material m1 =new Material(
                    1,
                  "24-11-2019",
                  "English",
                  "رياضي"  ,
                    "http://alhilal.hfc.sa",
                    "Salem Aldossari",
                    "in alhilal youtube channel",
                    "خلط",
                    "خلط النصر في كل مواجهاته"
            );
            repository.save(m1);
        };
    }
}
