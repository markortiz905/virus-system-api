package com.system.virus.repositories;

import com.system.virus.entities.Virus;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface VirusRepository extends CrudRepository<Virus, Long> {

    default List<Virus> findAllVirusList() {
        List<Virus> virusList = new ArrayList<>();
        findAll().forEach(virusList::add);
        return virusList;
    }
}
