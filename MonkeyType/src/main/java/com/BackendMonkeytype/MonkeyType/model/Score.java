package com.BackendMonkeytype.MonkeyType.model;

import jakarta.persistence.*;

@Entity
@Table(name = "scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int value;

    @ManyToOne
    private User user;

    public Score() {
    }

    public Score(int value, User user) {
        this.value = value;
        this.user = user;
    }

    public Score(int id, int value, User user) {
        this.id = id;
        this.value = value;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
