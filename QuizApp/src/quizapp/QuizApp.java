package quizapp;

public class QuizApp 
{

    static int LoggedUserNo;
    static String LoggedUserPassword;
    
    static final String IPAddress = "127.0.0.1";
    static final int PortNo = 4700;
    
    
    public static void main(String[] args) 
    {

        Gateway obj = new Gateway();
        obj.setVisible(true);
    }

}
