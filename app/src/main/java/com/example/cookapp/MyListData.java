package com.example.cookapp;

public class MyListData {
    private String name;
    private String cookware;
    private String device;
    private String power;
    private String minutes;
    private String rating;

    public MyListData(String name,
             String cookware,
             String device,
             String power,
             String minutes,
             String rating){

        this.name = name;
        this.cookware = cookware;
        this.device = device;
        this.power = power;
        this.minutes = minutes;
        this.rating = rating;
    }
    public MyListData(){
        this.name = "";
        this.cookware = "";
        this.device = "";
        this.power = "";
        this.minutes = "";
        this.rating = "";
    }

    public String getCookware() {
        return cookware;
    }

    public String getDevice() {
        return device;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getName() {
        return name;
    }

    public String getPower() {
        return power;
    }

    public String getRating() {
        return rating;
    }

    public void setCookware(String cookware) {
        this.cookware = cookware;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
