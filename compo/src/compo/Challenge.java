package compo;

import java.util.ArrayList;

public class Challenge extends Command {
    
    public int ChallengeID,FromUserNo,ToUserNo,CategoryID,StatusNo;
    public String ChallengeDate,FromUserName,ToUserName;
    
    public ArrayList<Question> Questions=null;
    public int UserNo;

    public boolean Attempted;
}
