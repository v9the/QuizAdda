package quizapp;

import compo.Challenge;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Wait extends javax.swing.JDialog {

    int ChallengeId;
    
    public class waiting extends TimerTask
    {
        @Override
        public void run()
        {
            try {
                Challenge obj=new Challenge();
                obj.ChallengeID=ChallengeId;
                obj.CommandName="#WaitingForResult#";
                Socket socket=new Socket(QuizApp.IPAddress,QuizApp.PortNo);
                ObjectOutputStream oos =new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(obj);
                
                ObjectInputStream ois =new ObjectInputStream(socket.getInputStream());
                Challenge obj1=(Challenge)ois.readObject();
                
                if(obj1.ChallengeID==0)
                {
                    this.cancel();
                    Wait.this.dispose();
                    Result objR = new Result(null, true);
                    objR.showResult(ChallengeId);
                    //objR.setVisible(true);
                }
            } catch (IOException ex) {
                Logger.getLogger(Wait.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Wait.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Wait(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        Timer timer=new Timer();
        timer.schedule(new waiting(), 100, 2000);
        
    }

    public void startWaiting(int cid)
    {
        ChallengeId =cid;
        this.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Opponent still attempting test");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel1)
                .addContainerGap(230, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
