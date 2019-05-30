package DataLayer;

import compo.User;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import quizapp.*;

public class DALUser extends DBOperations {

    public boolean register(User obj) {
        //User obj=new User();

        try {
            makeConnection();
            PreparedStatement ps = DBOperations.con.prepareStatement("Insert into Users values(?,?,?)");
            ps.setString(1, obj.UserID);
            ps.setString(2, obj.UserName);
            ps.setString(3, obj.UserPassword);

            ps.execute();
            
            closeConnection();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return true;
    }//Registerfunction
    public User authenticate(User obj)
    {
        //makeConnection();
         boolean b1=false;
         User us= new User();
            try {
                makeConnection();
                PreparedStatement ps=DBOperations.con.prepareStatement("Select * from Users where (UserID=? and UserPassword=?)");
                ps.setString(1,obj.UserID );
                ps.setString(2, obj.UserPassword);
                //System.out.println(obj.UserNumber);
             try (ResultSet rs = ps.executeQuery()) {
                 b1=rs.next();
                 if(b1)
                 {
                     
                     us.UserNumber=rs.getInt("UserNumber");
                     us.UserID=rs.getString("UserID");
                     us.UserName=rs.getString("UserName");
                     us.UserPassword=rs.getString("UserPassword");
                     //JOptionPane.showMessageDialog(null, us.UserNumber);
                     
                 }
                //JOptionPane.showMessageDialog(null, us.UserNumber);
             }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            return us ;
                       
}
    public User getData(User obj)
    {
        try {
            makeConnection();
            PreparedStatement ps = DBOperations.con.prepareStatement("Select * from Users where UserNumber=?" );
            ps.setInt(1,obj.UserNumber);
            ResultSet rs=ps.executeQuery();
            rs.next();
            
            obj.UserNumber = rs.getInt("UserNumber");
            obj.UserID=rs.getString("UserId");
            obj.UserName = rs.getString("UserName");
            obj.UserPassword= rs.getString("UserPassword");
        } catch (Exception ex) {
            Logger.getLogger(DALUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    public boolean changePassword(User obj)
    {
        try
        {
            makeConnection();
            PreparedStatement ps = DBOperations.con.prepareStatement("Update Users set UserPassword=? where UserNumber=?");
            ps.setString(1, obj.UserPassword);
            ps.setInt(2, obj.UserNumber);
            
            ps.execute();
            
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        
        }
        return true;
    }
    
}
