package itsol.mp.app.controllers;

import itsol.mp.app.dto.ResetPasswordDTO;
import itsol.mp.app.entities.Users;
import itsol.mp.app.entities.enums.UserStatus;
import itsol.mp.app.entities.enums.UserType;
import itsol.mp.app.repositories.UserRepository;
import itsol.mp.app.services.UserService;
import itsol.mp.app.utils.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/public")
public class PublicController {

    public static final Logger logger = LoggerFactory.getLogger(PublicController.class);

    Random random = new Random();

//    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    Date date = new Date();

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

        newUser.setUserType(UserType.COLLABORATOR);
        newUser.setUserStatus(UserStatus.PENDING);
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        newUser.setDateCreated(date);
        System.out.println();
        return new ResponseEntity<Users>(userService.addUser(newUser), HttpStatus.CREATED);
    }

    @PostMapping("/sendOTP")
    public int send(@RequestBody String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        int otp = random.nextInt(9999 - 1000);
        msg.setTo(email);
        msg.setSubject("XÁC NHẬN ĐĂNG KÝ");
        msg.setText("Mã OTP của bạn là " +otp + " vui lòng không chia sẻ mã với bất cứ ai để đảm bảo bảo mật thông tin của bạn!!!");
        javaMailSender.send(msg);
        return otp;
    }

    @GetMapping("/hello")
    public List<Users> getAll(){
        logger.info("User type "+UserType.COLLABORATOR);
        return userService.findAllUsers();
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassWord(@RequestBody ResetPasswordDTO resetPasswordDTO) throws MessagingException {
        Users user = userService.findUserByUsernameAndEmail(resetPasswordDTO.getUsername(),resetPasswordDTO.getEmail());
//        logger.info("Username " +username +" Email" +email);
        if(user == null){
            return new ResponseEntity(
                    new CustomErrorType("user with username " + user.getUsername() + " not exist "),
                    HttpStatus.CONFLICT);
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
        SimpleMailMessage msg = new SimpleMailMessage();
        String password = userService.getRamdomPassword();

        user.setPassword(encoder.encode(password));
        userService.updateUser(user);

        String htmlMsg = "<h3>\n" +
                "              Mật khẩu mới tài khoản " + user.getUsername() +" của bạn là "+ password +",<a\n" +
                "                href=\"http://localhost:4200/auth/login\"\n" +
                "                >Ấn vào đây để đăng nhập</a\n" +
                "              >\n" +
                "            </h3>";
        message.setContent(htmlMsg, "text/html;charset=utf-8");
        helper.setTo(user.getEmail());
        helper.setSubject("CẤP MẬT KHẨU MỚI");
        javaMailSender.send(message);
        return new ResponseEntity<Users> (user, HttpStatus.OK);
    }

   @GetMapping("/authorization")
    public String author(){
        return "authorization";
   }

}
