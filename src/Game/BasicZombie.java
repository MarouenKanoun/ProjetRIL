package Game;

public class BasicZombie extends ZombieGeneric {
	private final static String zombieImage = "image/BasicZombie.png";
	private final static int health = 200;
	private final static int speed = 10;
	private final static int hitPoint = 100;
	private final static int score = 20;
	private final static String name = "BasicZombie";


	public BasicZombie() {
		super(health, speed, hitPoint, zombieImage, score, name);
	}
}
