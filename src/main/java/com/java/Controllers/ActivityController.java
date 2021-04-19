package com.java.Controllers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java.DAO.ActivityDAO;
import com.java.Model.Activity;
import com.java.UserManagement.RateLimiterImpl;
import com.java.UserManagement.UserAccessImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Component("ActivityCOntroller")
@RestController
@RequestMapping("/Activity")
public class ActivityController {

    @Autowired
    UserAccessImpl userAccessImpl;
    ActivityDAO activityDAO = new ActivityDAO();
    @Autowired
    RateLimiterImpl rateLimiter;

    @PostMapping(value = "Add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean addActivity(String activity, String cookieId, String date)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId))
        {
            return activityDAO.addActivity(new Activity(username,date,activity));
        }
        return false;
    }


    @PostMapping(value = "Delete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean removeActivity(int activityId, String cookieId)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId))
        {
            return activityDAO.removeActivity(activityId,username);
        }
        return false;
    }

    @PostMapping(value = "Mark", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean markCompleted(int activityId, String cookieId)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId))
        {
            return activityDAO.markActivity(activityId,username);
        }
        return false;
    }

    @PostMapping(value = "Get", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @JsonSerialize
    public List<Activity> getCurrentActivities(String cookieId, String date)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId))
        {
            return activityDAO.getActivities(username,date);
        }
        return null;
    }
}
