package com.sitra.usermanagement.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


public class UserResponseDTO {
    private Integer id;
    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String supervisorUserId;
    private String titleText;
    private List<AddressDTO> addresses;
    private Long createUserId;
    private LocalDateTime createDttm;
    private Long updateUserId;
    private LocalDateTime updateDttm;
    public UserResponseDTO(){

    }
    public UserResponseDTO(Integer id, String userId, String firstName, String lastName, String emailAddress, String supervisorUserId, String titleText, List<AddressDTO> addresses, Long createUserId, LocalDateTime createDttm, Long updateUserId, LocalDateTime updateDttm) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.supervisorUserId = supervisorUserId;
        this.titleText = titleText;
        this.addresses = addresses;
        this.createUserId = createUserId;
        this.createDttm = createDttm;
        this.updateUserId = updateUserId;
        this.updateDttm = updateDttm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(LocalDateTime createDttm) {
        this.createDttm = createDttm;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public LocalDateTime getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(LocalDateTime updateDttm) {
        this.updateDttm = updateDttm;
    }
}
