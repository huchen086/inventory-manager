package org.goldencloud.inventorymanager.models;


import javax.validation.constraints.Size;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @NotNull(message = "Please provide an email")
    @Email
    private String email;

    @Size(min=6, message = "Your password must have at least 6 characters")
    @NotNull(message = "Please provide your password")
    private String password;

    @NotNull(message = "Please provide your first name")
    private String firstName;

    @NotNull(message = "Please provide your last name")
    private String lastName;

    @NotNull
    private boolean enabled = false;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;

    public User() {

    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}