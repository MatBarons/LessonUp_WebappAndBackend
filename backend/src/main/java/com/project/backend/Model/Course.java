package com.project.backend.Model;

public class Course{
    private String ID;
    private String name;
    private boolean isActive;

    public Course(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {return isActive;}

    public void setActive(boolean active) {
        isActive = active;
    }
}
