package com.application;


import org.springframework.data.util.Pair;
import javax.servlet.http.Cookie;


public interface UserService {

    String registerNewUser(Users users);
    Pair<Users, Cookie> login(LoginDto loginDto)throws Exception;
    Cookie logout();
    Users getUser(String jwt) throws Exception;
    Users getUserInfo(String bearerToken ) throws Exception;



}
