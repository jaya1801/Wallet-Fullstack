package com.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("auth")
@CrossOrigin(value = "http://localhost:4200/")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("user")
    public String registerNewUser(@RequestBody Users user){
       return this.userService.registerNewUser(user);
    }

    @PostMapping("login")
    public Users login(@RequestBody LoginDto loginDto, HttpServletResponse response) throws Exception {
        System.out.println("login "+loginDto.getUsername());
        Pair<Users, Cookie> pair = this.userService.login(loginDto);
        response.addCookie(pair.getSecond());
        return pair.getFirst();
    }
    @PostMapping("logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = this.userService.logout();
        response.addCookie(cookie);
        return "Logout Success !";
    }

    @GetMapping("getuser")
    public Users getUser(@CookieValue("jwt") String jwt) throws Exception {
        return this.userService.getUser(jwt);
    }


    @GetMapping("userinfo")
    public Users getUserInfo(@RequestHeader("Authorization") String bearerToken ) throws Exception {
        return this.userService.getUserInfo(bearerToken);
    }


}
