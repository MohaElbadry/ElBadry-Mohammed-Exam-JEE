package com.elbadry.mohammed.dtos;

import com.elbadry.mohammed.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDTO {
    private Long id;
    private String username;
    private Set<Role> roles;
    private Long clientId;
}
