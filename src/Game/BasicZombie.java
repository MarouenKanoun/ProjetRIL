package Game;

public class BasicZombie extends ZombieGeneric {
	private final static String zombieImage = "image/BasicZombie.png";
	private final static int health = 200;
	private final static int speed = 10;
	private final static int hitPoint = 100;
	private final static int score = 20;


	public BasicZombie() {
		super(health, speed, hitPoint, zombieImage, score);
	}
}
