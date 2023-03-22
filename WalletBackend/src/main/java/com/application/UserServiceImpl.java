package com.application;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;


    public String registerNewUser( Users user){
        this.userRepository.save(user);
        return "User registration success.";
    }


    public Pair<Users,Cookie> login(LoginDto loginDto) throws Exception {

        // Create a user service and log in method
        Users user = this.userRepository.findByUsername(loginDto.getUsername());
        if(user == null) throw new Exception("User does not exists");
        if(! user.getPassword().equals(loginDto.getPassword()))
            throw new Exception("User password does not match");

        // JWT util
        String issuer = loginDto.getUsername();
        Date expiry= new Date(System.currentTimeMillis() + (1000 * 60 * 60 ));
        String jwt = Jwts.builder().setIssuer(issuer).setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256,"secretKey").compact();

        Cookie cookie = new Cookie("jwt",jwt);
        user.setJwt(jwt);
        return Pair.of(user,cookie);
    }

    public Cookie logout(){
        Cookie cookie = new Cookie("jwt","");
        return cookie;
    }


    public Users getUser(String jwt) throws Exception {
        if(jwt == null)
            throw new Exception("Unauthenticated !");
        // Jwt Util class
        Claims claim = null;
        String Username = null;
        try{
            claim = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(jwt).getBody();
            Username = claim.getIssuer();

        }
        catch (ExpiredJwtException e){
            throw new Exception("JWT got Expired please log in again.");

        }
        catch (SignatureException e){
            throw new Exception("JWT Signature Exception.");
        }
        catch (Exception e){
            throw  new Exception("Unauthenticated !");
        }

        return this.userRepository.findByUsername(Username);

    }

    // API with bearer token of JWT

    public Users getUserInfo(String bearerToken ) throws Exception {
        String jwt = bearerToken.substring(7);
        String Username = jwtUtil.validateJwtAndGetUsername(jwt);
        return this.userRepository.findByUsername(Username);

    }
}
