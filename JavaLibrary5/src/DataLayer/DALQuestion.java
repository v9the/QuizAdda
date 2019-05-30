
package DataLayer;

import compo.Option;
import compo.Question;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class DALQuestion extends DBOperations {
    
    public ArrayList<Question> getQuestions(int CID)
{
	ArrayList<Question> All = new ArrayList<Question>();

	try
	{
		makeConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select top 5 * From Questions Where CategoryID=" +  CID +" order by NEWID()");


		Question q = null;
		String ids ="";


		while(rs.next())
		{
			q = new Question();

			q.QuestionID = rs.getInt("QuestionID");
			q.QuestionText = rs.getString("QuestionText");

			All.add(q);
			ids = ids+q.QuestionID +",";
		}//While

		rs.close();








		ids = ids.substring(0,ids.length()-1);

		rs = st.executeQuery("SElect * From Options Where QuestionID IN (" + ids +")"  );

		Option opt;

		int qid;

		while(rs.next())
		{
			opt = new Option();

			opt.OptionID = rs.getInt("OptionId");
			opt.OptionText = rs.getString("OptionText");

			qid = rs.getInt("QuestionID");

			for(Question ques : All)
			{
				if(ques.QuestionID==qid)
				{
					ques.Options.add(opt);
					break;
				}
			}			

		}//While

	}//try
	catch(Exception ex)
	{
		System.out.println(ex);
	}	

	return All;
}
}
