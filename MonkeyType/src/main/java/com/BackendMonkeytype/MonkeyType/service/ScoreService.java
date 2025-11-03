package com.BackendMonkeytype.MonkeyType.service;

import com.BackendMonkeytype.MonkeyType.DTO.ScoreDTO;
import com.BackendMonkeytype.MonkeyType.model.Score;
import com.BackendMonkeytype.MonkeyType.model.User;
import com.BackendMonkeytype.MonkeyType.repo.ScoreRepo;
import com.BackendMonkeytype.MonkeyType.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepo scoreRepo;

    @Autowired
    private UserService userService;


    public void addScore(ScoreDTO scoreDTO){

        if(scoreDTO.getValue()==0) return;

        User user = userService.getUserbyUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        scoreRepo.save(new Score(scoreDTO.getValue(),user));
    }

}
