import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javafx.scene.layout.*;

public class PlayGame implements ActionListener
{
    JFrame jfrm;
    JTextField jtf[][];
    JButton jvalid;
    JButton jreset;
    JLabel jlabPrompt;
    int count = 0;
    int SIZE = 9;
    static final int Empty_in_grid = 3;
    GenerateSudoku sudo;
    JPanel gridPanel = new JPanel();
    JPanel PromptPanel = new JPanel();
    JPanel ValidPanel = new JPanel();

    PlayGame(){
    
        jfrm = new JFrame("SUDOKU USING JAVA SWING GUI");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(1000,700);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        gridPanel.setPreferredSize(new Dimension(500, 500));
        gridPanel.setLayout(new GridLayout(SIZE, SIZE, 2, 2));
        gridPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
        
        JLabel jWelcome = new JLabel("\r\nSUDOKU GAME\r\n");
        jWelcome.setFont(new Font("Serif", Font.BOLD,35));
        JPanel Welcome = new JPanel();
        Welcome.setPreferredSize(new Dimension(950, 60));
        Welcome.add(jWelcome);
        jWelcome.setForeground(Color.RED);
        
        jfrm.add(Welcome, BorderLayout.CENTER);
        jfrm.add(gridPanel, BorderLayout.CENTER);    
        
        sudo = new GenerateSudoku();
        sudo.fillGrids();
        jtf = new JTextField[SIZE][SIZE];
        
        for(int i = 0; i < 9; i++)
        {
            for(int j = 0; j < 9; j++)
            {
                jtf[i][j] = new JTextField(1);
                jtf[i][j].setText(Integer.toString(sudo.arr[i][j]));
                jtf[i][j].addKeyListener(new KeyAdapter(){
                    public void keyTyped(KeyEvent e){
                        char input = e.getKeyChar();
                        if((input < '1' || input > '9') && input != '\b'){
                            e.consume();
                        }
                    }});
                jtf[i][j].setActionCommand("Cell");
                jtf[i][j].setFont(new Font("Serif", Font.BOLD,23));
                jtf[i][j].setFocusable(true);
                jtf[i][j].addActionListener(this);
                jtf[i][j].setHorizontalAlignment(JTextField.CENTER);
                jtf[i][j].setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
                gridPanel.add(jtf[i][j]);
            }
        }
                
        int j2, k2;
        j2 = 0; k2 = 0; make3grid(j2, k2);  addColour(j2, k2);
        j2 = 0; k2 = 3; make3grid(j2, k2); 
        j2 = 0; k2 = 6; make3grid(j2, k2);  addColour(j2, k2);
        j2 = 3; k2 = 0; make3grid(j2, k2);
        j2 = 3; k2 = 3; make3grid(j2, k2);  addColour(j2, k2);
        j2 = 3; k2 = 6; make3grid(j2, k2);
        j2 = 6; k2 = 0; make3grid(j2, k2);  addColour(j2, k2);
        j2 = 6; k2 = 3; make3grid(j2, k2);
        j2 = 6; k2 = 6; make3grid(j2, k2);  addColour(j2, k2);
        
        jvalid = new JButton("VALIDATE");
        jreset = new JButton("RESET");
        jvalid.setFont(new Font("Serif", Font.BOLD,15));
        jvalid.addActionListener(this);
        jvalid.setPreferredSize(new Dimension(300, 40));
        jvalid.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        ValidPanel.add(jvalid);
        jreset.setFont(new Font("Serif", Font.BOLD,15));
        jreset.addActionListener(this);
        jreset.setPreferredSize(new Dimension(300, 40));
        ValidPanel.add(jreset);
        jreset.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        ValidPanel.setPreferredSize(new Dimension(950, 60));
        
        jlabPrompt = new JLabel("\r\nEnter any Number between 1 to 9 in the Text fields."); 
        jlabPrompt.setFont(new Font("Serif", Font.BOLD,23));
        jlabPrompt.setForeground(new Color(128,0,128));
        PromptPanel.add(jlabPrompt);
        PromptPanel.setPreferredSize(new Dimension(1000, 30));
        
        jfrm.add(PromptPanel, BorderLayout.CENTER);
        jfrm.add(ValidPanel, BorderLayout.CENTER);
        
        jfrm.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        int key1 = 1, key2 = 1;
        String arr2[] = {"1","2","3","4","5","6","7","8","9"};
        if(ae.getActionCommand() == "VALIDATE")
        {
            
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    key1 = 1;
                    if (jtf[i][j].getText().equals("") || jtf[i][j].getText().equals(" "))
                    {
                        key1 = 0;
                    }
                    if(key1 == 0)
                    {
                        jlabPrompt.setText("KINDLY MAKE SURE THAT ALL THE TEXTFIELDS ARE FILLED :-(");
                        jlabPrompt.setVisible(true);
                        return;
                    }
                }
            }
                        
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    if (Integer.parseInt(jtf[i][j].getText()) != sudo.arr[i][j])
                    {
                        key2 = 0;
                        jlabPrompt.setText("SOLUTION IS NOT REACHED. TRY AGAIN :-(");
                        jlabPrompt.setVisible(true);
                        return;
                    }
                }
            }
                        
            if(key1 == 1 && key2 == 1)
            {
               jlabPrompt.setText("CONGRATULATIONS YOU HAVE SOLVED THE SUDOKU GRID :-)");
               jlabPrompt.setVisible(true);
               return;
            }
        }
        else if(ae.getActionCommand() == "RESET")
        {
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    if(jtf[i][j].isEditable())
                    {
                        jtf[i][j].setText("");
                    }
                }
            }
        }
        else{
            return;
        }
        return;
    }
    
    public void make3grid(int j1, int k1)
    {
        count = 0;
        Random rand = new Random();
        int randInt;
        
        for(int j = 0; j < 3; j++)
        {
            for(int k = 0; k < 3; k++)
            {
                randInt = rand.nextInt(2);
                if((count < Empty_in_grid) && (randInt == 0))
                {
                    count = count + 1;
                    jtf[j1 + j][k1 + k].setText("");
                    jtf[j1 + j][k1 + k].setDocument(new JTextFieldLimit(1));
                    jtf[j1 + j][k1 + k].setEditable(true);
                    jtf[j1+j][k1+k].setBackground(new Color(240, 255,255));
                    jtf[j1+j][k1+k].setForeground(Color.RED);
                }
                else
                {   
                    jtf[j1+j][k1+k].setBackground( new Color(240, 255, 255));
                    jtf[j1 + j][k1 + k].setEditable(false);
                }
                jtf[j1 + j][k1 + k].setVisible(true);
            }
        }        
    }
    
    public void addColour(int j1, int k1)
    {
        for(int j = 0; j < 3; j++)
        {
            for(int k = 0; k < 3; k++)
            {
                jtf[j1+j][k1+k].setBackground( new Color(255, 213, 128));
                jtf[j1+j][k1+k].setForeground(Color.BLACK);
                if(jtf[j1 + j][k1 + k].isEditable())
                {
                    jtf[j1+j][k1+k].setForeground(Color.RED); 
                }
            }
        }
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
           public void run(){
               new PlayGame();
           }
        });
    }
}
