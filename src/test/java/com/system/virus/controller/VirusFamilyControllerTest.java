package com.system.virus.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.system.virus.entities.VirusFamily;
import com.system.virus.services.VirusFamilyService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;

/**
 * I can add more test like this though, just for demonstration only. :)
 */
@SpringBootTest
@AutoConfigureMockMvc
public class VirusFamilyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VirusFamilyService virusFamilyService;

    @Test
    public void createFamilyVirus() throws Exception {
        VirusFamily family = new VirusFamily("Corona Virus 1", 175.0, 215.0, "DS Linear", true);
        when(virusFamilyService.create(any())).thenReturn(family);
        this.mockMvc.perform(
                post("/family")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"name\": \"Corona Virus 1\",\n" +
                                "    \"sizeMin\": 175.0,\n" +
                                "    \"sizeMax\": 215.0,\n" +
                                "    \"strand\": \"DS Linear\",\n" +
                                "    \"enveloped\": true\n" +
                                "}")
        ).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Corona Virus 1")));
    }
}
