package com.system.virus.repositories;

import com.system.virus.entities.VirusFamily;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface VirusFamilyRepository extends CrudRepository<VirusFamily, Long> {

    default List<VirusFamily> findAllVirusFamilies() {
        List<VirusFamily> virusFamilies = new ArrayList<>();
        findAll().forEach(virusFamilies::add);
        return virusFamilies;
    }
}
