package Game;

public class ZombieBasic extends ZombieGeneric {
	private final static String zombieImage = "image/zombie_base.png";
	private final static int health = 100;
	private final static int speed = 1;
	
	
	public ZombieBasic() {
		super(health, speed, zombieImage);
	}
}


