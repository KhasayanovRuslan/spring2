package com.geekbrains.geekmarketwinter.controllers;

import com.geekbrains.geekmarketwinter.entites.MagazineDescription;
import com.geekbrains.geekmarketwinter.entites.MagazineType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/magazine")
public class MagazineController {

    @GetMapping("/type")
    public MagazineDescription getMagazine() {
        return new MagazineDescription("name1", MagazineType.OBJ_TYPE);
    }

}
