/*Project: 2048
* Programmer: Christopher Jamieson
* Program: Restart.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: Creates the restart screen, with a confirmation button
*   and development options
*/
package pkg2048;
import java.awt.event.*;
import javax.swing.*;
public class Restart extends JFrame{
    //creates instance variables
    private JLabel msg;
    private JButton yes;
    private JButton no;
    private JButton other;
    private JButton showcase;
    private JButton win;
    private JButton quit;
    private int mod;
    private JLabel [] ex = new JLabel[11];
    private ImageIcon [] icons = new ImageIcon[12];
    private String [] tooltips = new String [11];
    JFrame oldFrame;
    JPanel panel = new JPanel();
    
    //constuctor
    public Restart(JFrame closeme, boolean enhance, int mod)
    {
        //sets mod to instance variable by same name
        this.mod=mod;
        //creates JLabel
        msg = new JLabel("Are you sure?");
        //sets Main window to instance variable to allow remote closing
        //(Creds to Stewart)
        oldFrame = closeme;
        //run basic button creating methods
        yesBut();
        noBut();
        otherBut();
        
        //if other is not selected
        if(!enhance){
            //add basic buttons
            panel.add(yes);
            panel.add(no);
            panel.add(other);
        }
        
        //if other was selected
        if(enhance)
        {
            //create advanced buttons ad methods
            quitBut();
            showCaseBut();
            winBut();
            panel.add(quit);
            panel.add(showcase);
            //create showcase labels
            for(int i=0;i<11;i++)
            {
                ex[i] = new JLabel("");
                panel.add(ex[i]);
            }
            panel.add(win);
        }
        
        //add panel to the frame
        add(panel);
    }//end of constuctor
    
    //method to create the basic yes button, reloads the entire game upon click
    public void yesBut()
    {
        //creates button
        yes=new JButton("Yes");
        //creates actionlistenerclass
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //closes current and main window
                dispose();
                oldFrame.dispose();
                //creates mod packer window
                //create frame andset size
                JFrame frm = new ModPacker();
                frm.setSize(400, 200);
                //make visible and close on exit
                frm.setVisible(true);
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                //set to center of screen and lock size
                frm.setLocationRelativeTo(null);
                frm.setResizable(false);
                //set title
                frm.setTitle("Select a Pack");
            }
        }//end of action listener class
        //creates/adds actionlistener
        ActionListener listener = new addActionListener();
        yes.addActionListener(listener);
    }//end of yesBut
    
    
    //method to create the basic button no, which closes the restart window
    public void noBut()
    {
        //creates Button
        no=new JButton("No");
        //create action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //closes current wndow
                dispose();
            }
        }//end of action listener class
        //creates/ adds action listener
        ActionListener listener = new addActionListener();
        no.addActionListener(listener);
    }//end of noBut
    
    //method to create the basic button other, which closes the main window
    //and loads dev options
    public void otherBut()
    {
        //creates button
        other=new JButton("Other");
        
        //creates action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //runs adder method to recreate the screen
                adder();
                
            }
        }//end of action listener class
        //adds/creates action listener
        ActionListener listener = new addActionListener();
        other.addActionListener(listener);
    }//end of otherBut
    
    //method to close all frame and load development options
    public void adder()
    {
        //close restart window
        dispose();
        //close main window
        oldFrame.dispose();
        //create new "enhanced" restart window
        JFrame frm = new Restart(this, true, mod);
        frm.setSize(250, 400);
        frm.setVisible(true);
        frm.setLocationRelativeTo(this);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setResizable(false);
        frm.setTitle("2048 By CJWJ");
        //runs showcase to create labels
        showCaseBut();  
    }//end of adder
    
    //method to create a set of labels with icons and tool tips to display
    //all playable pictures
    public void showCaseBut()
    {
        //create button
        showcase=new JButton("Showcase");
        //create action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //fetches pictures from mod object
                Mod mod1 = new Mod(mod);
                //sets icons and tooltips to instance variables
                icons = mod1.getIcons();
                tooltips = mod1.getTools();
                //loads icons to labels and assings tool tips
                if(mod==2||mod==3||mod==4)
                {
                    for(int i=0;i<10;i++)
                    {
                        ex[i].setIcon(icons[i]);
                        ex[i].setToolTipText(tooltips[i]);
                    }
                }
            }
        }//end of action listener class
        //adds/creates action listener
        ActionListener listener = new addActionListener();
        showcase.addActionListener(listener);
    }//end of showCaseBut
    
    //method to create the win Button, which automatically wins the game
    public void winBut()
    {
        //creates button
        win=new JButton("AutoWin");
        //creates action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //close all frames
                dispose();
                oldFrame.dispose();
                //creates victory window
                JFrame frm = new win(mod, true);
                frm.setSize(300, 200);
                frm.setVisible(true);
                frm.setLocationRelativeTo(null);
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.setResizable(false);
                frm.setTitle("wow you're amazing");
            }
        }//end of actionlistener class
        //adds/creates action listener
        ActionListener listener = new addActionListener();
        win.addActionListener(listener);
    }//end of winBut
    
    //method to create the quit button, ending the program
    private void quitBut()
    {
        //creates button
        quit = new JButton("Quit");
        //creates action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                //ends the program
                System.exit(0);
            }
        }//end of action listener class
        //adds/creates action listener
        ActionListener listener = new addActionListener();
        quit.addActionListener(listener);
                
    }//end of quitBut
}//end of Restart
