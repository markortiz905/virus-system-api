package com.system.virus.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.system.virus.entities.Virus;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class VirusSerializer extends JsonSerializer<Virus>{

    @Override
    public void serialize(Virus virus, JsonGenerator jsonGenerator,
                          SerializerProvider serializers) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", virus.getId().toString());
        jsonGenerator.writeStringField("name", virus.getName());
        jsonGenerator.writeStringField("animalAffected", virus.getAnimalAffected().toString());
        jsonGenerator.writeStringField("humanAffected", virus.getHumanAffected().toString());
        jsonGenerator.writeStringField("family_id", virus.getFamily().getId().toString());
        jsonGenerator.writeEndObject();
    }
}
