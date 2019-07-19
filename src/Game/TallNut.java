package Game;

public class TallNut extends Plante
{

	public static String desc = "TallNut : N'attaque pas. Bloque les zombies. Placement sur Nut";
	public TallNut()
	{
		  super(8000,0,0,125, "image/TallNut.png","image/TallNutOpaque.png",desc);
	}

	@Override
	   public int Action(Game gl)
		{
		return 0;
		}
}
