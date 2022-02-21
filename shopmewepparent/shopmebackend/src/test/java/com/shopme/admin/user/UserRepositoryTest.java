package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserWithOneRole() {
        Role roleAdmin = entityManager.find(Role.class, 1);
        User user = new User("ra@gmail.com", "Admin123", "rakib", "khan");
        user.addRole(roleAdmin);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testUserCreateWithTwoRole() {
        User user = new User("rakibTwo@gmail.com", "password", "Raking", "Nai");
        Role role = new Role(1);
        Role roleTwo = new Role(2);
        user.addRole(role);
        user.addRole(roleTwo);
        User savedUser = userRepository.save(user);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    public void listAllUsers() {
        List<User> userList = userRepository.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void testGetUserById() {
        User usr = null;
        Optional<User> user = userRepository.findById(7);
        if (user.isPresent()) {
            usr = user.get();
        }
        System.out.println(usr);
        assertThat(usr).isNotNull();
    }

    @Test
    public void testUpdateUser() {
        User usr;
        Optional<User> user = userRepository.findById(7);
        if (user.isPresent()) {
            usr = user.get();
            usr.setEnable(true);
            usr.setEmail("kasem@gmail.com");
            userRepository.save(usr);
        }
    }

    @Test
    public void testUpdateRole() {
        Optional<User> user = userRepository.findById(9);
        if (user.isPresent()) {
            User usr = user.get();
            Role role = roleRepository.findById(1).get();
            Role sales_person = roleRepository.findById(3).get();
            usr.getRoles().remove(sales_person);
            usr.addRole(role);
            userRepository.save(usr);
        }

    }

    @Test
    public void testDeleteUser() {
        Integer userId = 9;
        userRepository.deleteById(userId);
    }
}
