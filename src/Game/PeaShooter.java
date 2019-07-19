package Game;

public class PeaShooter extends Plante
{
  	public final int NombreDeTir = 1;

  	public PeaShooter()
  	{
  	    super(300,3,20, 100, "image/PeaShooter.png");

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
  		return DegatPlante;
  	}
}
