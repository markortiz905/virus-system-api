package com.system.virus.services;

import com.system.virus.exception.BadRequestServiceException;
import com.system.virus.exception.NotFoundServiceException;
import com.system.virus.exception.VirusServiceException;
import com.system.virus.entities.Virus;
import com.system.virus.entities.VirusFamily;
import com.system.virus.repositories.VirusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VirusService {
    @Autowired private VirusFamilyService virusFamilyService;
    @Autowired private VirusRepository virusRepository;

    public Virus create(String name, Boolean human_affected,
                        Boolean animal_affected, Long family_id)
            throws VirusServiceException, BadRequestServiceException {
        try {
            VirusFamily family = virusFamilyService.findVirusFamily(family_id);
            return virusRepository.save(new Virus(name, human_affected, animal_affected, family));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestServiceException(e, "Request Not Completed");
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }

    public Virus create(Virus virus) throws VirusServiceException, BadRequestServiceException {
        try {
            return virusRepository.save(virus);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestServiceException(e, "Request Not Completed");
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }

    public Virus update(Long id, String name, Boolean human_affected,
                              Boolean animal_affected, Long family_id)
            throws VirusServiceException, NotFoundServiceException, BadRequestServiceException {
        try {
            VirusFamily family = virusFamilyService.findVirusFamily(family_id);
            Virus virus = virusRepository.findById(id).get();
            virus.setName(name);
            virus.setHumanAffected(human_affected);
            virus.setAnimalAffected(animal_affected);
            virus.setFamily(family);
            return virusRepository.save(virus);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestServiceException(e, "Request Not Completed");
        } catch (NoSuchElementException e) {
            throw new NotFoundServiceException(e, "Virus not found using id " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }

    public Virus update(Virus virus) {
        return virusRepository.save(virus);
    }

    public Optional<Virus> findOptional(Long id) {
        return virusRepository.findById(id);
    }

    public boolean delete(Long id) throws VirusServiceException, NotFoundServiceException {
        try {
            virusRepository.delete(virusRepository.findById(id).get());
        } catch (NoSuchElementException e) {
            throw new NotFoundServiceException(e, "Virus not found using id " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
        return true;
    }

    public Virus findVirus(Long id) throws VirusServiceException,
            NotFoundServiceException, BadRequestServiceException {
        try {
            return virusRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundServiceException(e, "Virus not found using id " + id);
        } catch (ConstraintViolationException e) {
            throw new BadRequestServiceException(e, "Request Not Completed using id " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }

    public List<Virus> findAllVirus() throws VirusServiceException {
        try {
            return virusRepository.findAllVirusList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }
}
