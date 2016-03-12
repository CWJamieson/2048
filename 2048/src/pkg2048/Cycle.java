/*Project: 2048
* Programmer: Christopher Jamieson
* Program: Cycle.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: the Cycle class rotates through all photos used in the
*   game upon winning when mods are used (Threading Creds to Stewart)
*/
package pkg2048;
import javax.swing.*;
public class Cycle implements Runnable{
    
//creates instace variables
private ImageIcon [] pics = new ImageIcon [12];
JLabel pic;
JLabel window;
    //constuctor
    public Cycle(JLabel window, int mod)
    {
        //sets window to an instance variable
        this.window = window;
        //sets the picture rotation to that of the mod pack
        if(mod !=1){
        Mod mod1 = new Mod(mod);
        pics = mod1.getIcons();
        pic = new JLabel("");
        }
    }
    
    //run method to cycle through pictures
    public void run()
    {
        //create variables
        int count=0;
        //run loop
        while(true)
        {
            //resets after cycling 11 pictures
            if(count==11)
                count=0;
            
        try{
            //sets label to picture from mod pack
            window.setIcon(pics[count]);
            //sleeps for 0.3 seconds
            Thread.sleep(300);
        }
        catch(InterruptedException exception)
        {
            
        }
        //adds to the counter
        count++;
        }//end of run loop
    }//end of run
}//end of Cycle
