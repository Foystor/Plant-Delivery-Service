package com.udacity.PersistencePractice.entity.inventory;

import javax.persistence.Entity;

@Entity
public class Shrub extends Plant {

    private double height;
    private double width;

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
