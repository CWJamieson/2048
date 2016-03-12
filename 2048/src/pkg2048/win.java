/*Project: 2048
* Programmer: Christopher Jamieson
* Program: win.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: creates the win frame, which displays a congradulatory
*   message and give the user the option to continue or quit, while rotating
*   through any icons available
*/
package pkg2048;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class win extends JFrame{
    //create instance variables
    private JLabel grats;
    private JButton quit;
    private JButton cont;
    JLabel pic;
    String sp [], winStatement;
    //constuctor
    public win(int mod, boolean hacked)
    {
        //creates labels
        winStatement = "Congradulations, you've won!";
        //splits win statement for rebuilding
        sp = winStatement.split(" ");
        grats = new JLabel("");
        winStatement="";
        
        pic = new JLabel("");
        //runs button methods
        quitBut();
        contBut();
        //checks if the user cheated, and does not allow a cheater to continue
        cont.setEnabled(!hacked);
        //starts rotation through icons
        Cycle r1 = new Cycle(pic, mod);
        Thread rotate = new Thread(r1);
        rotate.start();
        
        //creates panels
        JPanel msg = new JPanel();
        JPanel buts = new JPanel();
        JPanel picpan = new JPanel();
        
        //adds components to panels
        msg.add(grats);
        buts.add(quit);
        buts.add(cont);
        picpan.add(pic);
        
        //adds panels to frame
        add(msg, BorderLayout.NORTH);
        add(picpan, BorderLayout.CENTER);
        add(buts, BorderLayout.SOUTH);
        Split();
    }//end of constuctor
    
    //method to set the picture on the frame to a certain icon
    public void setpic(ImageIcon icon)
    {
        pic.setIcon(icon);
    }//end of setpic'
    
    //method to create the quit button, ending the program
    private void quitBut()
    {
        //creates button
        quit = new JButton("Quit");
        //creates acton listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                //ends program
                System.exit(0);
            }
        }//end of action listener class
        //adds/creates action listener
        ActionListener listener = new addActionListener();
        quit.addActionListener(listener);
                
    }//end of quitBut
    
    //method to create the continue Button, closing the win window
    private void contBut()
    {
        //creates button
        cont = new JButton("Continue");
        //creates the actionlistener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                //closes window
                dispose();
                    
            }
        }//end of action listener class
        //add/creates action listener
        ActionListener listener = new addActionListener();
        cont.addActionListener(listener);
                
    }//end of contBut
    //split method for rebuilding broken text
    public void Split()
    {
        //loops for rebuilding
        for(int i=0;i<sp.length+1;i++)
        {
            for(int o=0;o<i;o++)
            {
                winStatement = winStatement + " " + sp[o];
            }
            grats.setText(winStatement);
            winStatement = "";
        }
    }//end of split
}//end of win
