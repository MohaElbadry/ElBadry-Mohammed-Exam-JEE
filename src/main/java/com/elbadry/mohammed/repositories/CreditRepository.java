package com.elbadry.mohammed.repositories;

import com.elbadry.mohammed.entities.Credit;
import com.elbadry.mohammed.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClientId(Long clientId);
    List<Credit> findByStatut(StatutCredit statut);
}
