package Game;

import java.awt.*;
import javax.swing.*;

public class Game extends JFrame{
    public Game(){
        JPanel container = new JPanel();
        int largeurMax = 7;
        int longueurMax = 11;
        this.setTitle("Grid Layout");
        this.setSize(1600, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new GridLayout(largeurMax,longueurMax));
        for (int i=0; i<largeurMax; i++){
            for (int j=0; j<longueurMax; j++){
                JButton jb = new JButton();
                container.add(jb);
                jb.setBackground(new Color(0x64FF64));
                if (i == 0 || i == largeurMax-1 || j ==0 || j == longueurMax-1) {
                    jb.setBackground(new Color(0x00FF00));
                }
            }
        }
        this.setContentPane(container);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        Game gl = new Game();
    }
}
