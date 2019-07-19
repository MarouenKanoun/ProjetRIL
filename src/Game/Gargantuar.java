package Game;

public class Gargantuar extends ZombieGeneric {
	private final static String zombieImage = "src/Game/Gargantuar.png";
	private final static int health = 3600;
	private final static int speed = 14;
	private final static int hitPoint = 4000;
	private final static int score = 5000;
	private final static String name = "Gargantuar";


	public Gargantuar() {
		super(health, speed, hitPoint, zombieImage, score, name);
	}
}
