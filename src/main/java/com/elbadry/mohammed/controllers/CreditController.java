package com.elbadry.mohammed.controllers;

import com.elbadry.mohammed.dtos.CreditDTO;
import com.elbadry.mohammed.dtos.CreditImmobilierDTO;
import com.elbadry.mohammed.dtos.CreditPersonnelDTO;
import com.elbadry.mohammed.dtos.CreditProfessionnelDTO;
import com.elbadry.mohammed.enums.StatutCredit;
import com.elbadry.mohammed.services.CreditService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
@Tag(name = "Credits", description = "Credit management API")
@SecurityRequirement(name = "bearerAuth")
public class CreditController {

    private final CreditService creditService;

    @GetMapping
    @Operation(summary = "Get all credits", description = "Get a list of all credits")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<List<CreditDTO>> getAllCredits() {
        return ResponseEntity.ok(creditService.getAllCredits());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get credit by ID", description = "Get a credit by its ID")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE') or @securityService.isCreditOwner(authentication, #id)")
    public ResponseEntity<CreditDTO> getCreditById(@PathVariable Long id) {
        return ResponseEntity.ok(creditService.getCreditById(id));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get credits by client ID", description = "Get all credits for a specific client")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE') or @securityService.isClientOwner(authentication, #clientId)")
    public ResponseEntity<List<CreditDTO>> getCreditsByClientId(@PathVariable Long clientId) {
        return ResponseEntity.ok(creditService.getCreditsByClientId(clientId));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Get credits by status", description = "Get all credits with a specific status")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<List<CreditDTO>> getCreditsByStatut(@PathVariable StatutCredit statut) {
        return ResponseEntity.ok(creditService.getCreditsByStatut(statut));
    }

    @PostMapping("/personnel")
    @Operation(summary = "Create personal credit", description = "Create a new personal credit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE', 'ROLE_CLIENT')")
    public ResponseEntity<CreditPersonnelDTO> createCreditPersonnel(@RequestBody CreditPersonnelDTO creditDTO) {
        return ResponseEntity.ok(creditService.createCreditPersonnel(creditDTO));
    }

    @PostMapping("/immobilier")
    @Operation(summary = "Create real estate credit", description = "Create a new real estate credit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE', 'ROLE_CLIENT')")
    public ResponseEntity<CreditImmobilierDTO> createCreditImmobilier(@RequestBody CreditImmobilierDTO creditDTO) {
        return ResponseEntity.ok(creditService.createCreditImmobilier(creditDTO));
    }

    @PostMapping("/professionnel")
    @Operation(summary = "Create professional credit", description = "Create a new professional credit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE', 'ROLE_CLIENT')")
    public ResponseEntity<CreditProfessionnelDTO> createCreditProfessionnel(@RequestBody CreditProfessionnelDTO creditDTO) {
        return ResponseEntity.ok(creditService.createCreditProfessionnel(creditDTO));
    }

    @PatchMapping("/{id}/statut")
    @Operation(summary = "Update credit status", description = "Update the status of an existing credit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<CreditDTO> updateCreditStatut(@PathVariable Long id, @RequestParam StatutCredit statut) {
        return ResponseEntity.ok(creditService.updateCreditStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete credit", description = "Delete a credit by its ID")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCredit(@PathVariable Long id) {
        creditService.deleteCredit(id);
        return ResponseEntity.noContent().build();
    }
}
