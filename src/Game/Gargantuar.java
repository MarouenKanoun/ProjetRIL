package Game;

public class Gargantuar extends ZombieGeneric {
	private final static String zombieImage = "image/Gargantuar.png";
	private final static int health = 200;
	private final static int speed = 14;
	private final static int hitPoint = 4000;
	private final static int score = 3000;


	public Gargantuar() {
		super(health, speed, hitPoint, zombieImage, score);
	}
}
