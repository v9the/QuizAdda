package compo;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable{
 public int QuestionID;
 public String QuestionText;
 public ArrayList<Option> Options=new ArrayList<Option>();
 public int AttemptedOptionID;
}
