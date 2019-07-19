package Game;

public class Nut extends Plante
{
	public static String desc = "Nut : N'attaque pas. Bloque les zombies.";
	  public Nut()
	  {
		    super(3600,0,0,50, "image/Nut.png", "image/NutOpaque.png",desc);
	  }

	  @Override
		 public int Action(Game gl)
		  {
		  return 0;
		  }
}
