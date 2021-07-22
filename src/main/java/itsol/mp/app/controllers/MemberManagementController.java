package itsol.mp.app.controllers;

import itsol.mp.app.entities.Users;
import itsol.mp.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberManagementController {

    @Autowired
    private UserService userService;

    @GetMapping("/get-member")
    public ResponseEntity<List<?>> getAllUser(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }
}
