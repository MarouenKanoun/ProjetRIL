package Game;

public class Imp extends ZombieGeneric {
	private final static String zombieImage = "src/Game/Imp.png";
	private final static int health = 200;
	private final static int speed = 5;
	private final static int hitPoint = 100;
	private final static int score = 80;
	private final static String name = "Imp";


	public Imp() {
		super(health, speed, hitPoint, zombieImage, score, name);
	}
}
