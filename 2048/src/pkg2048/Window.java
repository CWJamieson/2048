/*Project: 2048
* Programmer: Christopher Jamieson
* Program: Window.java
* Date: June 5
* Description: Program as a whole: replicated 2048 game. user uses buttons
*   to move tiles around a screen, adding like tiles until the board is filled
*   or the 2048 tile is formed.
*       This class: main window class, handles all gameplay. has methods to
*   allow for restarting, moving tiles, and loading pictures
*/
package pkg2048;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//extends frame
public class Window extends JFrame{
    
    //creates instance variables
    private JLabel [] [] grid;
    private JButton up, left, right, down, restart, undo;
    private JLabel filler, sco, Hsco;
    private String [][] gridchk = new String[4][4];
    private String []toolTips= new String [11]; 
    private int mod, score=0, HScore, undos=3;
    private ImageIcon [] img = new ImageIcon[12];
    boolean grats=true, useAdds;
    JLabel add;
    Color [] colourSet = new Color [13];
    
    //constuctor
    public Window(int mod, boolean useAdds)
    {
        //set mod and useadds to a global variable by the same name
        this.mod = mod;
        this.useAdds=useAdds;
        //get highscore from file
        HScore = getScore();
        //fetch pictures and colours
        pic();
        //run button methods
        upBut();
        leftBut();
        rightBut();
        downBut();
        restartBut();
        undoBut();
        //create JLabels for playing grid and controls
        grid=new JLabel[4][4];
        sco=new JLabel("Score");
        filler=new JLabel("");
        Hsco=new JLabel("HighScore");
        add=new JLabel("");
        
        //starts adds if they are enabled
        if(useAdds){
            //starts addvertisement cycle by calling addcycle
            AddCycle r1 = new AddCycle(add);
            Thread rotate = new Thread(r1);
            rotate.start();
        }
        
        //set up grid and intial spawns and colours
        createMap();
        setColour();
        Color backLight = new Color(105,105,105);
        
        //creates JPanels
        JPanel control = new JPanel(new GridLayout(3,3, 2, 2));
        JPanel screen = new JPanel(new GridLayout(4,4, 5, 5));
        JPanel addBox = new JPanel();
        JPanel userField;
        //creates a grid if adds are enabled
        if(useAdds)
            userField= new JPanel(new GridLayout(2,1,0,0));
        else
            userField = new JPanel(new GridLayout(1,1,0,0));
        //add grid to screen
        for(int i=0;i<4;i++)
        {
            for(int o=0;o<4;o++)
            {
                screen.add(grid[i][o]);
            }
        }
        //add controls
        control.add(sco);
        control.add(up);
        control.add(undo);
        control.add(left);
        control.add(filler);
        control.add(right);
        control.add(Hsco);
        control.add(down);
        control.add(restart);
        //set bacground colour
        screen.setBackground(backLight);
        //add panels to the frame
        addBox.add(add);
        userField.add(control);
        //adds the addBox if adds are enabled
        if(useAdds)
            userField.add(addBox);
        add(userField, BorderLayout.SOUTH);
        add(screen, BorderLayout.CENTER);
    }//end of constuctor
    
    
    //button method for the up button, causes all tiles to move as far up
    //as possible
    private void upBut()
    {
        //create button
        up = new JButton("up");
        
        //create actionlistener
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //fill up cross-reference grid
                fill(0);
                //run move(up) method
                move(8);
                //run post move operations
                clicked();
            }
        }
        //add action listener
        ActionListener listener = new addActionListener();
        up.addActionListener(listener);
    }//end of upBut
    
    //button method for the left button, causes all tiles to move as far left
    //as possible
    public void leftBut()
    {
        //create button
        left = new JButton("left");
        //create action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //fill up cross-reference grid
                fill(0);
                //run move(left) method
                move(4);
                //run post moved operations
                clicked();
                
            }
        }//end of action listener class
        //create and implement action listener
        ActionListener listener = new addActionListener();
        left.addActionListener(listener);
    }//end of leftBut
    
    
    //button method for the up button, causes all tiles to move as far right
    //as possible
    public void rightBut()
    {
        //creates button
        right = new JButton("right");
        
        //creates actionlistener class
        class addActionListener implements ActionListener
        {
            
            public void actionPerformed(ActionEvent event)
            {
                //fills cross reference grid
                fill(0);
                //run move(right) method
                move(6);
                //run post click operations
                clicked();
            }
        }//end of action listener class
        //creates and adds action listener
        ActionListener listener = new addActionListener();
        right.addActionListener(listener);
    }//end of rightBut
    
    
    //button method for the up button, causes all tiles to move as far down
    //as possible
    public void downBut()
    {
        //creates button
        down = new JButton("down");
        
        //creates action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //fills cross reference grid
                fill(0);
                //run move(down) method
                move(2);
                //run post clicked operations
                clicked();
            }
        }//end of action listener class
        //creates action listiener
        ActionListener listener = new addActionListener();
        //adds action listener
        down.addActionListener(listener);
    }//end of downBut
    
    //generalized movement method which takes a single integer that
    //corrisponds on the numberpad to the direction the blocks will be moved
    private void move(int num)
    {
        //num==8
        //creates variables and sets defult to move up
        //vairables are as follows: i=first loop counter, ci = end poin of i
        //vi = how the counter will change every iteration
         int  i=1, ci=4, vi=1;
         //o = nested counter, co = end point of o, vo = how o will change
         int  o=0, co=4, vo=1;
         //p = third nested counter, cp = end point of p, vp = how p will change
         //pd = weather the movement goes along rows or colluums
         //x & y = location in grid of the comparing tile
         int  p, cp=-1, vp=-1, pd, x, y;
         //d = the end of the grid(0 or 3), c = axis the end is on
         int  d = 0, c = 0;
        
         //changes variables in the user selected down
        if(num==2)
        {
            i=2; ci=-1;vi=-1;
            o=0; co=4; vo=1;
            cp=4; vp=1;
            d=3 ;c=0;
        }
        //changes variables in the user selected down
        else if(num==4)
        {
            i=0; ci=4; vi=1;
            o=1; co=4; vo=1;
            cp=-1; vp=-1;
            d = 0; c = 1;
        }
        //changes variables in the user selected down
        else if(num==6)
        {
            i=0; ci=4; vi=1;
            o=2; co=-1; vo=-1;
            cp=4; vp=1;
            d = 3; c = 1;
        }
        //create variables
        boolean flag=true;
        String tmp;
        //create nested grid searching loops
        for(int q=1;i!=ci;i+=vi)
        {
            for(int w=0;o!=co;o+=vo)
            {
                //checks if the tile is not empty
                if(!(grid[i][o].getText().equals("   0")))
                {
                    //assings a specific p value depending on movement direction
                    if(num==8)
                    {p=i-1;pd=1;}
                    else if(num==4){
                        p=o-1;pd=0;}
                    else if(num==6){
                        p=o+1;pd=0;}
                    else{
                        p=i+1;pd=1;}
                    //scans colluum to determine where above tile will move
                    innerloop:
                    for(int e=p;p!=cp;p+=vp)
                    {
                        //assings realative values to x and y depending on
                        //horizontal or vertical movement checks for the
                        //first not empty tile
                        if(pd==0){
                            x=i;
                            y=p;
                        }
                        else{
                            x=p;
                            y=o;
                        }
                            if(!(grid[x][y].getText().equals("   0")))
                        {
                            //marks as found(so it doesnt defult to the end)
                            flag=false;
                            //checks if tiles are like
                            
                            if(grid[i][o].getText().equals(grid[x][y].getText()))
                            {
                                //creates new tile string and adds to score
                                tmp=(Integer.parseInt(grid[i][o].getText().trim())*2)+"";
                                score+=Integer.parseInt(grid[i][o].getText().trim())*2;
                                //formats new tile string
                                while(tmp.length()<4)
                                {
                                    tmp=" "+tmp;
                                }
                                //sets old tile to 0
                                grid[i][o].setText("   0");
                                grid[x][y].setText(tmp);
                            }
                            //if tiles are not alike
                            else
                            {
                                //move to the tile below the filed tile
                                tmp=grid[i][o].getText();
                                //fill the old tile with 0
                                grid[i][o].setText("   0");
                                if(num==8)
                                    x++;
                                else if(num==4)
                                    y++;
                                else if(num==6)
                                    y--;
                                else
                                    x--;
                                
                                grid [x][y].setText(tmp);
                            }
                            //break to only move once
                            break innerloop;
                        }
                        
                    }//end of colluum scan
                    //if no tile is found
                    if(flag)
                    {
                        //move to the furthest tile
                        flag=false;
                        //changes x and y depending on direction
                        if(c==0){
                            x=d;
                            y=o;
                        }
                        else{
                            x=i;
                            y=d;
                        }
                        grid[x][y].setText(grid[i][o].getText());
                        grid[i][o].setText("   0");
                    }
                    //reset replacement flag
                    flag=true;
                }
            }
            //resets nested variables
            o=0;
            if(num==4)
                o=1;
            else if(num==6)
                o=2;
        }//of of nested searching loops
    }//end of move
    
    //undo button method, creates undo button which can be used three times
    private void undoBut()
    {
        //creates button
        undo = new JButton("undo ("+undos+")");
        
        //creates action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //resets grid to cross reference grid
                for(int i=0;i<4;i++)
                {
                    for(int o=0;o<4;o++)
                    {
                        grid[i][o].setText(gridchk[i][o]);
                    }
                }
                //refreshes colours
                setColour();
                //saves score
                save();
                //reduces the amount of undos left
                undos--;
                //sets undo buttton text to include the number of uses left
                if(undos==0)
                    undo.setText("undo");
                else
                    undo.setText("undo ("+undos+")");
                //disables button
                undo.setEnabled(false);
            }
        }//end of action listener class
        //creates action listener
        ActionListener listener = new addActionListener();
        //adds action listener
        undo.addActionListener(listener);
        //starts as disables
        undo.setEnabled(false);
    }//end of undoBut
    
    //method to create the restart button, opening a new menu
    public void restartBut(){
        //creates button
        restart = new JButton("Restart");
        
        //creates action listener class
        class addActionListener implements ActionListener
        {
            public void actionPerformed(ActionEvent event)
            {
                //runs ask method
                ask();
            }
        }//end of action listener class
        //creates action listener
        ActionListener listener = new addActionListener();
        //adds action listener
        restart.addActionListener(listener);
    }//end of restartBut
    
    //method to create the restart window
    public void ask()
    {
        //sends the frame, basic selection, and mod number
        JFrame frm = new Restart(this, false, mod);
        //selects frame size depending on adds
        if(useAdds)
            frm.setSize(250, 490);
        else
            frm.setSize(250, 400);
        frm.setVisible(true);
        frm.setLocationRelativeTo(this);
        //locks size and sets title
        frm.setResizable(false);
        frm.setTitle("2048 By CJWJ");
        
    }//end of ask
    
    //runs intial map preperations, including setting the colour, and numbers
    public void createMap()
    {
        //create variables
        int rand, count=0;
        //nested loops to prepare each tile's font, text, and colour
        for(int i=0;i<4;i++)
        {
            for(int o=0;o<4;o++)
            {
                grid[i][o]=new JLabel("   0");
                grid[i][o].setOpaque(true);
                grid[i][o].setForeground(Color.LIGHT_GRAY);
                grid[i][o].setFont(new Font("Serif", 0, 30));
            }
        }
        //spawns the first two numbers on the board
        for(int i=1;i<=16;i++)
        {
            //randomly selects where to place the numbers
            rand = (int)(i+Math.random()*(16-i+1));
            //checks if the selected tile is reaches, and places a two
            if((rand==16 || rand==15) && count<2)
            {
                if(i/4==4)
                {
                    grid[3][3].setText("   2");
                    grid[3][3].setOpaque(true);
                    count++;
                }
                else{
                    grid[i/4][i%4].setText("   2");
                    grid[i/4][i%4].setOpaque(true);
                    count++;
                }
            }
        }//end of spawn loop
        
                
    }//end of createMap
    
    //runs the post clicked operation methods
    public void clicked()
    {
        //if the grid has changed, spawn a new number
        if(chk()){
            spawn();
            //if the user has remaining undos, reenable it
            if(undos>0)
                undo.setEnabled(true);
        }
        else
            //if the grid did not change, disable the undo button
            undo.setEnabled(false);
        //refresh the grid's colours
        setColour();
        //save the highscore
        save();
    }//end of clicked
    
    //method to check if the cross reference gird is identical to the current
    //grid, to avoid over spawning
    private boolean chk()
    {
        //creates variable
        boolean flag=false;
        //nested loops to compare every tile
        for(int i=0;i<4;i++)
        {
            for(int o=0;o<4;o++)
            {
                //if tiles are not alike, the board has changed
                if(!(gridchk[i][o].equals(grid[i][o].getText())))
                    flag=true;
            }
        }//end of loops
        //returns if the board changed
        return flag;
    }//end of chk
    
    //spawn method to randomly select the placement of the next tile on
    //the board
    public void spawn()
    {
        //create variables
        int count=0;
        int rand, size;
        //checks the number of empty tiles
        for(int i=0;i<4;i++)
        {
            for(int o=0;o<4;o++)
            {
                if(grid[i][o].getText().equals("   0"))
                {
                    count++;
                }
            }
        }//end of tile check
        //selects a random empty tile
        rand = (int)(1+Math.random()*(count-1+1));
        //selects the size of the spawned tile
        size = (int)(1+Math.random()*(3-1+1));
        //resets count
        count=0;
        //finds selected tile, and places selected size in nested for loops
        outerloop:
        for(int i=0;i<4;i++)
        {
            for(int o=0;o<4;o++)
            {
                if(grid[i][o].getText().equals("   0"))
                {
                    count++;
                    if(count==rand)
                    {
                        if(size==1)
                            grid[i][o].setText("   4");
                        else
                            grid[i][o].setText("   2");
                        break outerloop;
                    }
                }
            }
        }//end of placement
    }//end of spawn
    
    //sets the colour of every tile on the board, along with the font and
    //tooltip/icon if applicatable
    private void setColour(){
        
        //nested for loops to set all tiles to appropriate colours, icons, etc
        for(int i=0;i<4;i++)
        {
            for(int o=0;o<4;o++)
            {
                
                if(grid[i][o].getText().equals("   0"))
                {
                    //reference for if else structure:
                    //sets background colour
                    grid[i][o].setBackground(colourSet[12]);
                    //sets text colour
                    grid[i][o].setForeground(colourSet[12]);
                    //sets icon
                    grid[i][o].setIcon(null);
                    //sets hover info
                    grid[i][o].setToolTipText(null);
                    //sets font
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals("   2"))
                {
                    grid[i][o].setBackground(colourSet[0]);
                    grid[i][o].setForeground(Color.BLACK);
                    grid[i][o].setIcon(img[0]);
                    grid[i][o].setToolTipText(toolTips[0]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals("   4"))
                {
                    grid[i][o].setBackground(colourSet[1]);
                    grid[i][o].setForeground(Color.BLACK);
                    grid[i][o].setIcon(img[1]);
                    grid[i][o].setToolTipText(toolTips[1]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals("   8"))
                {
                    grid[i][o].setBackground(colourSet[2]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setIcon(img[2]);
                    grid[i][o].setToolTipText(toolTips[2]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals("  16"))
                {
                    grid[i][o].setBackground(colourSet[3]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setIcon(img[3]);
                    grid[i][o].setToolTipText(toolTips[3]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals("  32"))
                {
                    grid[i][o].setBackground(colourSet[4]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setIcon(img[4]);
                    grid[i][o].setToolTipText(toolTips[4]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals("  64"))
                {
                    grid[i][o].setBackground(colourSet[5]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setIcon(img[5]);
                    grid[i][o].setToolTipText(toolTips[5]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals(" 128"))
                {
                    grid[i][o].setBackground(colourSet[6]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setIcon(img[6]);
                    grid[i][o].setToolTipText(toolTips[6]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals(" 256"))
                {
                    grid[i][o].setBackground(colourSet[7]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setIcon(img[7]);
                    grid[i][o].setToolTipText(toolTips[7]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals(" 512"))
                {
                    grid[i][o].setBackground(colourSet[8]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setIcon(img[8]);
                    grid[i][o].setToolTipText(toolTips[8]);
                    grid[i][o].setFont(new Font("Serif", 0, 30));
                }
                else if(grid[i][o].getText().equals("1024"))
                {
                    grid[i][o].setBackground(colourSet[9]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setFont(new Font("Serif", 0, 24));
                    grid[i][o].setIcon(img[9]);
                    grid[i][o].setToolTipText(toolTips[9]);
                }
                else if(grid[i][o].getText().equals("2048"))
                {
                    grid[i][o].setBackground(colourSet[10]);
                    grid[i][o].setForeground(Color.WHITE);
                    grid[i][o].setFont(new Font("Serif", 0, 24));
                    grid[i][o].setIcon(img[10]);
                    grid[i][o].setToolTipText(toolTips[10]);
                    if(grats)
                        win();
                }
                else
                {
                    grid[i][o].setBackground(colourSet[11]);
                    grid[i][o].setIcon(img[11]);
                    grid[i][o].setFont(new Font("Serif", 0, 24));
                    grid[i][o].setToolTipText((grid[i][o].getText()));
                }
            }
        }//end of nested loops
        
    }//end of setcolour
    
    //sets the score/highscore label and saves the highscore to a file
    public void save()
    {
        //sets the score label to the score
        sco.setText(score+"");
        //checks if the score is above the high score
        if(score>HScore)
        {
            try{
            //writes the highscore to the file
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("HighScore.txt")));
            out.println(score);
            out.close();
            HScore =score;
            }
            catch(IOException e)
            {
                
            }
        }
        //sets the highscore
        Hsco.setText(HScore+"");
    }//end of save
    
    //recursive method to fill the cross reference grid gridchk, takes an
    //int to select the row to record
    private void fill(int n)
    {
        //loop to fill the row of tiles
            for(int o=0;o<4;o++)
            {
                gridchk[n][o]=grid[n][o].getText();
            }
            //recalls the method with a lower row count
            if(n<3)
                fill(n+1);
        
    }//end of fill
    
    //method to create the win screen upon completing the game
    public void win()
    {
        //creates frame, sends mod and weather the user cheated
        JFrame frm = new win(mod, false);
        //sets size
        frm.setSize(300, 200);
        frm.setVisible(true);
        frm.setLocationRelativeTo(null);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //locks size and sets title
        frm.setResizable(false);
        frm.setTitle("wow you're amazing");
        //marks as won, so window only appears once
        grats=false;
    }//end of win
    
    //sets the pictures and creates the colours
    public void pic()
    {
        //wipes the tool Tips
        for(int i=0;i<11;i++)
        {
            toolTips[i]="";
        }
        //creates defult colour array
            colourSet [11]=new Color(255,165,0);
            colourSet [10]=new Color(255,250,0);
            colourSet [9]=new Color(238,238,0);
            colourSet [8]=new Color(205,205,0);
            colourSet [7]=new Color(139,139,0);
            colourSet [6]=new Color(255,69,0);
            colourSet [5]=new Color(255,0,0);
            colourSet [4]=new Color(255,64,64);
            colourSet [3]=new Color(255,99,71);
            colourSet [2]=new Color(255,125,64);
            colourSet [1]=new Color(205,197,191);
            colourSet [0]=new Color(205,205,193);
            colourSet [12]=new Color(128,128,128);
        
        //if a mod pack is used, is fetched from Mod class
        if(mod!=1){
            Mod mod1 = new Mod(mod);
            toolTips = mod1.getTools();
            img = mod1.getIcons();
        }
        
    }//end of pic
    
    //method to find and return the highscore, if one is present
    public int getScore()
    {
        //creates highscore variable
        int n;
        try{
            //trys to read high score file, parses high score, and
            //closes the stream
            BufferedReader scorein = new BufferedReader(new FileReader("HighScore.txt"));
            n = Integer.parseInt(scorein.readLine());
            scorein.close();
        }
        catch(IOException e)
        {
            //if no file is found, 0 is used
            n=0;
        }
        //returns highscore
        return n;
    }//end of getScore
    
}//end of class window
