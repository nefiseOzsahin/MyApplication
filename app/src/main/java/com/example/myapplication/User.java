package com.example.myapplication;

public class User {

    private String kullaniciad;
    private String uid;

    public User( ) {

    }

    public User(String kullaniciad, String uid) {
        this.kullaniciad = kullaniciad;
        this.uid = uid;
    }

    public String getKullaniciad() {
        return kullaniciad;
    }

    public void setKullaniciad(String kullaniciad) {
        this.kullaniciad = kullaniciad;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
