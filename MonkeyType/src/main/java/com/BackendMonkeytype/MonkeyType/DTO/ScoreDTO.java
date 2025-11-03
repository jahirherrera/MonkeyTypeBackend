package com.BackendMonkeytype.MonkeyType.DTO;

public class ScoreDTO {

    private int id;
    private int value;
    private String username;

    public ScoreDTO() {
    }

    public ScoreDTO(int value) {
        this.value = value;
    }

    public ScoreDTO(int value, String username) {
        this.value = value;
        this.username = username;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
