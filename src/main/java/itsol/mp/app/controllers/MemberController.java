package itsol.mp.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/member1")
    public String helloMember(){
        return "hello member";
    }
}
