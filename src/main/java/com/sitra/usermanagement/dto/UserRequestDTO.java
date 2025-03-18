package com.sitra.usermanagement.dto;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    @NotBlank(message = "User ID is required.")
    private String userId;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Email address is required.")
    @Email(message = "Email address must be valid.")
    private String emailAddress;
    private String supervisorUserId;
    private String titleText;
    @NotNull(message = "Address list cannot be null.")
    private List<AddressDTO> addresses;
public UserRequestDTO(){}

    public UserRequestDTO(String userId, String lastName, String firstName, String emailAddress, String supervisorUserId, String titleText, List<AddressDTO> addresses) {
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddress = emailAddress;
        this.supervisorUserId = supervisorUserId;
        this.titleText = titleText;
        this.addresses = addresses;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSupervisorUserId() {
        return supervisorUserId;
    }

    public void setSupervisorUserId(String supervisorUserId) {
        this.supervisorUserId = supervisorUserId;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
