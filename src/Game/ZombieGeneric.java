package Game;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ZombieGeneric {
	public int Pv;
	public int PvMax;
	public int Speed;
	public int Cooldown;
	public int Degat;
	public int Score;
	public boolean CanAction;
	ImageIcon IconZombie;
	public String ImageZombie;


	public ZombieGeneric(int _pv, int _speed,int _degat, String _imageZombie, int _score) {
		Pv = _pv;
		PvMax = _pv;
		Degat = _degat;
		Cooldown = _speed;
		Speed = _speed;
		ImageZombie = _imageZombie;
		Score = _score;
		IconZombie = new ImageIcon(
				new ImageIcon(_imageZombie).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));

	}

	public boolean Action()
	{
		Cooldown--;
		if(Cooldown <= 0) {
			return true;
		  }
		return false;

	}

	public void action() {
		// TODO Auto-generated method stub

	}

}
