package Game;
import java.util.Random;

public class Poire extends Plante 
{
	private int n;
	public Poire()
	{
	    super();
	    this.setVitesseAttaquePlante(10);
	    this.setDegasPlante(10);
		
	}
	  public void LanceBoule(Integer VitesseAttaque)
	  {
	        n = VitesseAttaque;
	        for(int i = 1; i <= VitesseAttaque; i++)
	        {
	          System.out.println("je lance ma boule  "+i);
	        }
	  }
}
