package com.BackendMonkeytype.MonkeyType.DTO;

import com.BackendMonkeytype.MonkeyType.model.User;

public class UserDTO {

    private int id;
    private String username;
    private String password;
    private int maxValue;


    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDTO(User user) {
        this.username = user.getUsername();
        this.maxValue = user.getHighestScore();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
