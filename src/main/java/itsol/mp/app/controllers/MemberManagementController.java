package itsol.mp.app.controllers;

import itsol.mp.app.entities.Divisons;
import itsol.mp.app.entities.Users;
import itsol.mp.app.entities.enums.UserStatus;
import itsol.mp.app.entities.enums.UserType;
import itsol.mp.app.services.DivisionService;
import itsol.mp.app.services.ProjectService;
import itsol.mp.app.services.UserService;
import itsol.mp.app.utils.CustomErrorType;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberManagementController {

    @Autowired
    private UserService userService;

    @Autowired DivisionService divisionService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/get-member")
    public ResponseEntity<List<?>> getAllUser() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    //Lấy danh sách phòng ban
    @GetMapping("/get-divisions")
    public ResponseEntity<List<?>> getAllDivisions() {
        return new ResponseEntity<>(
                divisionService.getAllDivision(), HttpStatus.OK
        );
    }


    //update member
    @PostMapping("/save-member/{id}")
    public ResponseEntity<?> saveUser(@RequestBody Users users, @PathVariable long id) {
        System.out.println("aaaaaaaaaaaa" + users.getDivisions().getDescription());
        System.out.println(users.getUserStatus());
        if (userService.findUserByUsername(users.getUsername()) == null) {
            return new ResponseEntity(
                    new CustomErrorType("User " + users + " không tồn tại"), HttpStatus.CONFLICT
            );
        }
        Divisons divisons = divisionService.findById(id);
        users.setDivisions(divisons);
        users.setUserStatus(users.getUserStatus());
        Date dateCreate = new Date();
        dateCreate = users.getDateCreated();
//        birthDay = users.getBirthDay();
//        users.setDateCreated(dateCreate);

        return new ResponseEntity(
                userService.updateUser(users), HttpStatus.OK
        );
    }

    Date date = new Date();

    @PostMapping("/create-member/{divisionId}")
    public ResponseEntity<?> createMember(@RequestBody Users newMember, @PathVariable long divisionId) {
        if(userService.findUserByUsername(newMember.getUsername()) != null){
            return new ResponseEntity(
                    new CustomErrorType("User " + newMember.getUsername() +" đã tồn tại"),HttpStatus.CONFLICT
            );
        }
        if(divisionService.findById(divisionId) == null) {
            return new ResponseEntity(
                    new CustomErrorType("Đơn vị không tồn tại"), HttpStatus.CONFLICT
            );
        }
        newMember.setPassword(encoder.encode(newMember.getPassword()));
        newMember.setMiddleName("A");
        newMember.setDateCreated(date);
        newMember.setAvartar("A");
        newMember.setUserStatus(UserStatus.ACTIVE);
        newMember.setUserType(UserType.EMPLOYEE);
        newMember.setDivisions(divisionService.findById(divisionId));
        return new ResponseEntity(
                userService.addUser(newMember),HttpStatus.CREATED
        );
    }
}
