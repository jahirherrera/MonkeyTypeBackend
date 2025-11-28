package com.BackendMonkeytype.MonkeyType.Controller;

import com.BackendMonkeytype.MonkeyType.DTO.CodeDTO;
import com.BackendMonkeytype.MonkeyType.service.CodeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CodeController {

    @Autowired
    private CodeService codeService;

    @PostMapping("/sendingCode")
    public String sendingCode(@RequestBody CodeDTO codeDTO){
        return codeService.sendingCode(codeDTO);
    }

    @PostMapping("/verifying")
    public String verifyingCode(@RequestBody CodeDTO codeDTO, HttpServletResponse response){
        return codeService.verifying(codeDTO, response);
    }




}
