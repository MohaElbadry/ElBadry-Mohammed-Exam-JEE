package com.elbadry.mohammed.repositories;

import com.elbadry.mohammed.entities.Remboursement;
import com.elbadry.mohammed.enums.TypeRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCreditId(Long creditId);
    List<Remboursement> findByType(TypeRemboursement type);
    List<Remboursement> findByDateBetween(Date dateDebut, Date dateFin);
}
