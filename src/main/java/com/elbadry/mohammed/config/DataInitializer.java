package com.elbadry.mohammed.config;

import com.elbadry.mohammed.entities.*;
import com.elbadry.mohammed.enums.Role;
import com.elbadry.mohammed.enums.StatutCredit;
import com.elbadry.mohammed.enums.TypeBien;
import com.elbadry.mohammed.enums.TypeRemboursement;
import com.elbadry.mohammed.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final RemboursementRepository remboursementRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create clients
        Client client1 = new Client();
        client1.setNom("John Doe");
        client1.setEmail("john.doe@example.com");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setNom("Jane Smith");
        client2.setEmail("jane.smith@example.com");
        clientRepository.save(client2);

        // Create users
        Utilisateur admin = new Utilisateur();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(Role.ROLE_ADMIN);
        admin.setRoles(adminRoles);
        utilisateurRepository.save(admin);

        Utilisateur employe = new Utilisateur();
        employe.setUsername("employe");
        employe.setPassword(passwordEncoder.encode("employe"));
        Set<Role> employeRoles = new HashSet<>();
        employeRoles.add(Role.ROLE_EMPLOYE);
        employe.setRoles(employeRoles);
        utilisateurRepository.save(employe);

        Utilisateur user1 = new Utilisateur();
        user1.setUsername("user1");
        user1.setPassword(passwordEncoder.encode("user1"));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(Role.ROLE_CLIENT);
        user1.setRoles(userRoles);
        user1.setClient(client1);
        utilisateurRepository.save(user1);

        Utilisateur user2 = new Utilisateur();
        user2.setUsername("user2");
        user2.setPassword(passwordEncoder.encode("user2"));
        user2.setRoles(userRoles);
        user2.setClient(client2);
        utilisateurRepository.save(user2);

        // Create credits
        CreditPersonnel creditPersonnel = new CreditPersonnel();
        creditPersonnel.setClient(client1);
        creditPersonnel.setDateDemande(new Date());
        creditPersonnel.setStatut(StatutCredit.EN_COURS);
        creditPersonnel.setMontant(10000.0);
        creditPersonnel.setDuree(12);
        creditPersonnel.setTauxInteret(5.0);
        creditPersonnel.setMotif("Achat de voiture");
        creditPersonnelRepository.save(creditPersonnel);

        CreditImmobilier creditImmobilier = new CreditImmobilier();
        creditImmobilier.setClient(client1);
        creditImmobilier.setDateDemande(new Date());
        creditImmobilier.setStatut(StatutCredit.ACCEPTE);
        creditImmobilier.setDateAcceptation(new Date());
        creditImmobilier.setMontant(200000.0);
        creditImmobilier.setDuree(240);
        creditImmobilier.setTauxInteret(2.5);
        creditImmobilier.setTypeBien(TypeBien.APPARTEMENT);
        creditImmobilierRepository.save(creditImmobilier);

        CreditProfessionnel creditProfessionnel = new CreditProfessionnel();
        creditProfessionnel.setClient(client2);
        creditProfessionnel.setDateDemande(new Date());
        creditProfessionnel.setStatut(StatutCredit.REJETE);
        creditProfessionnel.setMontant(50000.0);
        creditProfessionnel.setDuree(36);
        creditProfessionnel.setTauxInteret(4.0);
        creditProfessionnel.setMotif("Expansion d'entreprise");
        creditProfessionnel.setRaisonSociale("Smith Enterprises");
        creditProfessionnelRepository.save(creditProfessionnel);

        // Create remboursements
        Remboursement remboursement1 = new Remboursement();
        remboursement1.setCredit(creditImmobilier);
        remboursement1.setDate(new Date());
        remboursement1.setMontant(1000.0);
        remboursement1.setType(TypeRemboursement.MENSUALITE);
        remboursementRepository.save(remboursement1);

        Remboursement remboursement2 = new Remboursement();
        remboursement2.setCredit(creditImmobilier);
        remboursement2.setDate(new Date());
        remboursement2.setMontant(5000.0);
        remboursement2.setType(TypeRemboursement.REMBOURSEMENT_ANTICIPE);
        remboursementRepository.save(remboursement2);
    }
}
