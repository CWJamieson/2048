/*Project: 2048
* Programmer: Christopher Jamieson
* Program: Main.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: creates the first viewed window, and allows the user to
*   select the picture pack for the tiles before starting the game
*/


package pkg2048;

import java.io.*;
import javax.swing.*;
public class Main extends JFrame{
//throw exception
    public static void main(String[] args) throws IOException {
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
    }//end of main

}//end of class
