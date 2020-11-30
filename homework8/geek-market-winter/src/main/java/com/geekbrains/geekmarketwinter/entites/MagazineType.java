package com.geekbrains.geekmarketwinter.entites;

import com.google.gson.annotations.SerializedName;

public enum MagazineType {
    NONE(0, "Тип не задан"),
    E_TYPE(1, "интернет-варинт"),
    OBJ_TYPE(2, "реальный магазин"),
    ;

    private final int id;
    private final String name;

    MagazineType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
