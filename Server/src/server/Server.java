package server;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import DataLayer.*;
import compo.*;
import javax.swing.JOptionPane;
//import Login.*;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket serversocket = new ServerSocket(4700);
        Socket clientsocket = null;

        ArrayList<User> LoggedUsers = new ArrayList<User>();
        ArrayList<Challenge> AllChallenges =new ArrayList<Challenge>();
        ArrayList<AttemptedQuestion> CalResult=new ArrayList<AttemptedQuestion>();
        
        System.out.println("Server Starting...");

        while (true) {
            clientsocket = serversocket.accept();

            ObjectInputStream ois = new ObjectInputStream(clientsocket.getInputStream());
            Command cmd = (Command) ois.readObject();
            // User us=new User();

            if (cmd.CommandName.equals("register")) {
                User obj = (User) cmd;
                DALUser objDAL = new DALUser();
                boolean b1 = objDAL.register(obj);
                
                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(b1);

            }//IF Register       
            else if (cmd.CommandName.equals("LogIn")) {
                User obj = (User) cmd;
                DALUser objDAL = new DALUser();
                User us = objDAL.authenticate(obj);
                
                if(us.UserNumber>0)
                {
                    LoggedUsers.add(us);
                }

                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(us);

            } else if (cmd.CommandName.equals("#getData#")) {
                User obj = (User) cmd;
                DataLayer.DALUser objDAL = new DataLayer.DALUser();
                User us = objDAL.getData(obj);
                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(us);
                
                oos.close();
            }
            else if(cmd.CommandName.equals("#GetLoggedUsers#"))
            {
                //Request obj=(Request) cmd;
                
                ObjectOutputStream oos=new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(LoggedUsers);
                        
            }
            else if(cmd.CommandName.equals("#ChangePassword#"))
            {
              User obj = (User) cmd;
              DataLayer.DALUser objDal = new DataLayer.DALUser();
              boolean b1=objDal.changePassword(obj);
              
              ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
              oos.writeObject(b1);
              
            }
            else if(cmd.CommandName.equals("#SendChallenge#"))
            {
                Challenge obj= (Challenge)cmd;
                DALChallenge obj1=new DALChallenge();
                obj1.storeChallenge(obj);
            }
            else if(cmd.CommandName.equals("#anyChallenge#"))
            {
                Challenge obj =(Challenge) cmd;
                DALChallenge obj1=new DALChallenge();
                Challenge chl=obj1.anyRequest(obj);
                
                ObjectOutputStream oos = new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(chl);
            }
            else if(cmd.CommandName.equals("#UpdateStatus#"))
            {
                Challenge obj=(Challenge) cmd;
                DALChallenge objDAL=new DALChallenge();
                objDAL.update(obj);
                obj.StatusNo=1;
                DALQuestion objDQ=new DALQuestion();
                obj.Questions=objDQ.getQuestions(obj.CategoryID);
                
                AllChallenges.add(obj);
                
                ObjectOutputStream oos=new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(obj);
                
            }
            else if(cmd.CommandName.equals("#UpdateToCancel#"))
            {
                Challenge obj=(Challenge) cmd;
                DALChallenge objDAL=new DALChallenge();
                objDAL.update2(obj);
            }
            else if(cmd.CommandName.equals("#ChallengeAccepted#"))
            {
                Challenge obj=(Challenge)cmd;
//                DALChallenge objDAL=new DALChallenge();
//                Challenge obj1=objDAL.challengeAccepted(obj);
                for(Challenge challenge: AllChallenges)
                {
                    if(challenge.FromUserNo==obj.FromUserNo)
                    {
                        obj=challenge;
                        break;
                    }
                }
                
                ObjectOutputStream oos=new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(obj);
            }
            else if(cmd.CommandName.equals("#Attempt#"))
            {
                Challenge obj=(Challenge)cmd;
                DALChallenge objDAL=new DALChallenge();
                objDAL.attempt(obj);
                
                objDAL.finalUpdate(obj);
                int index = -1;

                for(int i=0;i<AllChallenges.size();i++)
                {
                    if(AllChallenges.get(i).ChallengeID==obj.ChallengeID)
                    {
                        index = i;
                        
                        break;
                    }
                }

                if(index!=-1)
                {
                    
                    if(AllChallenges.get(index).Attempted==false)
                    {
                        AllChallenges.get(index).Attempted=true;
                    }
                    else
                    {
                        AllChallenges.remove(index);
                    }
                }
            }
            else if(cmd.CommandName.equals("#GetResult#"))
            {
                Challenge obj=(Challenge) cmd;
                DALChallenge objDAL=new DALChallenge();
                CalResult=objDAL.result(obj);
                
                ObjectOutputStream oos=new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(CalResult);
            }
            else if(cmd.CommandName.equals("#LogOut#"))
            {
                User obj=(User) cmd;
                LoggedUsers.remove(obj.UserNumber);
            }
            else if(cmd.CommandName.equals("#GetChallengeData#"))
            {
                Challenge obj = (Challenge) cmd;
                
                obj = new DataLayer.DALChallenge().getChallengeData(obj.ChallengeID);
                
                ObjectOutputStream oos = new ObjectOutputStream (clientsocket.getOutputStream());
                oos.writeObject(obj);
                oos.close();
            }
            else if(cmd.CommandName.equals("#WaitingForResult#"))
            {
                //challenge obj1;
                
                Challenge obj=(Challenge) cmd;
                Challenge obj1=null;

                for(Challenge challenge: AllChallenges)
                {
                    if(challenge.ChallengeID==obj.ChallengeID)
                    {
                        obj1 = challenge;
                        break;
                    }
                }
                
                if(obj1==null)
                {
                    obj1=new Challenge();
                }
                
                ObjectOutputStream oos=new ObjectOutputStream(clientsocket.getOutputStream());
                oos.writeObject(obj1);
            }
            
            ois.close();
            clientsocket.close();

        }//While 
    }//Main

}//Class
