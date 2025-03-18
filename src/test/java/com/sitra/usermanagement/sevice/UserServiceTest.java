package com.sitra.usermanagement.sevice;

import com.sitra.usermanagement.dto.UserRequestDTO;
import com.sitra.usermanagement.dto.UserResponseDTO;
import com.sitra.usermanagement.entity.User;
import com.sitra.usermanagement.exception.UserNotFoundException;
import com.sitra.usermanagement.repository.UserRepository;
import com.sitra.usermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserId("U12345");
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmailAddress("john.doe@example.com");
        user.setCreateDttm(LocalDateTime.now());
        user.setUpdateDttm(LocalDateTime.now());

        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUserId("U12345");
        userRequestDTO.setFirstName("John");
        userRequestDTO.setLastName("Doe");
        userRequestDTO.setEmailAddress("john.doe@example.com");

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId("U12345");
        userResponseDTO.setFirstName("John");
        userResponseDTO.setLastName("Doe");
        userResponseDTO.setEmailAddress("john.doe@example.com");
    }
    // Successfully creates a user
    @Test
    void shouldCreateUserSuccessfully() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO result = userService.createUser(userRequestDTO);

        assertNotNull(result);
        assertEquals(user.getUserId(), result.getUserId());
        verify(userRepository, times(1)).save(any(User.class));
    }

    //  Database constraint violation (e.g., duplicate email)
    @Test
    void shouldThrowException_WhenCreatingUserWithDuplicateEmail() {
        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> userService.createUser(userRequestDTO));
        verify(userRepository, times(1)).save(any(User.class));
    }
    // Successfully updates an existing user
    @Test
    void shouldUpdateUserSuccessfully() {
        when(userRepository.findByUserId("U12345")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO result = userService.updateUser("U12345", userRequestDTO);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(userRepository, times(1)).findByUserId("U12345");
        verify(userRepository, times(1)).save(any(User.class));
    }

    // Updating non-existing user throws exception
    @Test
    void shouldThrowException_WhenUpdatingNonExistingUser() {
        when(userRepository.findByUserId("U99999")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateUser("U99999", userRequestDTO));
        verify(userRepository, times(1)).findByUserId("U99999");
        verify(userRepository, never()).save(any(User.class));
    }
    //Successfully deletes an existing user
    @Test
    void shouldDeleteUserSuccessfully() {
        when(userRepository.findByUserId("U12345")).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        assertDoesNotThrow(() -> userService.deleteUser("U12345"));
        verify(userRepository, times(1)).findByUserId("U12345");
        verify(userRepository, times(1)).delete(user);
    }

    // Deleting a non-existing user throws exception
    @Test
    void shouldThrowException_WhenDeletingNonExistingUser() {
        when(userRepository.findByUserId("U99999")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.deleteUser("U99999"));
        verify(userRepository, times(1)).findByUserId("U99999");
        verify(userRepository, never()).delete(any(User.class));
    }
    // Retrieves users under a supervisor
    @Test
    void shouldReturnUsersBySupervisor() {
        when(userRepository.findBySupervisorUserId("S123")).thenReturn(List.of(user));

        List<UserResponseDTO> users = userService.getUsersBySupervisor("S123");

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository, times(1)).findBySupervisorUserId("S123");
    }

    // Returns empty list if no users are found
    @Test
    void shouldReturnEmptyList_WhenNoUsersBySupervisor() {
        when(userRepository.findBySupervisorUserId("S999")).thenReturn(List.of());

        List<UserResponseDTO> users = userService.getUsersBySupervisor("S999");

        assertNotNull(users);
        assertTrue(users.isEmpty());
        verify(userRepository, times(1)).findBySupervisorUserId("S999");
    }
    //Successfully updates supervisor
    @Test
    void shouldUpdateSupervisorSuccessfully() {
        when(userRepository.findByUserId("U12345")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO result = userService.updateSupervisor("U12345", "S456");

        assertNotNull(result);
        assertEquals("S456", result.getSupervisorUserId());
        verify(userRepository, times(1)).findByUserId("U12345");
        verify(userRepository, times(1)).save(any(User.class));
    }

    //  Updating supervisor for a non-existing user throws exception
    @Test
    void shouldThrowException_WhenUpdatingSupervisorForNonExistingUser() {
        when(userRepository.findByUserId("U99999")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.updateSupervisor("U99999", "S456"));
        verify(userRepository, times(1)).findByUserId("U99999");
        verify(userRepository, never()).save(any(User.class));
    }

}