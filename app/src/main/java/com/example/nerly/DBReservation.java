package com.example.nerly;

public class DBReservation{
    public String title;
    public String waitNum;
    public String waitTime;
    public String boothLocation;

    public DBReservation(){

    }
    public DBReservation(String title, String waitNum, String waitTime, String boothLocation){
        this.title = title;
        this.waitNum = waitNum;
        this.waitTime = waitTime;
        this.boothLocation = boothLocation;
    }
}