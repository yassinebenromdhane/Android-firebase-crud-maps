package com.example.miniprojet.Model;

import java.util.Date;

public class Event {
    String id;
    String name ;
    String desc;
    String date;
    String place;
    String image;

    public Event() {
    }


    public Event(String id ,String name, String desc, String date, String place, String image) {
       this.id = id;
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.place = place;
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", date=" + date +
                ", image='" + image + '\'' +
                '}';
    }
}
