package com.elbadry.mohammed.controllers;

import com.elbadry.mohammed.dtos.CreditDTO;
import com.elbadry.mohammed.dtos.CreditPersonnelDTO;
import com.elbadry.mohammed.enums.StatutCredit;
import com.elbadry.mohammed.security.JwtTokenUtil;
import com.elbadry.mohammed.security.SecurityService;
import com.elbadry.mohammed.services.CreditService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CreditController.class)
public class CreditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreditService creditService;

    @MockBean
    private SecurityService securityService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    private CreditDTO creditDTO;
    private CreditPersonnelDTO creditPersonnelDTO;

    @BeforeEach
    public void setup() {
        creditDTO = new CreditDTO();
        creditDTO.setId(1L);
        creditDTO.setDateDemande(new Date());
        creditDTO.setStatut(StatutCredit.EN_COURS);
        creditDTO.setMontant(10000.0);
        creditDTO.setDuree(12);
        creditDTO.setTauxInteret(5.0);
        creditDTO.setClientId(1L);
        creditDTO.setType("PERSONNEL");

        creditPersonnelDTO = new CreditPersonnelDTO();
        creditPersonnelDTO.setId(1L);
        creditPersonnelDTO.setDateDemande(new Date());
        creditPersonnelDTO.setStatut(StatutCredit.EN_COURS);
        creditPersonnelDTO.setMontant(10000.0);
        creditPersonnelDTO.setDuree(12);
        creditPersonnelDTO.setTauxInteret(5.0);
        creditPersonnelDTO.setClientId(1L);
        creditPersonnelDTO.setType("PERSONNEL");
        creditPersonnelDTO.setMotif("Achat de voiture");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetAllCredits() throws Exception {
        List<CreditDTO> credits = Arrays.asList(creditDTO);
        when(creditService.getAllCredits()).thenReturn(credits);

        mockMvc.perform(get("/api/credits")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].montant", is(10000.0)))
                .andExpect(jsonPath("$[0].duree", is(12)))
                .andExpect(jsonPath("$[0].tauxInteret", is(5.0)))
                .andExpect(jsonPath("$[0].clientId", is(1)))
                .andExpect(jsonPath("$[0].type", is("PERSONNEL")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetCreditById() throws Exception {
        when(creditService.getCreditById(1L)).thenReturn(creditDTO);

        mockMvc.perform(get("/api/credits/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.montant", is(10000.0)))
                .andExpect(jsonPath("$.duree", is(12)))
                .andExpect(jsonPath("$.tauxInteret", is(5.0)))
                .andExpect(jsonPath("$.clientId", is(1)))
                .andExpect(jsonPath("$.type", is("PERSONNEL")));
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    public void testCreateCreditPersonnel() throws Exception {
        when(creditService.createCreditPersonnel(any(CreditPersonnelDTO.class))).thenReturn(creditPersonnelDTO);

        mockMvc.perform(post("/api/credits/personnel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(creditPersonnelDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.montant", is(10000.0)))
                .andExpect(jsonPath("$.duree", is(12)))
                .andExpect(jsonPath("$.tauxInteret", is(5.0)))
                .andExpect(jsonPath("$.clientId", is(1)))
                .andExpect(jsonPath("$.type", is("PERSONNEL")))
                .andExpect(jsonPath("$.motif", is("Achat de voiture")));
    }

    @Test
    @WithMockUser(roles = "EMPLOYE")
    public void testUpdateCreditStatut() throws Exception {
        when(creditService.updateCreditStatut(eq(1L), any(StatutCredit.class))).thenReturn(creditDTO);

        mockMvc.perform(patch("/api/credits/1/statut")
                .param("statut", "ACCEPTE")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.montant", is(10000.0)))
                .andExpect(jsonPath("$.duree", is(12)))
                .andExpect(jsonPath("$.tauxInteret", is(5.0)))
                .andExpect(jsonPath("$.clientId", is(1)))
                .andExpect(jsonPath("$.type", is("PERSONNEL")));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteCredit() throws Exception {
        mockMvc.perform(delete("/api/credits/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
