package com.elbadry.mohammed.services;

import com.elbadry.mohammed.dtos.ClientDTO;
import com.elbadry.mohammed.entities.Client;
import com.elbadry.mohammed.mappers.ClientMapper;
import com.elbadry.mohammed.repositories.ClientRepository;
import com.elbadry.mohammed.services.impl.ClientServiceImpl;
import java.lang.RuntimeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    private Client client;
    private ClientDTO clientDTO;

    @BeforeEach
    public void setup() {
        client = new Client();
        client.setId(1L);
        client.setNom("Test Client");
        client.setEmail("test@example.com");

        clientDTO = new ClientDTO();
        clientDTO.setId(1L);
        clientDTO.setNom("Test Client");
        clientDTO.setEmail("test@example.com");
    }

    @Test
    public void testGetAllClients() {
        // Arrange
        List<Client> clients = Arrays.asList(client);
        List<ClientDTO> clientDTOs = Arrays.asList(clientDTO);

        when(clientRepository.findAll()).thenReturn(clients);
        when(clientMapper.toDtoList(clients)).thenReturn(clientDTOs);

        // Act
        List<ClientDTO> result = clientService.getAllClients();

        // Assert
        assertThat(result).isNotEmpty();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getId()).isEqualTo(1L);
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    public void testGetClientById_Success() {
        // Arrange
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientMapper.toDto(client)).thenReturn(clientDTO);

        // Act
        ClientDTO result = clientService.getClientById(1L);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetClientById_NotFound() {
        // Arrange
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> clientService.getClientById(1L));
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    public void testCreateClient() {
        // Arrange
        when(clientMapper.toEntity(clientDTO)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDTO);

        // Act
        ClientDTO result = clientService.createClient(clientDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testUpdateClient_Success() {
        // Arrange
        when(clientRepository.existsById(1L)).thenReturn(true);
        when(clientMapper.toEntity(clientDTO)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDTO);

        // Act
        ClientDTO result = clientService.updateClient(1L, clientDTO);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        verify(clientRepository, times(1)).save(any(Client.class));
    }

    @Test
    public void testUpdateClient_NotFound() {
        // Arrange
        when(clientRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> clientService.updateClient(1L, clientDTO));
        verify(clientRepository, times(1)).existsById(1L);
        verify(clientRepository, never()).save(any(Client.class));
    }

    @Test
    public void testDeleteClient_Success() {
        // Arrange
        when(clientRepository.existsById(1L)).thenReturn(true);

        // Act
        clientService.deleteClient(1L);

        // Assert
        verify(clientRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteClient_NotFound() {
        // Arrange
        when(clientRepository.existsById(1L)).thenReturn(false);

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> clientService.deleteClient(1L));
        verify(clientRepository, times(1)).existsById(1L);
        verify(clientRepository, never()).deleteById(1L);
    }
}
