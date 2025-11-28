package com.BackendMonkeytype.MonkeyType.service;

import com.BackendMonkeytype.MonkeyType.DTO.UserDTO;
import com.BackendMonkeytype.MonkeyType.model.User;
import com.BackendMonkeytype.MonkeyType.repo.UserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder;

    public String loggingOut(HttpServletResponse response){

        Cookie cookie = new Cookie("token", null);

        cookie.setSecure(true); //HTTP is allowed
        cookie.setHttpOnly(true); // frontend cant read or modify it
        cookie.setMaxAge(0); //expiration time (1day)
        cookie.setPath("/"); //send all to endpoints
        cookie.setAttribute("SameSite", "None"); //cross-origin from frontend to backend\

        response.addCookie(cookie);
        return "cookie deleted";

    }

    public boolean verifyLogged(HttpServletRequest request){
        return request.getUserPrincipal() != null;
    }

    public UserService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String addUser(User user){

        User username = userRepo.findByUsername(user.getUsername());

        if (username != null) return "username already taken";

        User email = userRepo.findByEmail(user.getEmail());

        if (email != null) return "User with that email already created";

        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "success";
    }

    public User getUserbyUsername(String username){
        try {
            return userRepo.findByUsername(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changePassword(UserDTO userDTO){
        User user = userRepo.findByUsername(userDTO.getUsername());

        String newpassword = encoder.encode(userDTO.getPassword());

        user.setPassword(newpassword);
        userRepo.save(user);
    }

    public String verify(User user, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if (authentication.isAuthenticated()) {

            Cookie cookie = new Cookie("token", jwtService.generateToken(user.getUsername()));

            cookie.setSecure(true); //HTTP is allowed
            cookie.setHttpOnly(true); // frontend cant read or modify it
            cookie.setMaxAge(60 * 60 * 24); //expiration time (1day)
            cookie.setPath("/"); //send all to endpoints
            cookie.setAttribute("SameSite", "None"); //cross-origin from frontend to backend\

            response.addCookie(cookie);

            return "login successful";

        }
        return "Failed";
    }

    public List<UserDTO> getAllUserResults(){
        List<User> users = userRepo.findAll();

        users.sort((a,b)->Integer.compare(b.getHighestScore(), a.getHighestScore()));

        List<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            userDTOS.add(new UserDTO(user));
        }

        return userDTOS;
    }
}
