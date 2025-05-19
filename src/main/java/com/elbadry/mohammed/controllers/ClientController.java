package com.elbadry.mohammed.controllers;

import com.elbadry.mohammed.dtos.ClientDTO;
import com.elbadry.mohammed.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Client management API")
@SecurityRequirement(name = "bearerAuth")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @Operation(summary = "Get all clients", description = "Get a list of all clients")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID", description = "Get a client by its ID")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE') or @securityService.isClientOwner(authentication, #id)")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get client by email", description = "Get a client by its email")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<ClientDTO> getClientByEmail(@PathVariable String email) {
        return ResponseEntity.ok(clientService.getClientByEmail(email));
    }

    @PostMapping
    @Operation(summary = "Create client", description = "Create a new client")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.createClient(clientDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update client", description = "Update an existing client")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_EMPLOYE')")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.updateClient(id, clientDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client", description = "Delete a client by its ID")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
