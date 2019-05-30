package DataLayer;

//import Components2.User;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public abstract class DBOperations {

    protected static Connection con = null;

    protected void makeConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-8USEU4B\\ChahatDataServer:53812;database=ProjectDB1;userName=sa;password=chahat@1");
    }

    protected void closeConnection() {
        try {
            con.close();
            con =null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
