package itsol.mp.app.services;

import itsol.mp.app.entities.Users;
import itsol.mp.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

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

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public String getRamdomPassword() {
        String characters = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String randomString = "";
        int length = 6;
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++) {
            randomString += text[i];
        }
        return randomString;
    }

    public Users findUserByUsernameAndEmail(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }

    public Users findUserByUsernameAndRole(String username) {
        return userRepository.findByUsernameAndRole(username);
    }

}
