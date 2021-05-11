package com.java.Controllers;

import com.api.reflections.ReflectionsApplication;
import com.java.DAO.UserAccessDAO;
import com.java.Model.Credential;
import com.java.UserManagement.UserAccess;
import com.java.UserManagement.UserAccessImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component("ReportController")
@RestController
@RequestMapping("/access")

public class AccessController {

    @Autowired
    UserAccess userAccess;


    static final Logger logger = LoggerFactory.getLogger(ReflectionsApplication.class);

    UserAccessDAO userAccessDAO = new UserAccessDAO();

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "signin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String validateCred(Credential credential)
    {
         if(userAccessDAO.validateCredential(credential))
         {
             return userAccess.generateCookie(credential.getUsername());
         }
         return "Invalid Credential";
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "autosignin", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String validateCookie(String cookie)
    {
        String username = userAccess.authenticate(cookie);
        if(username != null)
        {
            return username;
        }

        return "Invalid Credential";
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean addUser(Credential credential)
    {
        return userAccessDAO.addCredential(credential);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "updatepassword", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean updatePassword(Credential credential, String newPassword)
    {

        if(userAccessDAO.validateCredential(credential))
        {
            return userAccessDAO.updatePassword(credential, newPassword);
        }
        return false;
    }
}


