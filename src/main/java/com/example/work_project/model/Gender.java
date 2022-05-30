package com.example.work_project.model;

public enum Gender {
    MALE("Мужской"),
    FEMALE("Женский");

    private final String displayGender;

    Gender(String displayGender) {
        this.displayGender = displayGender;
    }

    public String getDisplayGender() {
        return displayGender;
    }
}
