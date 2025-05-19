package com.elbadry.mohammed.dtos;

import com.elbadry.mohammed.enums.StatutCredit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {
    private Long id;
    private Date dateDemande;
    private StatutCredit statut;
    private Date dateAcceptation;
    private Double montant;
    private Integer duree;
    private Double tauxInteret;
    private Long clientId;
    private List<RemboursementDTO> remboursements;
    private String type;
}
