package com.system.virus.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.system.virus.exception.NotFoundServiceException;
import com.system.virus.exception.VirusServiceException;
import com.system.virus.entities.Virus;
import com.system.virus.entities.VirusFamily;
import com.system.virus.services.VirusFamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class VirusDeserializer extends JsonDeserializer<Virus> {

    @Autowired private VirusFamilyService  virusFamilyService;

    @Override
    public Virus deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("name").asText();
        Boolean animalAffected = node.get("animalAffected").asBoolean();
        Boolean humanAffected = node.get("humanAffected").asBoolean();
        String family_id = node.get("family_id").asText();
        VirusFamily family = null;
        try {
            family = virusFamilyService.findVirusFamily(Long.valueOf(family_id));
        } catch (VirusServiceException | NotFoundServiceException e)  {
            throw new IOException("Virus Family not found using id " + family_id, e);
        }
        return new Virus(name, humanAffected, animalAffected, family);
    }
}
