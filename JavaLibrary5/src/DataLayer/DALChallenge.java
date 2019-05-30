package DataLayer;

import compo.AttemptedQuestion;
import compo.Challenge;
import compo.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DALChallenge extends DBOperations {

    ArrayList<AttemptedQuestion> CalResult = new ArrayList<AttemptedQuestion>();

    public void storeChallenge(Challenge obj) {
        try {
            makeConnection();
            PreparedStatement ps = DBOperations.con.prepareStatement("Insert into Challenges values(getDate(),?,?,?,?)");
            ps.setInt(1, obj.FromUserNo);
            ps.setInt(2, obj.ToUserNo);
            ps.setInt(3, obj.CategoryID);
            ps.setInt(4, obj.StatusNo);

            ps.execute();

            //JOptionPane.showMessageDialog(null, "Challenge has been sent");
        } catch (Exception ex) {
            Logger.getLogger(DALChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Challenge anyRequest(Challenge obj) {
        Challenge obj1 = new Challenge();

        //boolean b=false;
        try {
            //Challenge obj=new Challenge();
            makeConnection();
            PreparedStatement ps = con.prepareStatement("Select C.ChallengeID,C.ChallengeDate,C.FromUserNo,FU.UserName as [FromUserName],C.ToUserNo,TU.UserName as [ToUserName],Cat.CategoryID,C.StatusNo from Challenges as [C],Users as [TU],Users as [FU],Categories as [Cat] where C.CategoryID=Cat.CategoryID and C.FromUserNo=FU.UserNumber and C.ToUserNo=TU.UserNumber and C.ToUserNo=? and C.StatusNo=0");
            ps.setInt(1, obj.ToUserNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj1.CategoryID = rs.getInt("CategoryID");
                obj1.ChallengeDate = rs.getString("ChallengeDate");
                obj1.ChallengeID = rs.getInt("ChallengeID");
                obj1.FromUserNo = rs.getInt("FromUserNo");
                obj1.ToUserNo = rs.getInt("ToUserNo");
                obj1.ToUserName = rs.getString("ToUserName");
                obj1.FromUserName = rs.getString("FromUserName");
            }
        } catch (Exception ex) {

        }

        return obj1;
    }

    public void update(Challenge obj) {
        try {
            makeConnection();
            PreparedStatement ps = con.prepareStatement("Update Challenges set StatusNo=1 where ChallengeID=?");
            ps.setInt(1, obj.ChallengeID);

            ps.execute();

        } catch (Exception ex) {

        }
    }

    public void update2(Challenge obj) {
        try {
            makeConnection();
            PreparedStatement ps = con.prepareStatement("Update Challenges set StatusNo=2 where ChallengeID=?");
            ps.setInt(1, obj.ChallengeID);

            ps.execute();

        } catch (Exception ex) {

        }
    }

    public Challenge challengeAccepted(Challenge obj) {
        Challenge obj1 = new Challenge();

        //boolean b=false;
        try {
            //Challenge obj=new Challenge();
            makeConnection();
            PreparedStatement ps = con.prepareStatement("Select C.ChallengeID,C.ChallengeDate,C.FromUserNo,FU.UserName as [FromUserName],C.ToUserNo,TU.UserName as [ToUserName],Cat.CategoryID,C.StatusNo from Challenges as [C],Users as [TU],Users as [FU],Categories as [Cat] where C.CategoryID=Cat.CategoryID and C.FromUserNo=FU.UserNumber and C.ToUserNo=TU.UserNumber and C.FromUserNo=? and C.StatusNo=1");
            ps.setInt(1, obj.FromUserNo);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj1.CategoryID = rs.getInt("CategoryID");
                obj1.ChallengeDate = rs.getString("ChallengeDate");
                obj1.ChallengeID = rs.getInt("ChallengeID");
                obj1.FromUserNo = rs.getInt("FromUserNo");
                obj1.ToUserNo = rs.getInt("ToUserNo");
                obj1.ToUserName = rs.getString("ToUserName");
                obj1.FromUserName = rs.getString("FromUserName");
            }
        } catch (Exception ex) {

        }

        return obj1;
    }

    public void attempt(Challenge obj) {
        try {
            makeConnection();

            PreparedStatement ps = con.prepareStatement("Insert into AttemptedQuestions values(?,?,?,?)");
            for (Question question : obj.Questions) {
                ps.setInt(1, question.QuestionID);
                ps.setInt(2, question.AttemptedOptionID);
                ps.setInt(3, obj.UserNo);
                ps.setInt(4, obj.ChallengeID);

                ps.execute();
            }
        } catch (Exception ex) {

        }
    }

    public void finalUpdate(Challenge obj) {
        //Challenge obj1=new Challenge();
        try {
            makeConnection();
            PreparedStatement ps = con.prepareStatement("Update challenges set StatusNo=? where challengeID=?");
            ps.setInt(1, obj.StatusNo);
            ps.setInt(2, obj.ChallengeID);

            ps.execute();
//         ResultSet rs=ps.executeQuery();
//         rs.next();
//         obj1.StatusNo=rs.getInt("StatusNo");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        //return obj;
    }

    public ArrayList<AttemptedQuestion> result(Challenge obj) {

        try {
            //int i=0;

            makeConnection();
            PreparedStatement ps = con.prepareStatement("select Q.QuestionText, AQ.OptionID ,O.OptionText, (Select OptionText from Options where QuestionID=AQ.QuestionID and IsAnswer=1) as [CorrectOptionText] , \n"
                    + "(select OptionID from Options where QuestionID=AQ.QuestionID and IsAnswer=1) as [CorrectOptionID] from Questions as [Q], AttemptedQuestions as[AQ],Options as [O]\n"
                    + "where AQ.ChallengeID=? and AQ.UserNumber=? and Q.QuestionID=AQ.QuestionID and O.OptionID=AQ.OptionID");
            ps.setInt(1, obj.ChallengeID);
            ps.setInt(2, obj.UserNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AttemptedQuestion obj1 = new AttemptedQuestion();
                obj1.QuestionText = rs.getString("QuestionText");
                obj1.OptionText = rs.getString("OptionText");
                obj1.OptionID = rs.getInt("OptionID");
                obj1.CorrectOptionID = rs.getInt("CorrectOptionID");
                obj1.CorrectOptionText = rs.getString("CorrectOptionText");

                CalResult.add(obj1);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return CalResult;
    }

    public compo.Challenge getChallengeData(int pChallengeId) {
        compo.Challenge obj = new Challenge();

        try {

            makeConnection();
            PreparedStatement ps = con.prepareStatement("Select C.ChallengeId, C.ChallengeDate, C.FromUserNo, FU.UserName as [FromUserName], C.ToUserNo, TU.UserName as [ToUserName], C.CategoryID\n" +
"From Challenges as [C], Users as [FU], Users as [TU] Where C.FromUserNo=FU.UserNumber and TU.UserNumber=C.ToUserNo and C.ChallengeID=?");
            
            ps.setInt(1, pChallengeId);
            
            ResultSet rs =  ps.executeQuery();
            
            rs.next();
            
            obj.ChallengeID = rs.getInt("ChallengeId");
            obj.ChallengeDate =  rs.getString("ChallengeDate");
            obj.FromUserNo =  rs.getInt("FromUserNo");
            obj.FromUserName =  rs.getString("FromUserName");
            obj.ToUserNo =  rs.getInt("ToUserNo");
            obj.ToUserName =  rs.getString("ToUserName");
            obj.CategoryID = rs.getInt("CategoryId");
            
            rs.close();
            closeConnection();
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return obj;
    }

}
