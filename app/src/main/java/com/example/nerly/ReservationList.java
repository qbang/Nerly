package com.example.nerly;

import android.graphics.drawable.Drawable;

public class ReservationList {
    private String boothName;
    private String location;
    private String waitNumber;
    private String waiting;
    private Drawable cancel;

    public ReservationList(){

    }
    public String getBoothName(){
        return boothName;
    }
    public void setBoothName(String boothName){
        this.boothName = boothName;
    }
    public String getLocation(){
        return location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public String getWaitNumber(){
        return waitNumber;
    }
    public void setWaitNumber(String waitNumber){
        this.waitNumber = waitNumber;
    }
    public String getWaiting(){
        return waiting;
    }
    public void setWaiting(String waiting){
        this.waiting = waiting;
    }
    public Drawable getCancel(){
        return cancel;
    }
    public void setCancel(Drawable cancel){
        this.cancel = cancel;
    }
}
