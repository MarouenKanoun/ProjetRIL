package Game;

public class ConeheadZombie extends ZombieGeneric {
	private final static String zombieImage = "src/Game/ConeheadZombie.png";
	private final static int health = 560;
	private final static int speed = 10;
	private final static int hitPoint = 100;
	private final static int score = 200;
	private final static String name = "ConeheadZombie";


	public ConeheadZombie() {
		super(health, speed, hitPoint, zombieImage, score, name);
	}
}
