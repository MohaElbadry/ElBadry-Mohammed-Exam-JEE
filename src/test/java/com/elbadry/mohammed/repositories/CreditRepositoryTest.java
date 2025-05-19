package com.elbadry.mohammed.repositories;

import com.elbadry.mohammed.entities.Client;
import com.elbadry.mohammed.entities.CreditPersonnel;
import com.elbadry.mohammed.enums.StatutCredit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CreditRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private CreditPersonnelRepository creditPersonnelRepository;

    @Test
    public void testFindByClientId() {
        // Create a client
        Client client = new Client();
        client.setNom("Test Client");
        client.setEmail("test@example.com");
        clientRepository.save(client);

        // Create a credit
        CreditPersonnel credit = new CreditPersonnel();
        credit.setClient(client);
        credit.setDateDemande(new Date());
        credit.setStatut(StatutCredit.EN_COURS);
        credit.setMontant(5000.0);
        credit.setDuree(12);
        credit.setTauxInteret(5.0);
        credit.setMotif("Test");
        creditPersonnelRepository.save(credit);

        // Test findByClientId
        List<com.elbadry.mohammed.entities.Credit> credits = creditRepository.findByClientId(client.getId());
        assertThat(credits).isNotEmpty();
        assertThat(credits.size()).isEqualTo(1);
        assertThat(credits.get(0).getClient().getId()).isEqualTo(client.getId());
    }

    @Test
    public void testFindByStatut() {
        // Create a client
        Client client = new Client();
        client.setNom("Test Client");
        client.setEmail("test@example.com");
        clientRepository.save(client);

        // Create a credit
        CreditPersonnel credit = new CreditPersonnel();
        credit.setClient(client);
        credit.setDateDemande(new Date());
        credit.setStatut(StatutCredit.ACCEPTE);
        credit.setDateAcceptation(new Date());
        credit.setMontant(5000.0);
        credit.setDuree(12);
        credit.setTauxInteret(5.0);
        credit.setMotif("Test");
        creditPersonnelRepository.save(credit);

        // Test findByStatut
        List<com.elbadry.mohammed.entities.Credit> credits = creditRepository.findByStatut(StatutCredit.ACCEPTE);
        assertThat(credits).isNotEmpty();
        assertThat(credits.size()).isEqualTo(1);
        assertThat(credits.get(0).getStatut()).isEqualTo(StatutCredit.ACCEPTE);
    }
}
