package Game;

public class CherryBomb extends Plante
{
	  public CherryBomb()
	  {
		    super(10000000,2,1800,150, "image/CherryBomb.png");
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
