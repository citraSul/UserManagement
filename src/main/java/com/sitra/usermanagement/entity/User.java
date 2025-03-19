package com.sitra.usermanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email_address", nullable = false)
    private String emailAddress;

    @ManyToOne(fetch = FetchType.LAZY) //Prevents unnecessary supervisor fetching
    @JoinColumn(name = "supervisor_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    @JsonIgnore //Prevents serialization of supervisor field
    private User supervisor;

    @Column(name = "supervisor_user_id")
    private String supervisorUserId;

    @Column(name = "title_text ")
    private String titleText;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
    @CreatedBy
    @Column(name = "create_user_id", nullable = false,updatable = false)
    private String createUserId;
    @CreatedDate
    @Column(name = "create_dttm", nullable = false, updatable = false)
    private LocalDateTime createDttm = LocalDateTime.now();
    @LastModifiedBy
    @Column(name = "update_user_id", nullable = false)
    private String updateUserId;
    @LastModifiedDate
    @Column(name = "update_dttm", nullable = false)
    private LocalDateTime updateDttm;

      public  User(){

     }
    public User(Integer id, String userId, String firstName, String lastName, String emailAddress,
                User supervisor,
                String supervisorUserId,
                String titleText, List<Address> addresses, String createUserId, LocalDateTime createDttm, String updateUserId, LocalDateTime updateDttm) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.supervisor = supervisor;
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

    public User getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(User supervisor) {
        this.supervisor = supervisor;
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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public LocalDateTime getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(LocalDateTime createDttm) {
        this.createDttm = createDttm;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public LocalDateTime getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(LocalDateTime updateDttm) {
        this.updateDttm = updateDttm;
    }

    @PreUpdate
    public void preUpdate() {

    this.updateDttm = LocalDateTime.now();
    }


}
