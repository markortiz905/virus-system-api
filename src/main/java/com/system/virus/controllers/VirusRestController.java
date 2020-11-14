package com.system.virus.controllers;

import com.system.virus.exception.BadRequestServiceException;
import com.system.virus.exception.NotFoundServiceException;
import com.system.virus.exception.VirusServiceException;
import com.system.virus.entities.Virus;
import com.system.virus.services.VirusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VirusRestController {

    private static final Logger logger = LogManager.getLogger(VirusFamilyRestController.class);

    @Autowired private VirusService virusService;

    @GetMapping("/virus")
    List<Virus> getAll() throws VirusServiceException {
        logger.info("txnState=start");
        List<Virus> list = virusService.findAllVirus();
        logger.info("txnState=end");
        return list;
    }

    @PostMapping("/virus")
    Virus newVirus(@Valid @RequestBody Virus virus) throws VirusServiceException, BadRequestServiceException {
        logger.info("txnState=start");
        Virus vs = virusService.create(virus);
        logger.info("txnState=end");
        return vs;
    }

    @GetMapping("/virus/{id}")
    Virus getFamily(@PathVariable Long id) throws VirusServiceException, NotFoundServiceException, BadRequestServiceException {
        logger.info("txnState=start");
        Virus vs = virusService.findVirus(id);
        logger.info("txnState=end");
        return vs;
    }

    @PutMapping("/virus/{id}")
    Virus updateFamily(@Valid @RequestBody Virus virus, @PathVariable Long id)
            throws VirusServiceException, BadRequestServiceException {
        logger.info("txnState=start");
        Virus vs = null;
        try{
            vs = virusService.findOptional(id)
                .map(vf -> {
                    vf.setName(virus.getName());
                    vf.setAnimalAffected(virus.getAnimalAffected());
                    vf.setHumanAffected(virus.getHumanAffected());
                    vf.setFamily(virus.getFamily());
                    return virusService.update(vf);
                }).orElseThrow(() -> new NotFoundServiceException("Virus Family"));
        } catch (NotFoundServiceException e) {
            vs = virusService.create(virus);
        }
        logger.info("txnState=end");
        return vs;
    }

    @DeleteMapping("/virus/{id}")
    void deleteEmployee(@PathVariable Long id) throws VirusServiceException, NotFoundServiceException {
        logger.info("txnState=start");
        virusService.delete(id);
        logger.info("txnState=end");
    }

}
