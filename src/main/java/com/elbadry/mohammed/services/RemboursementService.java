package com.elbadry.mohammed.services;

import com.elbadry.mohammed.dtos.RemboursementDTO;
import com.elbadry.mohammed.enums.TypeRemboursement;

import java.util.Date;
import java.util.List;

public interface RemboursementService {
    List<RemboursementDTO> getAllRemboursements();
    RemboursementDTO getRemboursementById(Long id);
    List<RemboursementDTO> getRemboursementsByCreditId(Long creditId);
    List<RemboursementDTO> getRemboursementsByType(TypeRemboursement type);
    List<RemboursementDTO> getRemboursementsByDateRange(Date dateDebut, Date dateFin);
    RemboursementDTO createRemboursement(RemboursementDTO remboursementDTO);
    RemboursementDTO updateRemboursement(Long id, RemboursementDTO remboursementDTO);
    void deleteRemboursement(Long id);
}
