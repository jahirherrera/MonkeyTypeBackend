package com.BackendMonkeytype.MonkeyType.service;

import com.BackendMonkeytype.MonkeyType.DTO.CodeDTO;
import com.BackendMonkeytype.MonkeyType.model.Code;
import com.BackendMonkeytype.MonkeyType.model.User;
import com.BackendMonkeytype.MonkeyType.repo.CodeRepo;
import com.BackendMonkeytype.MonkeyType.repo.UserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class CodeService {


    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    JWTService jwtService;

    @Autowired
    private CodeRepo codeRepo;

    public String sendingCode(CodeDTO codeDTO){

        User user = userRepo.findByUsername(codeDTO.getUsername());


        if(user == null) return "user not found";

        codeRepo.deleteAllByEmail(user.getEmail());

        String email = user.getEmail();
        String code = generateCode();

        codeRepo.save(new Code(code,email,false));


        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject("Password Recover Code. Do not reply or share with anyone");
        message.setText("This is the 6-digits code: "+ code);

        javaMailSender.send(message);
        return("Code send!");
    }

    public String generateCode() {
        int code = (int)(Math.random() * 900000) + 100000; // always 6 digits
        return String.valueOf(code);
    }

    public String verifying(CodeDTO codeDTO, HttpServletResponse response){

        User user = userRepo.findByUsername(codeDTO.getUsername());

        Code code = codeRepo.findByEmail(user.getEmail());

        if(code.getCode().equals(codeDTO.getCode()) && !code.isUsed()){
            code.setUsed(true);
            codeRepo.save(code);


            Cookie cookie = new Cookie("token", jwtService.generateToken(user.getUsername()));

            cookie.setSecure(true); //HTTP is allowed
            cookie.setHttpOnly(true); // frontend cant read or modify it
            cookie.setMaxAge(60 * 60 * 24); //expiration time (1day)
            cookie.setPath("/"); //send all to endpoints
            cookie.setAttribute("SameSite", "None"); //cross-origin from frontend to backend\

            response.addCookie(cookie);

            return "valid";
        }

        return "Invalid code";
    }

}
