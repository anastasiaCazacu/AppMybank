package com.mybank.repository;

import com.mybank.entity.Role;
import com.mybank.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        // Creează și salvează rolul
        Role role= new Role();
        role.setName("CLIENT");
        roleRepository.save(role);

        // Creează și salvează utilizatorul
        User user = new User();
        user.setUsername("ana");
        user.setPassword("1234");
        user.setRole(role);// Setează rolul corect

        userRepository.save(user);

        // Caută utilizatorul după username
        Optional<User> found = userRepository.findByUsername("ana");

        // Verifică dacă a fost găsit și dacă are datele corecte
        assertTrue(found.isPresent());
        assertEquals("ana", found.get().getUsername());
        assertEquals("CLIENT", found.get().getRole().getName());
    }
}
