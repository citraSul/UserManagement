package com.sitra.usermanagement.service;

import com.sitra.usermanagement.dto.UserRequestDTO;
import com.sitra.usermanagement.dto.UserResponseDTO;
import com.sitra.usermanagement.entity.User;
import com.sitra.usermanagement.exception.UserNotFoundException;
import com.sitra.usermanagement.repository.UserRepository;
import com.sitra.usermanagement.util.UserConverter;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service class for managing user-related operations.
 * <p>
 * This service handles user creation, updating, deletion, retrieval, and supervisor management.
 * It interacts with the {@link UserRepository} and converts entities to DTOs using {@link UserConverter}.
 * </p>
 *
 * @author sitra
 * @version 1.0
 */
@Slf4j
@Service
public class UserService {

    private  static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    /**
     * Constructor for injecting {@link UserRepository}.
     *
     * @param userRepository Repository for user operations.
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**
     * Creates a new user.
     *
     * @param dto The request DTO containing user details.
     * @return {@link UserResponseDTO} containing the created user's details.
     */
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        LOGGER.info("Creating user with email: {}", dto.getEmailAddress());

        User user = UserConverter.convertToEntity(dto);

        User savedUser = userRepository.save(user);
        LOGGER.info("User created successfully with ID: {}", savedUser.getUserId());

        return UserConverter.convertToDTO(savedUser);
    }

    /**
     * Updates an existing user.
     *
     * @param userId The ID of the user to update.
     * @param dto    The request DTO containing updated user details.
     * @return {@link UserResponseDTO} containing the updated user details.
     * @throws UserNotFoundException if the user is not found.
     */
    @Transactional
    public UserResponseDTO updateUser(String userId, UserRequestDTO dto) {
        LOGGER.info("Updating user with ID: {}", userId);

        User existingUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    LOGGER.error("User with ID {} not found", userId);
                  return new UserNotFoundException("User with ID " + userId + " not found");

                });

        User updatedUser = UserConverter.convertToEntity(dto);

        // Preserve existing fields
        updatedUser.setId(existingUser.getId());

        User savedUser = userRepository.save(updatedUser);
        LOGGER.info("User updated successfully: {}", savedUser.getUserId());


        return UserConverter.convertToDTO(savedUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param userId The ID of the user to delete.
     * @throws UserNotFoundException if the user is not found.
     */
    @Transactional
    public void deleteUser(String userId) {
        LOGGER.warn("Deleting user with ID: {}", userId);

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    LOGGER.error("User with ID {} not found", userId);
                   return new RuntimeException("User with ID " + userId + " not found");
                });

        userRepository.delete(user);
        LOGGER.info("User deleted successfully: {}", userId);
    }

    /**
     * Retrieves all users supervised by a given supervisor.
     *
     * @param supervisorId The ID of the supervisor.
     * @return A list of {@link UserResponseDTO} representing the users supervised by the given supervisor.
     */
    public List<UserResponseDTO> getUsersBySupervisor(String supervisorId) {
        LOGGER.info("Fetching users supervised by: {}", supervisorId);

        List<User> users = userRepository.findBySupervisorUserId(supervisorId);
        return users.stream().map(UserConverter::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Updates the supervisor of a given user.
     *
     * @param userId         The ID of the user whose supervisor needs to be updated.
     * @param newSupervisorId The ID of the new supervisor.
     * @return {@link UserResponseDTO} containing the updated user details.
     * @throws UserNotFoundException if the user is not found.
     */
    @Transactional
    public UserResponseDTO updateSupervisor(String userId, String newSupervisorId) {
        LOGGER.info("Updating supervisor for user ID: {} to new supervisor ID: {}", userId, newSupervisorId);

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> {
                    LOGGER.error("User with ID {} not found", userId);
                    return new UserNotFoundException("User with ID " + userId + " not found");
                });


        user.setSupervisorUserId(newSupervisorId);

        User savedUser = userRepository.save(user);
        LOGGER.info("Supervisor updated successfully for user ID: {}", savedUser.getUserId());

        return UserConverter.convertToDTO(savedUser);
    }


    public UserResponseDTO getUser(String userId) {

    User user  =  userRepository.findByUserId(userId).orElseThrow(() -> {
            LOGGER.error("User with ID {} not found", userId);
            return new UserNotFoundException("User with ID " + userId + " not found");
        });

        return UserConverter.convertToDTO(user);
    }
}
