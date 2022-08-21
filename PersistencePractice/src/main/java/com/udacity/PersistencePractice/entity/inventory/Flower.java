package com.udacity.PersistencePractice.entity.inventory;

import javax.persistence.*;

@Entity
public class Flower extends Plant {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
