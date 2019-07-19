package Game;

public class CherryBomb extends Plante
{
	// Plante pas entierement implimenté donc retirer du jeu
	

	public static String desc = "Cette plante n'attaque pas, elle ne sert qu'à ralentir les zombies";
	  public CherryBomb()
	  {
		    super(10000000,2,1800,150, "src/Game/CherryBomb.png",  "src/Game/CherryBomb.png", desc);
	  }

	  @Override
    	 public int Action(Game gl)
    	  {
    		Cooldown--;
    		if(Cooldown <= 0) {

    			return 1;
    		  }
    		return -1;
    	  }

    	@Override
    	public int Attaque() {
    		Cooldown = CooldownDefault;
    		return this.DegatPlante;
    	}
}
