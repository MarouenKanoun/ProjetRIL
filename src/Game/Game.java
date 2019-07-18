package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Game extends JFrame {
	public static JButton BoutonMemoire = null;
	public static Plante PlanteEnMemoire = null;
	public static CaseData[][] CaseDatas;
	public static Zombie Zombie;

	public Game()
    {
			

		
    	CaseDatas = new CaseData [5][9];
    	
    	//Resize icon----------------
    	//ImageIcon icon = new ImageIcon(new ImageIcon("image/NOIX.png").getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    	ImageIcon iconNoix = new ImageIcon(new ImageIcon("image/NOIX.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));    
    	ImageIcon iconPoire = new ImageIcon(new ImageIcon("image/poire.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    	ImageIcon iconSoleil = new ImageIcon(new ImageIcon("image/soleil.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
    	//-----------------------
    	//ImageIcon IC = new ImageIcon();
        JPanel container = new JPanel();
        int largeurMax = 7;
        int longueurMax = 11;
        this.setTitle("Grid Layout");
        this.setSize(1600, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container.setLayout(new GridLayout(largeurMax,longueurMax));
        for (int i=0; i<largeurMax; i++)
        {
            for (int j=0; j<longueurMax; j++)
            {
                JButton jb = new JButton();
                container.add(jb);
                jb.setBackground(new Color(0x64FF64));
                Boolean rentre = false;
                if (i == 0 || i == largeurMax-1 || j ==0 || j == longueurMax-1) 
                {
                    jb.setBackground(new Color(0x00FF00));
                    jb.setEnabled(false); //Le bouton n'est plus cliquable
                	rentre = true;
                    
                }
                if ( i == 0 && j ==0) 
                {
                	BoutonMemoire = jb;
                	rentre = true;
                }
                if ( i == largeurMax-1 && j ==6) 
                {
                    jb.setIcon(new ImageIcon(iconNoix.getImage()));
                    jb.setEnabled(true); 
                	rentre = true;
                   // JOptionPane.showMessageDialog(null, "Noix  ");
                    clickActionGetPlante(jb,  new Noix());
                }
                if ( i == largeurMax-1 && j ==5) 
                {
                    jb.setIcon(new ImageIcon(iconPoire.getImage()));
                    jb.setEnabled(true); 
                	rentre = true;
                   // JOptionPane.showMessageDialog(null, "je suis une pois  ");
                    clickActionGetPlante(jb, new Poire());
                }
                if ( i == largeurMax-1 && j ==4) 
                {
                    jb.setIcon(new ImageIcon(iconSoleil.getImage()));
                    jb.setEnabled(true);
                	rentre = true; 
                    //IC.setImage(getIconImage());
                    //jb.addActionListener(this);
                    clickActionGetPlante(jb, new Soleil());
                    
                    
                }
                if(!rentre) {
                	CaseData cd = new CaseData();
                	cd.jButton = jb;
                	clickActionSetPlante(cd);
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

	public static void clickActionGetPlante(JButton jb, Plante toset) {
		jb.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				BoutonMemoire.setIcon(jb.getIcon());
				PlanteEnMemoire = toset;
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	public static void clickActionSetPlante(CaseData cd) {
		cd.jButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
//				cd.jButton.setIcon(BoutonMemoire.getIcon());
				// PlanteEnMemoire = toset;
				if (cd.p == null) {
					cd.jButton.setIcon(BoutonMemoire.getIcon());
					cd.p = PlanteEnMemoire;
				}
				;

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

		});
	}

}
