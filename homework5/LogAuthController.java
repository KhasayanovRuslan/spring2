package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.LogAuth;
import com.geekbrains.geekmarketwinter.services.LogAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogAuthController {
    private LogAuthService logAuthService;

    @Autowired
    public void setLogAuthService(LogAuthService logAuthService) {
        this.logAuthService = logAuthService;
    }

//    @GetMapping("/login")
    public LogAuth saveLogAuth(LogAuth logAuth) {
        return logAuthService.save(logAuth);
    }
}
