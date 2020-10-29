package com.geekbrains.geekmarketwinter.services;

import com.geekbrains.geekmarketwinter.entites.LogAuth;
import com.geekbrains.geekmarketwinter.repositories.LogAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogAuthService {
    private LogAuthRepository logAuthRepository;

    @Autowired
    public void setLogAuthRepository(LogAuthRepository logAuthRepository) {
        this.logAuthRepository = logAuthRepository;
    }

    public LogAuth save(LogAuth logAuth) {
        return logAuthRepository.save(logAuth);
    }
}
