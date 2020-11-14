package com.system.virus.repositories;


import com.system.virus.entities.Virus;
import com.system.virus.entities.VirusFamily;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Ideally I can create more test but this is time limited and was not fully indicated from the spec
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
//@ActiveProfiles({"dev", "integration"})
public class VirusRepositoryTest {

    @Autowired
    private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;
    @Autowired private VirusRepository virusRepository;
    @Autowired private VirusFamilyRepository virusFamilyRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(virusRepository).isNotNull();
    }


    @Test
    void createTest() {
        VirusFamily family = new VirusFamily("Test Virus", 1.0, 1.0, "test", false);
        family = virusFamilyRepository.save(family);
        assertThat(virusFamilyRepository.findById(family.getId()).isPresent()).isTrue();
        Virus virus = new Virus("Test Virus", false, true, family);
        virus = virusRepository.save(virus);
        assertThat(virusRepository.findById(virus.getId()).isPresent()).isTrue();
    }
}
