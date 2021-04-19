package com.java.DAO;

import com.java.DatabaseConnection.DBConnectionImpl;
import com.java.Model.Credential;
import org.springframework.context.annotation.Configuration;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserAccessDAO {


    public UserAccessDAO(){}

    public boolean addCredential(Credential credential)
    {
        try {
            PreparedStatement ps = DBConnectionImpl.getPreparedStatement("INSERT INTO USERS VALUES(?,MD5(?))");
            ps.setString(1,credential.getUsername());
            ps.setString(2,credential.getPassword());
            ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();;
            return false;
        }
        return true;
    }

    public boolean validateCredential(Credential credential)
    {
        try{
            PreparedStatement ps = DBConnectionImpl.getPreparedStatement("SELECT COUNT(*) FROM USERS WHERE USERNAME = ? AND PASSWORD = MD5(?)");
            ps.setString(1,credential.getUsername());
            ps.setString(2,credential.getPassword());
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                if(rs.getInt(1) == 1)
                {
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePassword(Credential credential, String newPassword)
    {
        try{
            PreparedStatement ps = DBConnectionImpl.getPreparedStatement("UPDATE USERS SET PASSWORD = MD5(?) WHERE USERNAME = ?");
            ps.setString(1,newPassword);
            ps.setString(2,credential.getUsername());
            ps.executeUpdate();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
