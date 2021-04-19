package com.java.Model;

public class Credential {
    private String username;
    private String password;

    public Credential(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(){
        this.username = username;
    }

    public void setPassword(){
        this.password = password;
    }

}
