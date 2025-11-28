package com.BackendMonkeytype.MonkeyType.Controller;

import com.BackendMonkeytype.MonkeyType.DTO.UserDTO;
import com.BackendMonkeytype.MonkeyType.model.User;
import com.BackendMonkeytype.MonkeyType.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response) {return userService.verify(user, response);}

    @GetMapping("/getResults")
    public List<UserDTO> getAllUserResults(){
        return userService.getAllUserResults();
    }

    @PostMapping("/userLogOut")
    public String LoggingOut(HttpServletResponse response){
        return userService.loggingOut(response);
    }



    @GetMapping("/logged")
    public boolean verifyingLogged(HttpServletRequest request){
        return userService.verifyLogged(request);
    }
}
