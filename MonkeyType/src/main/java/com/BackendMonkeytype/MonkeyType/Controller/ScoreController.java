package com.BackendMonkeytype.MonkeyType.Controller;

import com.BackendMonkeytype.MonkeyType.DTO.ScoreDTO;
import com.BackendMonkeytype.MonkeyType.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    @PostMapping("/addScore")
    public void addingScore(@RequestBody ScoreDTO scoreDTO){
        scoreService.addScore(scoreDTO);
    }

}
