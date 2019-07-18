package Game;

public class Zombie {
	public static int SpawnNumber = 0;

	public static void zombie(String[] args) {
		ZombieBasic jeanBernard = new ZombieBasic();
//		ZombieBasic.spawn(jeanBernard);
	}

	public static int spawnZombies(int Tour) {
		if (Tour % 5 == 0) {
			SpawnNumber = Tour / 20;

			if (SpawnNumber > 5)
				return 5;
			return SpawnNumber;
		}
		return 0;
	}

}
