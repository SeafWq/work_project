package com.example.work_project.model;

public enum Category {
    WORKING_SPECIALITY("Рабочая специальность"),
    SPECIALIST("Специалист"),
    EMPLOYEE("Служащий");

    private final String displayCategory;

    Category(String displayCategory) {
        this.displayCategory = displayCategory;
    }

    public String getDisplayCategory() {
        return displayCategory;
    }
}
