package com.elbadry.mohammed.repositories;

import com.elbadry.mohammed.entities.CreditImmobilier;
import com.elbadry.mohammed.enums.TypeBien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByTypeBien(TypeBien typeBien);
}
