package com.backend.systemdesign.ai.model;

public class Question {
    private String id;
    private String title;
    private String description;

    public Question(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
