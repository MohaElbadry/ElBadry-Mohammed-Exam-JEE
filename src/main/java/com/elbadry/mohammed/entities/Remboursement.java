package com.elbadry.mohammed.entities;

import com.elbadry.mohammed.enums.TypeRemboursement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Remboursement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    private Double montant;
    @Enumerated(EnumType.STRING)
    private TypeRemboursement type;
    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;
}
