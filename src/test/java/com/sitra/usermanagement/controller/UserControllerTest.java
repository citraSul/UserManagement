package com.sitra.usermanagement.controller;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitra.usermanagement.dto.UserRequestDTO;
import com.sitra.usermanagement.dto.UserResponseDTO;
import com.sitra.usermanagement.exception.UserNotFoundException;
import com.sitra.usermanagement.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUserId("U123");
        userRequestDTO.setFirstName("John");
        userRequestDTO.setLastName("Doe");
        userRequestDTO.setEmailAddress("john.doe@example.com");
        userRequestDTO.setTitleText("Software Engineer");
        userRequestDTO.setSupervisorUserId(null);
        userRequestDTO.setAddresses(Collections.emptyList());

        userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1);
        userResponseDTO.setUserId("U123");
        userResponseDTO.setFirstName("John");
        userResponseDTO.setLastName("Doe");
        userResponseDTO.setEmailAddress("john.doe@example.com");
        userResponseDTO.setTitleText("Software Engineer");
        userResponseDTO.setSupervisorUserId(null);
    }

    // Happy Path: Create User
    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        when(userService.createUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        ResultActions response = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(userResponseDTO.getUserId())))
                .andExpect(jsonPath("$.firstName", is(userResponseDTO.getFirstName())));
    }

    //Unhappy Path: Create User with Invalid Data
    @Test
    void shouldFailToCreateUser_WhenInvalidData() throws Exception {
        userRequestDTO.setEmailAddress(""); // Invalid email

        ResultActions response = mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequestDTO)));

        response.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Bad Request")));
    }

    // Happy Path: Get Users by Supervisor
    @Test
    void shouldReturnUsersForSupervisor() throws Exception {
        when(userService.getUsersBySupervisor("U123")).thenReturn(Collections.singletonList(userResponseDTO));

        mockMvc.perform(get("/api/users/supervisor/U123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId", is("U123")));
    }

    // Unhappy Path: No Users Found for Supervisor
    @Test
    void shouldReturnNotFoundForSupervisorWithoutUsers() throws Exception {
        when(userService.getUsersBySupervisor("U999")).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/users/supervisor/U999"))
                .andExpect(status().isOk()) // If empty list, return 200 with []
                .andExpect(jsonPath("$.length()").value(0));
    }

    // Happy Path: Update User
    @Test
    void shouldUpdateUserSuccessfully() throws Exception {
        when(userService.updateUser(Mockito.eq("U123"), any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        mockMvc.perform(put("/api/users/U123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("U123")));
    }

    // Unhappy Path: Update Non-Existent User
    @Test
    void shouldReturnNotFound_WhenUpdatingNonExistingUser() throws Exception {
        when(userService.updateUser(Mockito.eq("U999"), any(UserRequestDTO.class)))
                .thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(put("/api/users/U999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequestDTO)))
                .andExpect(status().isNotFound());
    }

   // Happy Path: Delete User
    @Test
    void shouldDeleteUserSuccessfully() throws Exception {
        Mockito.doNothing().when(userService).deleteUser("U123");

        mockMvc.perform(delete("/api/users/U123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("User deleted successfully")));
    }

    // Unhappy Path: Delete Non-Existent User
    @Test
    void shouldReturnNotFound_WhenDeletingNonExistingUser() throws Exception {
        Mockito.doThrow(new RuntimeException("User not found")).when(userService).deleteUser("U999");

        mockMvc.perform(delete("/api/users/U999"))
                .andExpect(status().isNotFound());
    }

    // Happy Path: Update User Supervisor
    @Test
    void shouldUpdateUserSupervisorSuccessfully() throws Exception {

        when(userService.updateSupervisor("U123", "U456")).thenReturn(userResponseDTO);

        mockMvc.perform(patch("/api/users/U123/supervisor/U456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is("U123")));
    }

    //  Unhappy Path: Assign Supervisor to Non-Existent User
    @Test
    void shouldReturnNotFound_WhenAssigningSupervisorToNonExistingUser() throws Exception {
        String token = "eyJraWQiOiI0YWIzZmQ1My1jNzRmLTQ5NTktOGM4MS01YTBkZmVmZTVlMjgiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJteS1jbGllbnQtaWQiLCJhdWQiOiJteS1jbGllbnQtaWQiLCJuYmYiOjE3NDIzMjI3NzgsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE3NDIzMjMwNzgsImlhdCI6MTc0MjMyMjc3OCwianRpIjoiZTg5OTEyZTYtYzQ4ZS00ODM2LWI4ODktM2FiMjM1NjQ3YTNjIn0.aJgEv6YFPadVDFba49z8TTtYUS575VrSHxg5bB2BkEYmo8YeXV_mH7AsiNOlQkjYLhUOa7RPfcoLMHziKaZoQXabnpn5-CZUfSB_nqe6UwJZ7hGHOSwRtmz5YG9uM5uYF5mJANj49kP2zPeAm8JI66Duz-31vqDDIWjJBZgivqo32_BdtJBiNtrkQvoSB0JeuwPnYxELNz8asbrYTv4-IZCSWA6RJNkc0nWuyx0MvRqWijjzGPW3vXlai0wjBv9kBHQwbWBD3rHiWTxqIj0qmYyA1Y1A8LCG7Fzh18EFNkm492IUmT_fU7V3Jy0I7tjMVAtyHPSzMUVdyDmpQ0W1oQ";
        when(userService.updateSupervisor("U999", "U456"))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(patch("/api/users/U999/supervisor/U456")

                        .with(csrf()))

                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"}) // Mock authentication to bypass security
    void shouldReturnNotFound_WhenAssigningSupervisorToNonExistingUser1() throws Exception {
        when(userService.updateSupervisor("U999", "U456"))
                .thenThrow(new UserNotFoundException("User not found"));

        mockMvc.perform(patch("/api/users/U999/supervisor/U456")
                        .with(csrf().asHeader())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")));
    }

}

