package com.elbadry.mohammed.controllers;

import com.elbadry.mohammed.dtos.RemboursementDTO;
import com.elbadry.mohammed.enums.TypeRemboursement;
import com.elbadry.mohammed.services.RemboursementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/remboursements")
@RequiredArgsConstructor
@Tag(name = "Remboursements", description = "Remboursement management API")
@SecurityRequirement(name = "bearerAuth")
public class RemboursementController {

    private final RemboursementService remboursementService;

    @GetMapping
    @Operation(summary = "Get all remboursements", description = "Get a list of all remboursements")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<List<RemboursementDTO>> getAllRemboursements() {
        return ResponseEntity.ok(remboursementService.getAllRemboursements());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get remboursement by ID", description = "Get a remboursement by its ID")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE') or @securityService.isRemboursementOwner(authentication, #id)")
    public ResponseEntity<RemboursementDTO> getRemboursementById(@PathVariable Long id) {
        return ResponseEntity.ok(remboursementService.getRemboursementById(id));
    }

    @GetMapping("/credit/{creditId}")
    @Operation(summary = "Get remboursements by credit ID", description = "Get all remboursements for a specific credit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE') or @securityService.isCreditOwner(authentication, #creditId)")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByCreditId(@PathVariable Long creditId) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByCreditId(creditId));
    }

    @GetMapping("/type/{type}")
    @Operation(summary = "Get remboursements by type", description = "Get all remboursements with a specific type")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByType(@PathVariable TypeRemboursement type) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByType(type));
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get remboursements by date range", description = "Get all remboursements within a specific date range")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<List<RemboursementDTO>> getRemboursementsByDateRange(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin) {
        return ResponseEntity.ok(remboursementService.getRemboursementsByDateRange(dateDebut, dateFin));
    }

    @PostMapping
    @Operation(summary = "Create remboursement", description = "Create a new remboursement")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE', 'ROLE_CLIENT')")
    public ResponseEntity<RemboursementDTO> createRemboursement(@RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.createRemboursement(remboursementDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update remboursement", description = "Update an existing remboursement")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<RemboursementDTO> updateRemboursement(@PathVariable Long id, @RequestBody RemboursementDTO remboursementDTO) {
        return ResponseEntity.ok(remboursementService.updateRemboursement(id, remboursementDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete remboursement", description = "Delete a remboursement by its ID")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteRemboursement(@PathVariable Long id) {
        remboursementService.deleteRemboursement(id);
        return ResponseEntity.noContent().build();
    }
}
