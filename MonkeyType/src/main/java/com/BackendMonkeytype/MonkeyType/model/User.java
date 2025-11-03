package com.BackendMonkeytype.MonkeyType.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fullname;
    @Column(unique = true)
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Score> scores;



    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String fullname, String username, String password, List<Score> scores) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.scores = scores;
    }

    public User(int id, String fullname, String username, String password, List<Score> scores) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.scores = scores;
    }

    public int getHighestScore(){
        if (scores.isEmpty()) return 0;

        int highest =0;

        for(Score score : scores){
            if(score.getValue() > highest){
                highest = score.getValue();
            }
        }

        return highest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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
}
