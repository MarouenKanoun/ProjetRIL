package Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JFrame {
	
	public static final Plante[] TableauDePlante = new Plante[]{
			new Shovel(),
			new SunFlower(),
			new TwinSunFlower(),
			new PeaShooter(),
			new Repeater(),
			new Gatling(),
			new TorchWood(),
			new Nut(),
			new TallNut()};
		public static final ZombieGeneric[] TableauDeZombie = new ZombieGeneric[]{
				new Gargantuar(),
				new BucketheadZombie(),
				new ConeheadZombie(),
				new Imp(),
				new BasicZombie()};
	public static JButton BoutonMemoire = null;
	public static JButton BoutonTour = null;
	public static JButton BoutonSoldeSoleil = null;
	public static JButton BoutonScore = null;
	public static Plante PlanteEnMemoire = null;
	public static CaseData[][] CaseDatas;
	public static CaseData[] Garden = new CaseData[9];
	public static int SoldeSoleil;
	public static int Tour;
	public static int Vague;
	public static int State;
	public static int ScoreGame;

	public static String separator = System.getProperty("line.separator");
	public static ArrayList<ZombieGeneric> ZombieNeedToSpawn = new ArrayList<ZombieGeneric>();
	public static int Credit = 0;
	public final static double TauxDeMultiplication = 1.2;
	public boolean lose = false;
	public JPanel container = new JPanel();
	public static long tempsTour = 500;

	

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
				boolean hasBeenAddJButton = false;
				JButton jb = new JButton();
				jb.setBackground(new Color(0x64FF64));
				Boolean rentre = false;
				if (i == 0 ||i == largeurMax - 1||j == 0 || j == longueurMax - 1) {
					jb.setBackground(new Color(0x00FF00));
					jb.setEnabled(false);
					rentre = true;
				}
				if (i == 0 && j == 0) {
					BoutonMemoire = jb;
					jb.setEnabled(true);
					rentre = true;
				}
				if (i == 2 && j == 0) {
					jb.setEnabled(true);  
					jb.setIcon(new ImageIcon(new ImageIcon("image/Sun.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT)));
					rentre = true;
				}
				
				if (i == 3 && j == 0) {
					BoutonSoldeSoleil = jb;
					jb.setText(SoldeSoleil +" Soleils");
					
					rentre = true;
				}
				if (i == 0 && j == 10) {
					BoutonScore = jb;
					BoutonScore.setText("Score : " + ScoreGame);
					rentre = true;
				}
				if (i == 1 && j == 10) {
					BoutonTour = jb;
					rentre = true;
				}
				
				if (i == 0 && j>2 && j<8) {
					jb.setIcon(TableauDeZombie[7-j].IconZombie);
					String str = TableauDeZombie[7-j].Name + "\n : Niveau : " + (j-2)+ separator + " Vie : "+ TableauDeZombie[7-j].PvMax +separator + "\n Vitesse : " + TableauDeZombie[7-j].Cooldown;
					jb.setToolTipText(str);
					jb.setEnabled(true);
					rentre = true;
				}
				
				if(i == largeurMax - 1 && j>0 && j<10) {
					clickActionGetPlante(jb, TableauDePlante[j-1]);
					jb.setBackground(new Color(0xB8860B));
					
					jb.setIcon(TableauDePlante[j-1].ImagePlante);
					jb.setEnabled(false);
					Garden[j-1] = new CaseData();
					Garden[j-1].JButtonCase = jb;
					Garden[j-1].PlanteCase = TableauDePlante[j-1];
					rentre = true;
				}
				
				if (!rentre) {
					CaseData cd = new CaseData();
					cd.JButtonCase = jb;
					if(j == 9)
						jb.setBackground(new Color(0xA9A9A9));
					if (j != 9)
						clickActionSetPlante(cd);
					rentre = true;
					CaseDatas[i - 1][j - 1] = cd;

				}
				if(!hasBeenAddJButton)
					container.add(jb);

			}

		}
		RefreshGarden();
		this.setContentPane(container);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void Reset() {
		Garden = new CaseData[9];
		SoldeSoleil = 200; 
		Tour = 0; 
		Vague = 0;
		Credit = 0;
		State = 0;
		ScoreGame = 0;	
	}
	public static void main(String[] args) {
		boolean wantContinue = true;
		while (wantContinue) {
			Reset();
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
		while(!(Vague == 3 && AnyZombie()) && lose == false) {
			this.nextTurn();
			try {
				Thread.sleep(tempsTour);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (lose)
			return "Tu as perdu Bouh";

		return "Tu as gagné GG";

	}
	public void SetTourBouton() {
		int seconds = (Tour%120)/2;
		int minutes = Tour/120;
		if (seconds < 10)
			BoutonTour.setText(minutes +" : 0"+ seconds +separator + "\n Vague : "+ Vague+"/3");
		else
			BoutonTour.setText(minutes +" : "+ seconds +separator +" Vague : "+ Vague+"/3");
	}
	public void nextTurn() {
		Tour++;
		SetTourBouton();
		//	Plante action
		actionPlante();
		// Zombie action
		actionZombie();

		// Zombie spawn
		getZombiesToSpawn();
		spawnZombies();
		RefreshGarden();



	}

private void actionZombie() {

		for (int i = 0; i < CaseDatas.length; i++) {
			for (int j = 0; j < CaseDatas[0].length; j++) {
				if(CaseDatas[i][j].ZombieCase != null)
					if(CaseDatas[i][j].ZombieCase.Action()){
						moveZombie(i,j);
						attaqueZombie(i,j);
					}
				}
				}
			}
	private void attaqueZombie(int i, int j) {
		if(j == 0)
			return;
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
			lose = true;
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
						CaseDatas[i][j].JButtonCase.setIcon(CaseDatas[i][j].PlanteCase.ImagePlante);
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
					CaseDatas[i][j].JButtonCase.setIcon(CaseDatas[i][j].PlanteCase.ImagePlanteOpaque);
				 	}
				}
			}
		}
	}
	public boolean AnyZombie() {
		for (int i = 0; i < CaseDatas.length; i++) 
			for (int j = 0; j < CaseDatas[0].length; j++) 
				if(CaseDatas[i][j].ZombieCase != null) 
					return false;
		return true;
	}
	public ZombieGeneric ZombieFound(int _i, int _j, boolean destroy)
	{
			for (int j = _j; j < CaseDatas[0].length; j++) {
				if(CaseDatas[_i][j].ZombieCase != null) {
					if(destroy) {
						Credit += CaseDatas[_i][j].ZombieCase.Score*TauxDeMultiplication;
						ScoreGame += CaseDatas[_i][j].ZombieCase.Score;
						BoutonScore.setText("Score : " + ScoreGame);
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
		if(Vague == 3)
			return;
		if (Tour == 60)
			Credit += 20;

		if(ScoreGame > 150 && Vague == 0){
			if(!ligneIsEmpty())
				return;
			for(int i = 0; i < 5; i++) {
				ZombieGeneric zg = GetNewZombie(new Imp());
				zg.Score = 0;
				ZombieNeedToSpawn.add(zg);
				
			}		
			Vague++;
			return;
		}
		if(ScoreGame > 4500 && Vague == 1){
			if(!ligneIsEmpty())
				return;
			for(int i = 0; i < 5; i++) {
				ZombieGeneric zg = GetNewZombie(new BucketheadZombie());
				zg.Score = 0;
				ZombieNeedToSpawn.add(zg);
				
			}	
			Credit += 500;
			Vague++;
		}
		if(ScoreGame > 30000 && Vague == 2){
			if(!ligneIsEmpty())
				return;
			for(int i = 0; i < 5; i++) {
				ZombieGeneric zg = GetNewZombie(new Gargantuar());
				zg.Score = 0;
				ZombieNeedToSpawn.add(zg);
				
			}	
			Credit += 500;
			Vague++;
		}
		if(ScoreGame > 400 && State == 0){
			Credit += 200;
			State++;
		}

		if(ScoreGame > 1500 && State == 1){
			Credit += 1000;
			State++;
		}
		
		if(Vague == 2 && State == 2){
			Credit += 5000;
			State++;
		}

		for(int i = 0; i+State < TableauDeZombie.length; i++)
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

public static void RefreshGarden() {
	for(int i = 0; i < Garden.length; i++) {
		if(SoldeSoleil >= Garden[i].PlanteCase.Price) {
			Garden[i].JButtonCase.setEnabled(true);
		}
		else {
			Garden[i].JButtonCase.setEnabled(false);
		}
	}
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
				jb.setToolTipText(toset.Description);
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
