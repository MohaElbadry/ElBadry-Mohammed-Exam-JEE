package com.elbadry.mohammed.repositories;

import com.elbadry.mohammed.entities.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
    List<CreditProfessionnel> findByRaisonSocialeContaining(String raisonSociale);
}
