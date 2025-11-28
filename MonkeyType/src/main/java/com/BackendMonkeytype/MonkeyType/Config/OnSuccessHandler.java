package com.BackendMonkeytype.MonkeyType.Config;

import com.BackendMonkeytype.MonkeyType.model.User;
import com.BackendMonkeytype.MonkeyType.repo.UserRepo;
import com.BackendMonkeytype.MonkeyType.service.JWTService;
import com.BackendMonkeytype.MonkeyType.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OnSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTService jwtService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        if(email==null) return;

        String name = oAuth2User.getAttribute("name");
        String username = email.substring(0,email.indexOf("@"));   //jahir123@

        User user = userRepo.findByEmail(email);

        if(user == null){
            user = new User(name, username,"passwordbydefautlt",email);
            userRepo.save(user);
        }

        String token = jwtService.generateToken(user.getUsername());

        Cookie cookie = new Cookie("token", token);

        cookie.setSecure(true); //HTTP is allowed
        cookie.setHttpOnly(true); // frontend cant read or modify it
        cookie.setMaxAge(60 * 60 * 24); //expiration time (1day)
        cookie.setPath("/"); //send all to endpoints
        cookie.setAttribute("SameSite", "None"); //cross-origin from frontend to backend\

        response.addCookie(cookie);

        response.sendRedirect("http://localhost:3000");

    }
}
