package Game;

public class BucketheadZombie extends ZombieGeneric {
	private final static String zombieImage = "image/BucketheadZombie.png";
	private final static int health = 1300;
	private final static int speed = 10;
	private final static int hitPoint = 100;
	private final static int score = 1000;
	private final static String name = "BucketheadZombie";


	public BucketheadZombie() {
		super(health, speed, hitPoint, zombieImage, score, name);
	}
}
