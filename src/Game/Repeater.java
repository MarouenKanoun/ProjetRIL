package Game;

public class Repeater extends Plante
{
  	public static int NombreDeTir = 2;

	public static String desc = "Repeater : Attaque 2 fois à 20 dégats/tir ";
  	public Repeater()
  	{
  	    super(300,3,20, 200, "image/Repeater.png",  "image/RepeaterOpaque.png", desc);

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
