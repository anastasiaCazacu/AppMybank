package com.mybank.dto;

import com.mybank.entity.Credit;
import com.mybank.entity.Deposit;
import com.mybank.entity.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

public class UserDTO {
    private String username;
    private String password;
    private String confirmPassword;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String roleName; // Exemplu: "CLIENT", "BANK", "ADMIN"

    // Getteri și setteri
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getConfirmPassword() { return confirmPassword; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }


//    @ManyToOne(fetch = FetchType.EAGER)// Relație cu rolul
//    @JoinColumn(name = "role_id") // Cheia străină în tabelul users
//    private Role role; //  corect: entitate deoarece e Role entitate
//
//    @OneToMany(mappedBy = "user")//relatia cu depozitele
//    private List<Credit> credits;
//
//    @OneToMany(mappedBy = "user")//relatia cu depozitele
//    private List<Deposit> deposits;
//
//    // Getteri și setteri
//    public Long getId() { return id; }
//
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//
//    public String getConfirmPassword() { return confirmPassword; }
//    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
//
//    public String getFullname() { return fullname; }
//    public void setFullname(String fullname) { this.fullname = fullname; }
//
//    public String getEmail() { return email; }
//    public void setEmail(String email) { this.email = email; }
//
//    public String getPhone() { return phone; }
//    public void setPhone(String phone) { this.phone = phone; }
//
//    public String getAddress() { return address; }
//    public void setAddress(String address) { this.address = address; }
//
//    public Role getRole() { return role; }
//    public void setRole(Role role) { this.role = role; }
//
//    public List<Credit> getCredits() { return credits; }
//    public void setCredits(List<Credit> credits) { this.credits = credits; }
//
//    public List<Deposit> getDeposits() { return deposits; }
//    public void setDeposits(List<Deposit> deposits) { this.deposits = deposits; }
}
