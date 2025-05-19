package com.elbadry.mohammed.services;

import com.elbadry.mohammed.dtos.CreditDTO;
import com.elbadry.mohammed.dtos.CreditImmobilierDTO;
import com.elbadry.mohammed.dtos.CreditPersonnelDTO;
import com.elbadry.mohammed.dtos.CreditProfessionnelDTO;
import com.elbadry.mohammed.enums.StatutCredit;

import java.util.List;

public interface CreditService {
    List<CreditDTO> getAllCredits();
    CreditDTO getCreditById(Long id);
    List<CreditDTO> getCreditsByClientId(Long clientId);
    List<CreditDTO> getCreditsByStatut(StatutCredit statut);
    
    CreditPersonnelDTO createCreditPersonnel(CreditPersonnelDTO creditDTO);
    CreditImmobilierDTO createCreditImmobilier(CreditImmobilierDTO creditDTO);
    CreditProfessionnelDTO createCreditProfessionnel(CreditProfessionnelDTO creditDTO);
    
    CreditDTO updateCreditStatut(Long id, StatutCredit statut);
    void deleteCredit(Long id);
}
