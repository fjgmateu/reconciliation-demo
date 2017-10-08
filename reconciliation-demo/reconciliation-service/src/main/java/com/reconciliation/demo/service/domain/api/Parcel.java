package com.reconciliation.demo.service.domain.api;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *FJGMATEU
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Parcel {

    private double weight;
    private double width;
    private double height;
    private double lenght;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }


    public void setHeight(double height) {
        this.height = height;
    }

    public double getLenght() {
        return lenght;
    }

    public void setLenght(double lenght) {
        this.lenght = lenght;
    }


    @java.lang.Override
    public java.lang.String toString() {
        return "Parcel{" +
                "weight=" + weight +
                ", width=" + width +
                ", height=" + height +
                ", lenght=" + lenght +
                '}';
    }
}

