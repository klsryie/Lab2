package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String text, anons, title;

    public Long GetId() {
        return id;
    }

    public String GetTitle() {
        return title;
    }

    public void SetTitle(String title) {
        this.title = title;
    }

    public String GetAnons() {
        return anons;
    }

    public void SetAnons(String anons) {
        this.anons = anons;
    }

    public String GetText() {
        return text;
    }

    public void SetText(String text) {
        this.text = text;
    }

    public Post() {

    }

    public Post(String title, String anons, String text) {
        this.title = title;
        this.anons = anons;
        this.text = text;
    }

}
