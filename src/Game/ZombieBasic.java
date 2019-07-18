package Game;

public class ZombieBasic extends ZombieGeneric {
	private final static String zombieImage = "image/zombie_base.png";
	private final static int health = 100;
	private final static int speed = 1;
	private final static int hitPoint = 50;
	
	
	public ZombieBasic() {
		super(health, speed, hitPoint, zombieImage);
	}
}


