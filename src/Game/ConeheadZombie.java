package Game;

public class ConeheadZombie extends ZombieGeneric {
	private final static String zombieImage = "image/ConeheadZombie.png";
	private final static int health = 560;
	private final static int speed = 10;
	private final static int hitPoint = 100;
	private final static int score = 100;


	public ConeheadZombie() {
		super(health, speed, hitPoint, zombieImage, score);
	}
}
