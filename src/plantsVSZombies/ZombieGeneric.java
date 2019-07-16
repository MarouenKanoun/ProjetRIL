package plantsVSZombies;

abstract class ZombieGeneric {
	public int pv;
	public int speed;
	public String imageZombie;
	private final int zombieSize = 32;
	private final int zombieDrowSize = 48;
	
	public ZombieGeneric(int _pv, int _speed, String _imageZombie) { 
		pv = _pv;
		speed = _speed;
		imageZombie = _imageZombie;
		
	}
	
	public void spawn() {
		
	}
	
}
