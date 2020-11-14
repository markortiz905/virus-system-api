package com.system.virus.services;

import com.system.virus.exception.BadRequestServiceException;
import com.system.virus.exception.NotFoundServiceException;
import com.system.virus.exception.VirusServiceException;
import com.system.virus.entities.VirusFamily;
import com.system.virus.repositories.VirusFamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VirusFamilyService {
    @Autowired private VirusFamilyRepository virusFamilyRepository;

    public VirusFamily create(String name, Double sizeMin, Double sizeMax,
                          String strand, Boolean enveloped) throws VirusServiceException, BadRequestServiceException {
        try {
            return virusFamilyRepository.save(new VirusFamily(name, sizeMin, sizeMax, strand, enveloped));
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestServiceException(e, "Request Not Completed");
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }

    public VirusFamily create(VirusFamily virusFamily) throws VirusServiceException, BadRequestServiceException {
        return create(virusFamily.getName(), virusFamily.getSizeMin(), virusFamily.getSizeMax(),
                virusFamily.getStrand(), virusFamily.getEnveloped());
    }

    public VirusFamily update(Long id, String name, Double sizeMin, Double sizeMax,
                          String strand, Boolean enveloped)
            throws VirusServiceException, NotFoundServiceException, BadRequestServiceException {
        try {
            VirusFamily family = virusFamilyRepository.findById(id).get();
            family.setName(name);
            family.setSizeMin(sizeMin);
            family.setSizeMax(sizeMax);
            family.setStrand(strand);
            family.setEnveloped(enveloped);
            return virusFamilyRepository.save(family);
        } catch (DataIntegrityViolationException e) {
            throw new BadRequestServiceException(e, "Request Not Completed");
        } catch (NoSuchElementException e) {
            throw new NotFoundServiceException(e, "Virus Family not found using id " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }

    public VirusFamily update(VirusFamily virusFamily) {
        return virusFamilyRepository.save(virusFamily);
    }

    public boolean delete(Long id) throws VirusServiceException, NotFoundServiceException {
        try {
            virusFamilyRepository.delete(virusFamilyRepository.findById(id).get());
        } catch (NoSuchElementException e) {
            throw new NotFoundServiceException(e, "Virus Family not found using id " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
        return true;
    }

    public VirusFamily findVirusFamily(Long id) throws VirusServiceException, NotFoundServiceException {
        try {
            return virusFamilyRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            throw new NotFoundServiceException(e, "Virus Family not found using id " + id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }

    public Optional<VirusFamily> findOptional(Long id) {
        return virusFamilyRepository.findById(id);
    }

    public List<VirusFamily> findAllVirusFamilies() throws VirusServiceException {
        try {
            return virusFamilyRepository.findAllVirusFamilies();
        } catch (Exception e) {
            e.printStackTrace();
            throw new VirusServiceException(e, "Failed to create Virus.");
        }
    }
}
