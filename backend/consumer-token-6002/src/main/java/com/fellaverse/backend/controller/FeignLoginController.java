package com.fellaverse.backend.controller;

import com.fellaverse.backend.dto.UserDTO;
import com.fellaverse.backend.pojo.ResultData;
import com.fellaverse.backend.service.FeignAuthenticationService;
import com.fellaverse.backend.validator.ValidGroup;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class FeignLoginController {
    @Autowired
    private FeignAuthenticationService feignAuthenticationService;

    @PostMapping("/create")
    public String login(@Validated(value = ValidGroup.Crud.Query.class) @RequestBody UserDTO userDTO) {
        String result = feignAuthenticationService.login(userDTO);
        Assert.notNull(result, "Login Failed");
        return result;
    }
}
