package Game;

public class TorchWood extends Plante
{
	public static String desc = "TorchWood : N'attaque pas. Double les d�gats des plantes � sa gauche";
	  public TorchWood()
	  {
		    super(300,0,0,175, "src/Game/TorchWood.png","src/Game/TorchWood.png",desc);
	  }

	  @Override
		 public int Action(Game gl)
		  {
		  return 0;
		  }
}
