package Game;

public class Imp extends ZombieGeneric {
	private final static String zombieImage = "image/Imp.png";
	private final static int health = 200;
	private final static int speed = 5;
	private final static int hitPoint = 100;
	private final static int score = 80;


	public Imp() {
		super(health, speed, hitPoint, zombieImage, score);
	}
}
