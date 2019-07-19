package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import javax.swing.*;

public class Game extends JFrame {
	public static JButton BoutonMemoire = null;
	public static JButton BoutonTour = null;
	public static JButton BoutonSoldeSoleil = null;
	public static JButton BoutonScore = null;
	public static Plante PlanteEnMemoire = null;
	public static CaseData[][] CaseDatas;
	public static int SoldeSoleil = 100;
	public static int Tour = 0;
	public static int State = 1;
	public static int ScoreGame = 0;
	public static ArrayList<ZombieGeneric> ZombieNeedToSpawn = new ArrayList<ZombieGeneric>();
	public static int Credit = 0;
	public final static double TauxDeMultiplication = 1.2;
	public static boolean Lose = false;
	public boolean Run = true;
	public JPanel container = new JPanel();
	public static long tempsTour = 500;

	public final Plante[] TableauDePlante = new Plante[]{
		new Shovel(),
		new SunFlower(),
		new TwinSunFlower(),
		new PeaShooter(),
		new Repeater(),
		new Gatling(),
		new TorchWood(),
		new Nut(),
		new TallNut(),
		new CherryBomb()};
	public final ZombieGeneric[] TableauDeZombie = new ZombieGeneric[]{
			new Gargantuar(),
			new BucketheadZombie(),
			new ConeheadZombie(),
			new Imp(),
			new BasicZombie()};

