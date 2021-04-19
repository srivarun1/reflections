package com.java.DAO;

import com.java.DatabaseConnection.DBConnectionImpl;
import com.java.Model.Activity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {
    public boolean addActivity(Activity activity)
    {
        try{
            PreparedStatement ps = DBConnectionImpl.getPreparedStatement("INSERT INTO ACTIVITY(USERNAME,DATE,ACTIVITY) VALUES(?,?,?)");
            ps.setString(1,activity.getUsername());
            ps.setString(2,activity.getDate());
            ps.setString(3,activity.getActivity());
            ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean removeActivity(int activityId,String username) {
        try{
            PreparedStatement ps = DBConnectionImpl.getPreparedStatement("DELETE FROM ACTIVITY WHERE ID = ? AND USERNAME = ?");
            ps.setInt(1,activityId);
            ps.setString(2,username);
            ps.executeUpdate();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean markActivity(int activityId, String username) {
        try{
            PreparedStatement ps = DBConnectionImpl.getPreparedStatement("UPDATE ACTIVITY SET COMPLETED = TRUE WHERE ID = ? AND USERNAME = ?");
            ps.setInt(1,activityId);
            ps.setString(2,username);
            ps.executeUpdate();
            return true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public List<Activity> getActivities(String username, String date) {
        List<Activity> activities = new ArrayList<>();

        try{
            PreparedStatement ps = DBConnectionImpl.getPreparedStatement("SELECT * FROM ACTIVITY WHERE USERNAME = ? AND DATE = ?");
            ps.setString(1,username);
            ps.setString(2,date);
            ResultSet rs = ps.executeQuery();

            while(rs.next())
            {
                activities.add(new Activity(rs.getInt("ID"),rs.getString("USERNAME"),rs.getString("DATE"),rs.getString("ACTIVITY"),rs.getBoolean("COMPLETED")));
            }
            return activities;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}
