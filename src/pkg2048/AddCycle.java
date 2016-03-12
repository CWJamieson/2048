/*Project: 2048
* Programmer: Christopher Jamieson
* Program: AddCycle.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: the addcycle class rotates through banner ads at the
    bottom of the main window to maximize revenue (Threading Creds to Stewart)
*/
package pkg2048;
import javax.swing.*;
import java.net.*;
public class AddCycle implements Runnable{

    //creates instance variables
    private ImageIcon [] pics = new ImageIcon [5];
    JLabel pic = new JLabel("");
    JLabel window;
    
    //constructor
    public AddCycle(JLabel window)
    {
        //assings ad window to a instance variable
        this.window = window;
        try{
            //fills the array with the file location of assorted ads
            //(creds to whowantsakookie via stack overflow)
        pics[0] = new ImageIcon(new URL("file:.\\picStorage\\Adds\\ezgif-save.gif"));
        pics[1] = new ImageIcon(new URL("file:.\\picStorage\\Adds\\ezgif-save (1).gif"));
        pics[2] = new ImageIcon(new URL("file:.\\picStorage\\Adds\\ezgif-save (2).gif"));
        pics[3] = new ImageIcon(new URL("file:.\\picStorage\\Adds\\ezgif-save (3).gif"));
        pics[4] = new ImageIcon(new URL("file:.\\picStorage\\Adds\\ezgif-save (4).gif"));
        }
        catch(MalformedURLException w)
        {
        }
        
    }//end of constructor
    
    //run method to cycle through ads every two minutes
    public void run()
    {
        //create variable
        int count=0;
        //start rotate loop
        while(true)
        {
            //resets counter every 5 iterations
            if(count==5)
                count=0;
            
        try{
            //sets the next icon
            window.setIcon(pics[count]);
            //sleeps for 2 min
            Thread.sleep(6000);
        }
        catch(InterruptedException exception)
        {
            
        }
        //adds to the counter
        count++;
        }//end of rotate loop
    }//end of run
}//end of AddCycle


