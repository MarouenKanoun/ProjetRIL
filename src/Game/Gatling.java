package Game;

public class Gatling extends Plante
{
	public static String desc = "Gatling : Attaque 4 fois à 20 dégats/tir. Placement sur Repeater ";
  	public static int NombreDeTir = 4;

  	public Gatling()
  	{
  	    super(300,3,20,400, "src/Game/Gatling.png","src/Game/GatlingOpaque.png",desc);

  	}

  	@Override
  	 public int Action(Game gl)
  	  {
  		Cooldown--;
  		if(Cooldown <= 0) {
  			return NombreDeTir;
  		  }
  		return -1;
  	  }

  	@Override
  	public int Attaque() {
  		Cooldown = CooldownDefault;
  		return this.DegatPlante;
  	}
}
