package com.elbadry.mohammed.dtos;

import com.elbadry.mohammed.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String nom;
    private String email;
    private Set<Role> roles;
}