	public Game() {

		CaseDatas = new CaseData[5][9];

		// Resize icon----------------
		// ImageIcon icon = new ImageIcon(new
		// ImageIcon("image/NOIX.png").getImage().getScaledInstance(200, 200,
		// Image.SCALE_DEFAULT));
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
					jb.setEnabled(false);
					rentre = true;

				}
				if (i == 0 && j == 0) {
					BoutonMemoire = jb;
					rentre = true;
				}
				if (i == 3 && j == 0) {
					BoutonSoldeSoleil = jb;
					BoutonSoldeSoleil.setText(SoldeSoleil +" Soleils");
					rentre = true;
				}
				if (i == 0 && j == 10) {
					BoutonScore = jb;
					rentre = true;
				}
				if (i == 0 && j == 1) {
					BoutonTour = jb;
					rentre = true;
				}
				if(i == largeurMax - 1 && j<10) {
					clickActionGetPlante(jb, TableauDePlante[j]);
					jb.setIcon(TableauDePlante[j].ImagePlante);
					jb.setEnabled(true);
					rentre = true;
				}
				if (!rentre) {
					CaseData cd = new CaseData();
					cd.JButtonCase = jb;
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
			ZombieNeedToSpawn = new ArrayList<ZombieGeneric>();
			Tour = 0;
			ScoreGame = 0;
			Credit = 0;
			Lose = false;
			Game gl = new Game();
			String response = gl.Play();
			// display the showOptionDialog
			Object[] options = { "YES", "NO" };
			int choice = JOptionPane.showOptionDialog(null, response + " Would you like to restart?", "Restart",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);


			if (choice == JOptionPane.NO_OPTION) {
				wantContinue = false;
				System.exit(0);
			} else {
				wantContinue = true;
			}
		}

	}

	public String Play() {
		while (ScoreGame < 20000 && Lose == false) {
			this.nextTurn();
			try {
				Thread.sleep(tempsTour);

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
		//	Plante action

			actionPlante();
		// Zombie action
		actionZombie();

		// Zombie spawn
		getZombiesToSpawn();
		spawnZombies();




	}

private void actionZombie() {

		for (int i = 0; i < CaseDatas.length; i++) {
			for (int j = 1; j < CaseDatas[0].length; j++) {
				if(CaseDatas[i][j].ZombieCase != null)
					if(CaseDatas[i][j].ZombieCase.Action()){
						moveZombie(i,j);
						attaqueZombie(i,j);
					}
				}
				}
			}
	private void attaqueZombie(int i, int j) {
		if (CaseDatas[i][j - 1].PlanteCase != null ) {
			CaseDatas[i][j - 1].PlanteCase.ViePlante = CaseDatas[i][j - 1].PlanteCase.ViePlante - CaseDatas[i][j].ZombieCase.Degat;
			if (CaseDatas[i][j - 1].PlanteCase.ViePlante<= 0) {
				CaseDatas[i][j - 1].JButtonCase.setIcon(null);
				CaseDatas[i][j - 1].PlanteCase = null;
			}
		}


	}

	public void moveZombie(int i, int j) {
		if (CaseDatas[i][0].ZombieCase != null) {
			Run = false;
			Lose = true;
			return;
		}
		if(CaseDatas[i][j - 1].PlanteCase == null
					&& CaseDatas[i][j - 1].ZombieCase == null) {
				CaseDatas[i][j - 1].JButtonCase.setIcon(CaseDatas[i][j].JButtonCase.getIcon());
				CaseDatas[i][j].JButtonCase.setIcon(null);
				CaseDatas[i][j - 1].ZombieCase = CaseDatas[i][j].ZombieCase;
				CaseDatas[i][j - 1].ZombieCase.Cooldown = CaseDatas[i][j - 1].ZombieCase.Speed;
				CaseDatas[i][j].ZombieCase = null;
			}
	}

	private void actionPlante() {
		for (int i = 0; i < CaseDatas.length; i++) {
			for (int j = 0; j < CaseDatas[0].length; j++) {
				if (CaseDatas[i][j].PlanteCase != null){

					int action = CaseDatas[i][j].PlanteCase.Action(this); // -1 Rien, 0 Action Sans Attque, Autre Nombre Dattaque
					if( action >= 0) {
						CaseDatas[i][j].JButtonCase.setEnabled(true);
						for(int atq = 0; atq < action; atq++){
							ZombieGeneric touched = ZombieFound(i,j, false);
							if(touched != null) {
								touched.Pv -= CaseDatas[i][j].PlanteCase.Attaque()*TorchWoodFound(i,j);
								if(touched.Pv <= 0)
								ZombieFound(i,j, true);

							}
						}
					}
				else{
					CaseDatas[i][j].JButtonCase.setEnabled(false);
				 	}
				}
			}
		}
	}

	public ZombieGeneric ZombieFound(int _i, int _j, boolean destroy)
	{
			for (int j = _j; j < CaseDatas[0].length; j++) {
				if(CaseDatas[_i][j].ZombieCase != null) {
					if(destroy) {
						Credit += CaseDatas[_i][j].ZombieCase.Score*TauxDeMultiplication;
						ScoreGame += CaseDatas[_i][j].ZombieCase.Score;
						CaseDatas[_i][j].ZombieCase = null;
						CaseDatas[_i][j].JButtonCase.setIcon(null);}
					return CaseDatas[_i][j].ZombieCase;}
			}
			return null;
	}

	public int TorchWoodFound(int _i, int _j)
	{
			for (int j = _j; j < CaseDatas[0].length; j++) {
				if(CaseDatas[_i][j].PlanteCase != null)
					if( CaseDatas[_i][j].PlanteCase instanceof TorchWood)
						return 2;
			}
			return 1;
	}
	public ZombieGeneric GetNewZombie(ZombieGeneric zombieExample){
		if( zombieExample instanceof Gargantuar)
			return new Gargantuar();
		if( zombieExample instanceof Imp)
			return new Imp();
		if( zombieExample instanceof BucketheadZombie)
			return new BucketheadZombie();
		if( zombieExample instanceof ConeheadZombie)
			return new ConeheadZombie();
		if( zombieExample instanceof BasicZombie)
			return new BasicZombie();
		return null;

	}

	public void getZombiesToSpawn()
	{
		if (Tour == 60)
			Credit += 20;

		if(ScoreGame > 150 && State == 1){
			if(!ligneIsEmpty())
				return;
			for(int i = 0; i < 5; i++) {
				ZombieGeneric zg = GetNewZombie(new Imp());
				zg.Score = 0;
				ZombieNeedToSpawn.add(zg);
				
			}			
			State++;
			return;
		}
		if(ScoreGame > 300 && State == 2){
			Credit += 100;
			State++;
		}

		if(ScoreGame > 750 && State == 3){
			Credit += 500;
			State++;
		}

		if(ScoreGame > 4000 && State == 4){
			Credit += 3000;
			State++;
		}

		// A regler
		for(int i = 0; i+State-1 < TableauDeZombie.length; i++)
			{
			if(Credit >= TableauDeZombie[i].Score)
				{
				Credit -= TableauDeZombie[i].Score;
				ZombieNeedToSpawn.add(GetNewZombie(TableauDeZombie[i]));
			}
		}
	}

public boolean ligneIsEmpty(){
		for (int i = 0; i < 5; i++)
			if(CaseDatas[i][8].ZombieCase != null)
				return false;
		return true;
}

public boolean ligneisRempli(){
	for (int i = 0; i < 5; i++)
		if(CaseDatas[i][8].ZombieCase == null)
			return false;
	return true;
}
	public void spawnZombies(){

		while(!ligneisRempli() && ZombieNeedToSpawn.size() > 0){
				Random Rand = new Random();
				int spawnCase = Rand.nextInt(5);
				if(CaseDatas[spawnCase][8].ZombieCase == null) {
					CaseDatas[spawnCase][8].JButtonCase.setIcon(ZombieNeedToSpawn.get(0).IconZombie);
					CaseDatas[spawnCase][8].ZombieCase = ZombieNeedToSpawn.get(0);
					ZombieNeedToSpawn.remove(0);
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
		cd.JButtonCase.addMouseListener(new MouseListener() {
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
//				cd.JButtonCase.setIcon(BoutonMemoire.getIcon());
				// PlanteEnMemoire = toset;
				if(PlanteEnMemoire instanceof Shovel){
					cd.JButtonCase.setIcon(null);
					cd.PlanteCase = null;
					return;
				}
				if (CanPlacePlante(cd) && cd.ZombieCase == null && SoldeSoleil >= PlanteEnMemoire.Price ) {
					SoldeSoleil -= PlanteEnMemoire.Price;
					BoutonSoldeSoleil.setText(SoldeSoleil +" Soleils");
					cd.JButtonCase.setIcon(BoutonMemoire.getIcon());

					cd.PlanteCase = setPlante(PlanteEnMemoire);
				}


			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

		});
	}

public static boolean CanPlacePlante(CaseData cd){
	if( PlanteEnMemoire instanceof Gatling)
		{
		if(cd.PlanteCase instanceof Repeater)
			return true;
		else
			return false;
		}
	if( PlanteEnMemoire instanceof TallNut)
		{
		if(cd.PlanteCase instanceof Nut)
			return true;
		else
			return false;
		}
	if( PlanteEnMemoire instanceof TwinSunFlower)
		{
		if(cd.PlanteCase instanceof SunFlower)
			return true;
		else
			return false;
		}
	if(cd.PlanteCase == null)
		return true;
	else
		return false;
}


	public static Plante setPlante(Plante plante) {
		if( plante instanceof Nut)
			return new Nut();
		if( plante instanceof SunFlower)
			return new SunFlower();
		if( plante instanceof PeaShooter)
			return new PeaShooter();
		if( plante instanceof Gatling)
			return new Gatling();
		if( plante instanceof Repeater)
			return new Repeater();
		if( plante instanceof TwinSunFlower)
			return new TwinSunFlower();
		if( plante instanceof TallNut)
			return new TallNut();
		if( plante instanceof TorchWood)
			return new TorchWood();
		if( plante instanceof CherryBomb)
			return new CherryBomb();
		return null;
	}

}
