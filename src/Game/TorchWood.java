package Game;

public class TorchWood extends Plante
{
	public static String desc = "TorchWood : N'attaque pas. Double les dégats des plantes à sa gauche";
	  public TorchWood()
	  {
		    super(300,0,0,175, "image/TorchWood.png","image/TorchWood.png",desc);
	  }

	  @Override
		 public int Action(Game gl)
		  {
		  return 0;
		  }
}
