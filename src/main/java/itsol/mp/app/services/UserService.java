package itsol.mp.app.services;

import itsol.mp.app.entities.Users;
import itsol.mp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users addUser(Users users) {
        return userRepository.save(users);
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users updateUser(Users users) {
        return userRepository.save(users);
    }

    public Users findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
