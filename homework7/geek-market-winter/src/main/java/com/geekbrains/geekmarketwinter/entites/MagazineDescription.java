package com.geekbrains.geekmarketwinter.entites;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MagazineDescription {

    private String name;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private MagazineType type;

    public MagazineDescription(String name, MagazineType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MagazineType getType() {
        return type;
    }

    public void setType(MagazineType type) {
        this.type = type;
    }
}
