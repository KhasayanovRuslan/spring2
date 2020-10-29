package com.geekbrains.geekmarketwinter.entites;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "logging_auth")
@Data
public class LogAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    public LogAuth(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return message;
    }

}
