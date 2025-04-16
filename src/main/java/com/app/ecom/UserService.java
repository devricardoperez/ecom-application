package com.app.ecom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
//    private List<User> users = new ArrayList<>();
//    private Long nextId = 1L;
    private final UserRepository userRepository;


    public List<User> fectAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
//        user.setId(nextId++);
        userRepository.save(user);
    }

    public Optional<User> fetchUser(Long id) {
        return userRepository.findById(id);
    }

    public boolean updateUser(User updateUser, Long id) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateUser.getFirstName());
                    existingUser.setLastName(updateUser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                }).orElse(false);
    }
}
