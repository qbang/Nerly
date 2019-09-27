package com.example.nerly;

import android.graphics.drawable.Drawable;

public class TastyBoothList {
    private Drawable booth_img;
    private String booth_title;
    private String booth_expla;
    private String booth_num;
    private String more_info;

    public TastyBoothList(){

    }
    public Drawable getBooth_img(){
        return booth_img;
    }
    public void setBooth_img(Drawable booth_img){
        this.booth_img = booth_img;
    }
    public String getBooth_title(){
        return booth_title;
    }
    public void setBooth_title(String booth_title){
        this.booth_title = booth_title;
    }
    public String getBooth_expla(){
        return booth_expla;
    }
    public void setBooth_expla(String booth_expla){
        this.booth_expla = booth_expla;
    }
    public String getBooth_num(){
        return booth_num;
    }
    public void setBooth_num(String booth_num){
        this.booth_num = booth_num;
    }
    public String getMore_info(){
        return more_info;
    }
    public void setMore_info(String more_info){
        this.more_info = more_info;
    }
}
