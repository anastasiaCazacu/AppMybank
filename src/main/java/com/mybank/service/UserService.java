package com.mybank.service;

import com.mybank.dto.UserDTO;
import com.mybank.entity.Role;
import com.mybank.entity.User;
import com.mybank.repository.RoleRepository;
import com.mybank.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    public final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    //inregistrarea utilizatorilor
    @Transactional
    public User registerUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username-ul deja exista!");
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email-ul deja exista!");
        }
        Role role=roleRepository.findByName(userDTO.getRoleName()).orElseThrow(() -> new RuntimeException("Rolul nu a fost găsit: " + userDTO.getRoleName()));

        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFullname(userDTO.getFullname());
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setRole(role);

        //daca nu data de nastere e gol se seteaza automat
        if (userDTO.getDateOfBirth() != null) {
            Date date= Date.from(userDTO.getDateOfBirth()
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant());
            user.setDateOfBirth(date);
        }
        return userRepository.save(user);
    }

    //caut utilizatorul dupa username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //caut utilizatorul dupa email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

       //verif daca exista userul
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    //verific aca exista emailul
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public List<User> getUsersByRole(String roleName) {
        return userRepository.findByRole_Name(roleName);
    }

    //getAll()
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //sterge un utilizator
    public  void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
