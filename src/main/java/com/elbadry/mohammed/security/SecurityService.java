package com.elbadry.mohammed.security;

import com.elbadry.mohammed.entities.Client;
import com.elbadry.mohammed.entities.Credit;
import com.elbadry.mohammed.entities.Remboursement;
import com.elbadry.mohammed.entities.Utilisateur;
import com.elbadry.mohammed.repositories.ClientRepository;
import com.elbadry.mohammed.repositories.CreditRepository;
import com.elbadry.mohammed.repositories.RemboursementRepository;
import com.elbadry.mohammed.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final UtilisateurRepository utilisateurRepository;
    private final ClientRepository clientRepository;
    private final CreditRepository creditRepository;
    private final RemboursementRepository remboursementRepository;

    public boolean isClientOwner(Authentication authentication, Long clientId) {
        String username = authentication.getName();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElse(null);
        
        if (utilisateur == null || utilisateur.getClient() == null) {
            return false;
        }
        
        return utilisateur.getClient().getId().equals(clientId);
    }

    public boolean isCreditOwner(Authentication authentication, Long creditId) {
        String username = authentication.getName();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElse(null);
        
        if (utilisateur == null || utilisateur.getClient() == null) {
            return false;
        }
        
        Credit credit = creditRepository.findById(creditId).orElse(null);
        if (credit == null) {
            return false;
        }
        
        return credit.getClient().getId().equals(utilisateur.getClient().getId());
    }

    public boolean isRemboursementOwner(Authentication authentication, Long remboursementId) {
        String username = authentication.getName();
        Utilisateur utilisateur = utilisateurRepository.findByUsername(username).orElse(null);
        
        if (utilisateur == null || utilisateur.getClient() == null) {
            return false;
        }
        
        Remboursement remboursement = remboursementRepository.findById(remboursementId).orElse(null);
        if (remboursement == null || remboursement.getCredit() == null) {
            return false;
        }
        
        return remboursement.getCredit().getClient().getId().equals(utilisateur.getClient().getId());
    }
}
