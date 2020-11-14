package com.system.virus.controllers;

import com.system.virus.exception.BadRequestServiceException;
import com.system.virus.exception.NotFoundServiceException;
import com.system.virus.exception.VirusServiceException;
import com.system.virus.entities.VirusFamily;
import com.system.virus.services.VirusFamilyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VirusFamilyRestController {

    private static final Logger logger = LogManager.getLogger(VirusFamilyRestController.class);

    @Autowired private VirusFamilyService virusFamilyService;

    @GetMapping("/family")
    List<VirusFamily> getAll() throws VirusServiceException {
        logger.info("txnState=start");
        List<VirusFamily> list = virusFamilyService.findAllVirusFamilies();
        logger.info("txnState=end");
        return list;
    }

    @PostMapping("/family")
    VirusFamily newVirusFamily(@Valid @RequestBody VirusFamily virusFamily) throws VirusServiceException, BadRequestServiceException {
        logger.info("txnState=start");
        VirusFamily family = virusFamilyService.create(virusFamily);
        logger.info("txnState=end");
        return family;
    }

    @GetMapping("/family/{id}")
    VirusFamily getFamily(@PathVariable Long id) throws VirusServiceException, NotFoundServiceException {
        logger.info("txnState=start");
        VirusFamily family = virusFamilyService.findVirusFamily(id);
        logger.info("txnState=end");
        return family;
    }

    @PutMapping("/family/{id}")
    VirusFamily updateFamily(@Valid @RequestBody VirusFamily virusFamily, @PathVariable Long id)
            throws VirusServiceException, BadRequestServiceException {
        logger.info("txnState=start");
        VirusFamily family = null;
        try{
            family = virusFamilyService.findOptional(id)
                .map(vf -> {
                    vf.setName(virusFamily.getName());
                    vf.setStrand(virusFamily.getStrand());
                    vf.setSizeMin(virusFamily.getSizeMin());
                    vf.setSizeMax(virusFamily.getSizeMax());
                    vf.setEnveloped(virusFamily.getEnveloped());
                    return virusFamilyService.update(vf);
                }).orElseThrow(() -> new NotFoundServiceException("Virus Family"));
        } catch (NotFoundServiceException e) {
            family = virusFamilyService.create(virusFamily);
        }
        logger.info("txnState=end");
        return family;
    }

    @DeleteMapping("/family/{id}")
    void deleteEmployee(@PathVariable Long id) throws VirusServiceException, NotFoundServiceException {
        logger.info("txnState=start");
        virusFamilyService.delete(id);
        logger.info("txnState=end");
    }

}
