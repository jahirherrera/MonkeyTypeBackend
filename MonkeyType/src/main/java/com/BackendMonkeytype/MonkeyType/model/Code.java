package com.BackendMonkeytype.MonkeyType.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    //private Date createAt;
    //private Date expireAt;
    private String email;
    private boolean used;

    public Code() {
    }

    public Code(String code, String email, boolean used) {
        this.code = code;
        this.email = email;
        this.used = used;
    }

    public Code(int id, String code, String email, boolean used) {
        this.id = id;
        this.code = code;
        this.email = email;
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
