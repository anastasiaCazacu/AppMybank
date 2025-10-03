package com.mybank.repository;

import com.mybank.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("ana");
        user.setPassword("1234");
        user.setRole("USER");

        userRepository.save(user);

        Optional<User> found = userRepository.findByUsername("ana");
        assertTrue(found.isPresent());
        assertEquals("ana", found.get().getUsername());
    }
}
