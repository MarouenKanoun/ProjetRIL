package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Game extends JFrame {
	public static JButton BoutonMemoire = null;
	public static JButton BoutonTour = null;
	public static JButton BoutonSolde = null;
	
	public static Plante PlanteEnMemoire = null;
	public static CaseData[][] CaseDatas;
	public int Tour = 19;
	public boolean Loose = false;
	public boolean Run = true;
	public static  Integer SoldeGame=800;
	
	
	public Game() {
		
		CaseDatas = new CaseData[5][9];

		// Resize icon----------------
		// ImageIcon icon = new ImageIcon(new
		// ImageIcon("image/NOIX.png").getImage().getScaledInstance(200, 200,
		// Image.SCALE_DEFAULT));
		ImageIcon iconNoix = new ImageIcon(
				new ImageIcon("image/NOIX.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon iconPoire = new ImageIcon(
				new ImageIcon("image/poire.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImageIcon iconSoleil = new ImageIcon(
				new ImageIcon("image/soleil.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		// -----------------------
		// ImageIcon IC = new ImageIcon();
		JPanel container = new JPanel();
		int largeurMax = 7;
		int longueurMax = 11;
		this.setTitle("Grid Layout");
		this.setSize(1600, 900);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setLayout(new GridLayout(largeurMax, longueurMax));
		for (int i = 0; i < largeurMax; i++) {
			for (int j = 0; j < longueurMax; j++) {
				JButton jb = new JButton();
				container.add(jb);
				jb.setBackground(new Color(0x64FF64));
				Boolean rentre = false;
				if (i == 0 || i == largeurMax - 1 || j == 0 || j == longueurMax - 1) {
					jb.setBackground(new Color(0x00FF00));
					jb.setEnabled(false); // Le bouton n'est plus cliquable
					rentre = true;

				}
				if (i == 0 && j == 0) {
					BoutonMemoire = jb;
					rentre = true;
				}
				if (i == 0 && j == 1) {
					BoutonTour = jb;
					rentre = true;
				}
				if (i == largeurMax - 1 && j == 6) {
					jb.setIcon(new ImageIcon(iconNoix.getImage()));
					jb.setEnabled(true);
					rentre = true;
					// JOptionPane.showMessageDialog(null, "Noix ");
					Noix MaNoix=new Noix();
					clickActionGetPlante(jb, MaNoix, MaNoix.getCout(),SoldeGame);
				}
				if (i == largeurMax - 1 && j == 5) {
					jb.setIcon(new ImageIcon(iconPoire.getImage()));
					jb.setEnabled(true);
					rentre = true;
					// JOptionPane.showMessageDialog(null, "je suis une pois ");
					Poire MaPois= new Poire();
					clickActionGetPlante(jb, MaPois,MaPois.getCout(),SoldeGame);
				}
				if (i == largeurMax - 1 && j == 4) {
					jb.setIcon(new ImageIcon(iconSoleil.getImage()));
					jb.setEnabled(true);
					rentre = true;
					Soleil MonSoleil = new Soleil();
					clickActionGetPlante(jb,MonSoleil,MonSoleil.getCout(),SoldeGame);

				}
				if (i == 0 && j == 10) {
					BoutonSolde = jb;
					jb.setText("Solde "+SoldeGame);
				}
				
				if (!rentre) {
					CaseData cd = new CaseData();
					cd.jButton = jb;
					clickActionSetPlante(cd);
					rentre = true;
					CaseDatas[i - 1][j - 1] = cd;
				}
			}

		}
		this.setContentPane(container);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Game gl = new Game();
		gl.Play();
	}

	public void Play() {
		while (Tour < 100 && Loose == false) {
			this.nextTurn();
			try {
				TimeUnit.SECONDS.sleep(3);
				
				//CaseDatas[4][5].jButton.setText("test"); 

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void nextTurn() {
		Tour++;
		BoutonTour.setText(Integer.toString(Tour));

		// Zombie action

		for (int i = 1; i < CaseDatas.length; i++) {
			for (int j = 1; j < CaseDatas[0].length; j++) {
				if (CaseDatas[i][j].Zombie != null && CaseDatas[i][j - 1].plante == null
						&& CaseDatas[i][j - 1].Zombie == null) {
					CaseDatas[i][j - 1].jButton.setIcon(CaseDatas[i][j].jButton.getIcon());
					CaseDatas[i][j].jButton.setIcon(null);
					CaseDatas[i][j - 1].Zombie = CaseDatas[i][j].Zombie;
					CaseDatas[i][j].Zombie = null;
					

				}
			}

		}

		for (int i = 0; i < Zombie.spawnZombies(Tour); i++) {

//			Zombie spawn

			Random Rand = new Random();
			int spawnCase = Rand.nextInt(5);
			ZombieBasic.spawn(new ZombieBasic(), CaseDatas[spawnCase][8].jButton);

			CaseDatas[spawnCase][8].Zombie = new ZombieBasic();
//			Plante action

		}

	}

	public static void clickActionGetPlante(JButton jb, Plante toset,int coutPlante,int Credit) {
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
			public void mousePressed(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub
				if (Credit<coutPlante)
				{
					javax.swing.JOptionPane.showMessageDialog(null,"you can't"); 
			    }
				else
				{
				BoutonMemoire.setIcon(jb.getIcon());
				PlanteEnMemoire = toset;
				
				}
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
				
				if (cd.plante == null && SoldeGame >= PlanteEnMemoire.getCout()) {
					cd.jButton.setIcon(BoutonMemoire.getIcon());
					cd.plante = PlanteEnMemoire;
				   SoldeGame=SoldeGame- PlanteEnMemoire.getCout();
				   BoutonSolde.setText("Solde "+SoldeGame);
				   
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
