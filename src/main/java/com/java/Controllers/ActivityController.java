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

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Component("ActivityController")
@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    UserAccessImpl userAccessImpl;
    ActivityDAO activityDAO = new ActivityDAO();
    @Autowired
    RateLimiterImpl rateLimiter;

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public int addActivity(String activity, String cookieId, String date)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId) && activity.length() > 0)
        {
            Activity currentActivity = new Activity(username,date,activity);
            activityDAO.addActivity(currentActivity);
            return activityDAO.getActivityId(currentActivity);
        }
        return -1;
    }


    @DeleteMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean removeActivity(int activityId, String cookieId)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId))
        {
            return activityDAO.removeActivity(activityId,username);
        }
        return false;
    }

    @PutMapping(value = "/mark", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean markCompleted(int activityId, String cookieId)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId))
        {
             return activityDAO.markActivity(activityId,username);
        }
        return false;
    }

    @PutMapping(value = "/unmark", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public boolean unMarkCompleted(int activityId, String cookieId)
    {
        String username = userAccessImpl.authenticate(cookieId);
        if(username != null && rateLimiter.isWithinRateLimit(cookieId))
        {
            return activityDAO.unMarkActivity(activityId,username);
        }
        return false;
    }

    @GetMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
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
