package com.elbadry.mohammed.entities;

import com.elbadry.mohammed.enums.StatutCredit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Credit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dateDemande;
    @Enumerated(EnumType.STRING)
    private StatutCredit statut;
    @Temporal(TemporalType.DATE)
    private Date dateAcceptation;
    private Double montant;
    private Integer duree;
    private Double tauxInteret;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Remboursement> remboursements = new ArrayList<>();
}
