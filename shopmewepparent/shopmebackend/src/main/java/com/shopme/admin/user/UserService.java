package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> userList() {
        return userRepository.findAll();
    }

    public List<Role> roleList() {
        return roleRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
