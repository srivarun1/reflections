package com.java.UserManagement;

import com.java.Model.Credential;

public interface UserAccess {

    public String authenticate(String cookieId);

    public String generateCookie(String username);

}
