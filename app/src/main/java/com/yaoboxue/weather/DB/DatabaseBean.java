package com.yaoboxue.weather.DB;

public class DatabaseBean {
    private int id;
    private String city;
    private String content;

    public DatabaseBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DatabaseBean(int id, String city, String content) {
        this.id = id;
        this.city = city;
        this.content = content;
    }
}
