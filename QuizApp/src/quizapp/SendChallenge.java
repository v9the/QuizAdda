package quizapp;

import compo.Challenge;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.util.Timer;
import java.util.TimerTask;

public class SendChallenge extends javax.swing.JDialog {

    public void start(int UserNumber, int CategoryID) {
        //this.setVisible(true);
        try {
            Socket socket = new Socket(QuizApp.IPAddress, QuizApp.PortNo);
            Challenge obj = new Challenge();
            //obj.ChallengeDate="1/12/2018";
            obj.CategoryID = CategoryID;
            obj.FromUserNo = QuizApp.LoggedUserNo;
            obj.ToUserNo = UserNumber;
            obj.StatusNo = 0;
            obj.CommandName = "#SendChallenge#";

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(obj);

        } catch (IOException ex) {
            Logger.getLogger(SendChallenge.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class Accepted extends TimerTask {

        @Override
        public void run() {
            try {
                Socket socket = new Socket(QuizApp.IPAddress, QuizApp.PortNo);
                Challenge obj = new Challenge();
                obj.FromUserNo = QuizApp.LoggedUserNo;
                obj.CommandName = "#ChallengeAccepted#";

                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(obj);

                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Challenge obj1 = (Challenge) ois.readObject();

                if (obj1.StatusNo == 1) {
                    //JOptionPane.showMessageDialog(null, "Hi");
                    this.cancel();
                    SendChallenge.this.dispose();
                    StartChallenge objSC = new StartChallenge(null, true);
                    objSC.start(obj1);
                    //objSC.setVisible(true);
                }

            } catch (IOException ex) {
                Logger.getLogger(SendChallenge.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SendChallenge.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public SendChallenge(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Timer timer = new Timer();
        timer.schedule(new Accepted(), 100, 2000);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Waiting for response...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
