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
	public static Plante PlanteEnMemoire = null;
	public static CaseData[][] CaseDatas;
	public static int Tour = 19;
	public static boolean Lose = false;
	public boolean Run = true;
	public JPanel container = new JPanel();
	public int tempsTour = 1;

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
					clickActionGetPlante(jb, new Noix());
				}
				if (i == largeurMax - 1 && j == 5) {
					jb.setIcon(new ImageIcon(iconPoire.getImage()));
					jb.setEnabled(true);
					rentre = true;
					// JOptionPane.showMessageDialog(null, "je suis une pois ");
					clickActionGetPlante(jb, new Poire());
				}
				if (i == largeurMax - 1 && j == 4) {
					jb.setIcon(new ImageIcon(iconSoleil.getImage()));
					jb.setEnabled(true);
					rentre = true;
					// IC.setImage(getIconImage());
					// jb.addActionListener(this);
					clickActionGetPlante(jb, new Soleil());

				}
				if (!rentre) {
					CaseData cd = new CaseData();
					cd.jButton = jb;
					if (j != 9)
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
		boolean wantContinue = true;
		while (wantContinue) {
			Tour = 0;
			Lose = false;
			Game gl = new Game();
			String response = gl.Play();
			// display the showOptionDialog
			Object[] options = { "YES", "NO" };
			int choice = JOptionPane.showOptionDialog(null, response + " Would you like to restart?", "Restart",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

			// interpret the user's choice
			if (choice == JOptionPane.NO_OPTION) {
				wantContinue = false;
				System.exit(0);
			} else {
				wantContinue = true;
			}
		}

	}

	public String Play() {
		while (Tour < 100 && Lose == false) {
			this.nextTurn();
			try {
				TimeUnit.SECONDS.sleep(tempsTour);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (Lose)
			return "Tu as perdu Bouh";

		return "Tu as gagné GG";

	}

	public void nextTurn() {
		Tour++;
		BoutonTour.setText(Integer.toString(Tour));

		// Zombie action
		this.moveZombie();
		this.actionZombie();

		for (int i = 0; i < Zombie.spawnZombies(Tour); i++) {

//			Zombie spawn

			Random Rand = new Random();
			int spawnCase = Rand.nextInt(5);
			ZombieBasic.spawn(new ZombieBasic(), CaseDatas[spawnCase][8].jButton);

			CaseDatas[spawnCase][8].Zombie = new ZombieBasic();
//			Plante action

		}

	}
	
	private void actionZombie() {
		for (int i = 0; i < CaseDatas.length; i++) {
			for (int j = 1; j < CaseDatas[0].length; j++) {
				if (CaseDatas[i][j].Zombie != null && CaseDatas[i][j - 1].plante != null ) {
					Plante planteAttaquee = CaseDatas[i][j - 1].plante;
					planteAttaquee.setViePlante( planteAttaquee.getViePlante() - CaseDatas[i][j].Zombie.degat);					
					if (planteAttaquee.getViePlante()<= 0)
						CaseDatas[i][j - 1].plante = null;
				}
			}
		}
		
	}

	public void moveZombie(){
		for (int i = 0; i < CaseDatas.length; i++) {
			if (CaseDatas[i][0].Zombie != null) {
				Run = false;
				Lose = true;

				return;

			}
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
				if (cd.plante == null && cd.Zombie == null) {
					cd.jButton.setIcon(BoutonMemoire.getIcon());
					cd.plante = PlanteEnMemoire;
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
