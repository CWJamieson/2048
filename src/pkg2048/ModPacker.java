/*Project: 2048
* Programmer: Christopher Jamieson
* Program: ModPacker.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: first window class, creates the window's panels, including
*   the mod selection and instructions. depending on what is selected, will fill
*   the main window method with different parameters. also contains the 
*   option to have banner ads at the bottom
*/
package pkg2048;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//extends frame
public class ModPacker extends JFrame{
    //create instace variables
    private JLabel msg;
    private JComboBox modList;
    private JCheckBox adds;
    private boolean enable=false;
    
    //constuctor
    public ModPacker()
    {
        //set message
        msg = new JLabel("Select a mod pack: ");
        //run combo box method and checkbox method
        addModList();
        addCheckBox();
        
        //create panels
        JPanel panel = new JPanel();
        JPanel buts = new JPanel();
        
        //add message to the above panel
        panel.add(msg);
        
        //add combo box to button panel
        buts.add(modList);
        buts.add(adds);
        
        //organize panels
        add(panel, BorderLayout.NORTH);
        add(buts, BorderLayout.CENTER);
        
    }//end of constuctor
    
    
    //combo box method
     private void addModList()
    {
        //creates list of mods
        final String [] mods = {"Base Game", "Teacher Mod", "Doctor who Mod", "Game of Thrones Mod"};
        //creates the combo box
        modList=new JComboBox(mods);
        modList.setSelectedIndex(mods.length-1);
        //creates action listener
        class addActionListener implements ActionListener
        {
            //finds selected option and sends said option as a
            //parameter to the main window class
            public void actionPerformed(ActionEvent event)
            {
               //finds selected option
                int n=0;
                JComboBox cb = (JComboBox)event.getSource();
                String mod = (String)cb.getSelectedItem();
                for(int i=0;i<mods.length;i++)
                {
                    if(mod.equals(mods[i]))
                    {
                        n=i+1;
                        break;
                    }
                }
                //closes window and creates main window
                dispose();
                JFrame frm = new Window(n,enable);
                //selects window size depending on adds
                if(enable)
                    frm.setSize(250, 490);
                else
                    frm.setSize(250, 400);
                frm.setVisible(true);
                frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frm.setLocationRelativeTo(null);
                frm.setResizable(false);
                frm.setTitle("2048 By CJWJ");
            }
        }
        //adds action listener
        ActionListener listener = new addActionListener();
        modList.addActionListener(listener);
    }//end of addModList
     
     //chekc box method, to enable or disable ads
    private void addCheckBox()
    {
        //create box
        adds = new JCheckBox("Enable ads?");
        
        //create item listener(creds to Matthew Flaschen via stack overflow
        //&& Oracle)
        class addItemListener implements ItemListener
        {
            public void itemStateChanged(ItemEvent event)
            {
                //switches between enables and disabled every click
                if(enable)
                    enable=false;
                else
                    enable=true;
            }
        }
        //add item listener
        ItemListener listener = new addItemListener();
        adds.addItemListener(listener);
    }//end of addCheckBox
}//end of ModPacker
