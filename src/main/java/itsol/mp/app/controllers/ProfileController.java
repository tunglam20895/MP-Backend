package itsol.mp.app.controllers;

import itsol.mp.app.entities.Users;
import itsol.mp.app.repositories.UserRepository;
import itsol.mp.app.utils.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/get-info/{username}")
    public ResponseEntity<?> getinfo(@PathVariable String username) {
        if (userRepository.findByUsername(username) == null) {
            return new ResponseEntity<>(
                    new CustomErrorType("Lỗi, User " + username + " không tồn tại"), HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(
                userRepository.findByUsername(username), HttpStatus.OK
        );
    }

    @PostMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@RequestBody Users updateUser) {
        if (userRepository.findByUsername(updateUser.getUsername()) == null) {
            return new ResponseEntity(
                    new CustomErrorType("User " + updateUser.getUsername() + " không tồn tại"), HttpStatus.CONFLICT
            );
        }
        return new ResponseEntity<>(
                userRepository.save(updateUser), HttpStatus.OK
        );
    }

}
