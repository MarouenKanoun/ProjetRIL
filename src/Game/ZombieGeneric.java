package Game;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ZombieGeneric {
	public int pv;
	public int speed;
	ImageIcon iconZombie; 
	public String imageZombie;
	private final int zombieSize = 32;
	private final int zombieDrowSize = 48;
	
	public ZombieGeneric(int _pv, int _speed, String _imageZombie) { 
		pv = _pv;
		speed = _speed;
		imageZombie = _imageZombie;
		iconZombie = new ImageIcon(
				new ImageIcon("image/zombie_base.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		
	}
	
	public static void spawn(ZombieGeneric Z, JButton jb) {
		
		jb.setIcon(Z.iconZombie);
	}
	
}
