package com.sitra.usermanagement.controller;


import com.sitra.usermanagement.dto.UserRequestDTO;
import com.sitra.usermanagement.dto.UserResponseDTO;
import com.sitra.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing user-related operations.
 * This controller provides endpoints for user creation, update, deletion,
 * retrieving users by supervisor, and updating user supervisors.
 *
 * @author Sitra
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {


    private  static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    /**
     * Constructor for injecting {@link UserService}.
     *
     * @param userService Service class for user operations.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Creates a new user.
     *
     * @param dto The request body containing user details.
     * @return ResponseEntity containing the created user details.
     */
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO dto) {
        LOGGER.info("Received request to create user with email: {}", dto.getEmailAddress());

        UserResponseDTO response = userService.createUser(dto);
        LOGGER.info("User created successfully with ID: {}", response.getUserId());

        return ResponseEntity.ok(response);
    }

    /**
     * Updates an existing user.
     *
     * @param userId The ID of the user to update.
     * @param dto The request body containing updated user details.
     * @return ResponseEntity containing the updated user details.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable String userId, @RequestBody UserRequestDTO dto) {
        LOGGER.info("Received request to update user with ID: {}", userId);

        UserResponseDTO response = userService.updateUser(userId, dto);
        LOGGER.info("User updated successfully with ID: {}", response.getUserId());

        return ResponseEntity.ok(response);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to delete.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        LOGGER.warn("Received request to delete user with ID: {}", userId);

        userService.deleteUser(userId);

        LOGGER.info("User deleted successfully: {}", userId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all users supervised by a given supervisor.
     *
     * @param supervisorId The ID of the supervisor.
     * @return ResponseEntity containing a list of users supervised by the given supervisor.
     */
    @GetMapping("/supervisor/{supervisorId}")
    public ResponseEntity<List<UserResponseDTO>> getUsersBySupervisor(@PathVariable String supervisorId) {
        LOGGER.info("Fetching users supervised by ID: {}", supervisorId);

        List<UserResponseDTO> users = userService.getUsersBySupervisor(supervisorId);

        LOGGER.info("Retrieved {} users for supervisor ID: {}", users.size(), supervisorId);

        return ResponseEntity.ok(users);
    }

    /**
     * Updates the supervisor of a given user.
     *
     * @param userId The ID of the user whose supervisor needs to be updated.
     * @param newSupervisorId The ID of the new supervisor.
     * @return ResponseEntity containing the updated user details.
     */
    @PatchMapping("/{userId}/supervisor/{newSupervisorId}")
    public ResponseEntity<UserResponseDTO> updateSupervisor(@PathVariable String userId, @PathVariable String newSupervisorId) {
        LOGGER.info("Updating supervisor for user ID: {} to new supervisor ID: {}", userId, newSupervisorId);

        UserResponseDTO response = userService.updateSupervisor(userId, newSupervisorId);

        LOGGER.info("Supervisor updated successfully for user ID: {}", response.getUserId());

        return ResponseEntity.ok(response);
    }


}
