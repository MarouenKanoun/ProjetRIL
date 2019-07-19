package Game;

public class Gatling extends Plante
{
  	public static int NombreDeTir = 4;

  	public Gatling()
  	{
  	    super(300,3,20,400, "image/Gatling.png");

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
