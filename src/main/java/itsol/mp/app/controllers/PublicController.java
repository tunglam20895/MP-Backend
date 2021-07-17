package itsol.mp.app.controllers;

import itsol.mp.app.entities.Users;
import itsol.mp.app.services.UserService;
import itsol.mp.app.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;

import java.security.Principal;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/public")
public class PublicController {

    public static final Logger logger = LoggerFactory.getLogger(PublicController.class);

    Random OTP = new Random();

    @Autowired
    private JavaMailSender javaMailSender;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Principal login(Principal principal) {
        logger.info("user logged " + principal);
        return principal;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users newUser) {
        if (userService.findUserByUsername(newUser.getUsername()) != null) {
            logger.error("username Already exist " + newUser.getUsername());
            return new ResponseEntity(
                    new CustomErrorType("user with username " + newUser.getUsername() + "already exist "),
                    HttpStatus.CONFLICT);
        }
        newUser.setRole("ROLE_MEMBER");
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return new ResponseEntity<Users>(userService.addUser(newUser), HttpStatus.CREATED);
    }

    @PostMapping("/sendOTP")
    public int send(@RequestBody String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        int otp = OTP.nextInt(9999 - 1000);
        msg.setTo(email);
        msg.setSubject("XÁC NHẬN ĐĂNG KÝ");
        msg.setText("Mã OTP của bạn là " +otp + " vui lòng không chia sẻ mã với bất cứ ai để đảm bảo bảo mật thông tin của bạn!!!");
        javaMailSender.send(msg);
        return otp;
    }

    @GetMapping("/hello")
    public List<Users> getAll(){
        logger.info("abc");
        return userService.findAllUsers();
    }

    @GetMapping("/a")
    public Users getAll(String username){
        username = "admin";
        return userService.findUserByUsername(username);
    }

}
