/*Project: 2048
* Programmer: Christopher Jamieson
* Program: Mod.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: loads specific pictures to an array if the user wishes
*   to replace numbers with relatable pictures
*/
package pkg2048;
import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
public class Mod {
    //create instance variables
    private String []toolTips = new String [11];
    private ImageIcon [] img = new ImageIcon[12];
    //constuctor
    public Mod(int num)
    {
        //creates temperary holding array for file locations and tool tips,
        //depending on selected mod
        String [][] list = new String [2][12];
        if(num ==3)
        {
            list[0][0]=".\\picStorage\\dwmod\\1. matha.jpg";
            list[0][1]=".\\picStorage\\dwmod\\2. cyber.jpg";
            list[0][2]=".\\picStorage\\dwmod\\3. sontarian.jpg";
            list[0][3]=".\\picStorage\\dwmod\\4. rose.jpg";
            list[0][4]=".\\picStorage\\dwmod\\5. eccleston.jpg";
            list[0][5]=".\\picStorage\\dwmod\\6. master.jpg";
            list[0][6]=".\\picStorage\\dwmod\\7. Cassandra.jpg";
            list[0][7]=".\\picStorage\\dwmod\\8. matt smith.jpg";
            list[0][8]=".\\picStorage\\dwmod\\9. amy.jpg";
            list[0][9]=".\\picStorage\\dwmod\\10. tennant.jpg";
            list[0][10]=".\\picStorage\\dwmod\\11. dalek.jpg";
            list[0][11]=".\\picStorage\\troll.jpg";
            list[1][0]="Martha Jones (2)";;
            list[1][1]="Cyberman (4)";
            list[1][2]="Sontarian(8)";
            list[1][3]="Rose Tyler (16)";
            list[1][4]="9th Doctor (32)";
            list[1][5]="The Master (64)";
            list[1][6]="Cassandra (128)";
            list[1][7]="11th doctor (256)";
            list[1][8]="Amy Pond (512)";
            list[1][9]="10th Doctor (1024)";
            list[1][10]="Dalek (2048)";
            list[1][11]="Trollololol";
            
        }
        else if(num ==2)
        {
            list[0][0]=".\\picStorage\\1. mac.jpg";
            list[0][1]=".\\picStorage\\2.jpg";
            list[0][2]=".\\picStorage\\4. Na.jpg";
            list[0][3]=".\\picStorage\\5. ogrady.jpg";
            list[0][4]=".\\picStorage\\5. rehel.jpg";
            list[0][5]=".\\picStorage\\6. van.jpg";
            list[0][6]=".\\picStorage\\7. papa.jpg";
            list[0][7]=".\\picStorage\\8. ianni.jpg";
            list[0][8]=".\\picStorage\\10. vogt.jpg";
            list[0][9]=".\\picStorage\\11. gright.jpg";
            list[0][10]=".\\picStorage\\him.png";
            list[0][11]=".\\picStorage\\troll.jpg";
            list[1][0]="Mr. Macdonald (2)";
            list[1][1]="Mrs. Kumagai (4)";
            list[1][2]="Mr. Naciuk (8)";
            list[1][3]="Mrs. O'grady (16)";
            list[1][4]="Mrs. Rehel (32)";
            list[1][5]="Mr. Van (64)";
            list[1][6]="Mr. Papa (128)";
            list[1][7]="Mrs. Ianni (256)";
            list[1][8]="Mrs. Vogt (512)";
            list[1][9]="Mrs. Grightmire (1024)";
            list[1][10]="Big G (2048)";
            list[1][11]="Trollololol";
            
        }
        else if(num ==4)
        {
            list[0][0]=".\\picStorage\\GoT\\arya(2).jpg";
            list[0][1]=".\\picStorage\\GoT\\sansa (4).jpg";
            list[0][2]=".\\picStorage\\GoT\\robb(8).jpg";
            list[0][3]=".\\picStorage\\GoT\\Jon Snow(16).jpg";
            list[0][4]=".\\picStorage\\GoT\\Ned-Stark(32).jpg";
            list[0][5]=".\\picStorage\\GoT\\joffrey(64).jpg";
            list[0][6]=".\\picStorage\\GoT\\tyrion_lannister(128).jpg";
            list[0][7]=".\\picStorage\\GoT\\jaime-lannister(256).jpg";
            list[0][8]=".\\picStorage\\GoT\\tywin-lannister(512).jpg";
            list[0][9]=".\\picStorage\\GoT\\Daenerys (1024).jpg";
            list[0][10]=".\\picStorage\\GoT\\white_walker(2048).jpg";
            list[0][11]=".\\picStorage\\GoT\\Old Gods(+).jpg";
            list[1][0]="Arya Stark (2)";
            list[1][1]="Sansa Stark (4)";
            list[1][2]="Robb Stark (8)";
            list[1][3]="Jon Snow (16)";
            list[1][4]="Ned Stark (32)";
            list[1][5]="Joffrey Barathian (64)";
            list[1][6]="Tyrion Lannister (128)";
            list[1][7]="Jamie Lannister (256)";
            list[1][8]="Tywin Lannister (512)";
            list[1][9]="Daenerys Stormborn (1024)";
            list[1][10]="White Walker (2048)";
            list[1][11]="Trollololol";
            
        }
        
        try{
            //loads icons from specified locations
            //(Creds to Guillaume Polet via Stack overflow)
                Image pre = ImageIO.read(new File(list[0][0]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[0] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][1]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[1] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][2]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[2] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][3]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[3] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][4]));
                pre= pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[4] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][5]));
                pre= pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[5] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][6]));
                pre= pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[6] = new ImageIcon(pre);

                pre= ImageIO.read(new File(list[0][7]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[7] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][8]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[8] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][9]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[9] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][10]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[10] = new ImageIcon(pre);

                pre = ImageIO.read(new File(list[0][11]));
                pre = pre.getScaledInstance(60, 70, Image.SCALE_SMOOTH);
                img[11] = new ImageIcon(pre);
                
                //sets tooltips to specified pharses
                toolTips[0]=list[1][0];
                toolTips[1]=list[1][1];
                toolTips[2]=list[1][2];
                toolTips[3]=list[1][3];
                toolTips[4]=list[1][4];
                toolTips[5]=list[1][5];
                toolTips[6]=list[1][6];
                toolTips[7]=list[1][7];
                toolTips[8]=list[1][8];
                toolTips[9]=list[1][9];
                toolTips[10]=list[1][10];
            }
            catch(IOException e)
            {

            }
    }//end of constuctor
    
    //method to return tool tips for use in the main window
    public String [] getTools()
    {
        return toolTips;
    }//end of getTools
    //method to return icons for use in the main window
    public ImageIcon[] getIcons()
    {
        return img;
    }//end of getIcons
    
}//end of Mod
