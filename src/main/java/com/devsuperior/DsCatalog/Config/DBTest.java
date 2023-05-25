package com.devsuperior.DsCatalog.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devsuperior.DsCatalog.services.DbService;

@Configuration
@Profile("test")
public class DBTest {

    @Autowired
    private DbService dbService;

    @Bean
    public void instanciaDB() {
        this.dbService.instaciaDados();
    }

}
