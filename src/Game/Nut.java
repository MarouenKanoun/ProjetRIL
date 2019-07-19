package Game;

public class Nut extends Plante
{
	  public Nut()
	  {
		    super(3600,0,0,50, "image/Nut.png");
	  }

	  @Override
		 public int Action(Game gl)
		  {
		  return -1;
		  }
}
