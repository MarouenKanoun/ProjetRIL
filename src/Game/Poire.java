package Game;
import java.util.Random;

public class Poire extends Plante 
{
	private int n;
	private int i;
	
	public Poire()
	{
	    super();
	    this.setVitesseAttaquePlante(10);
	    this.setDegasPlante(10);
	    this.setCout(100);
		
	}
	  public String LanceBoule(Integer VitesseAttaque)
	  {
		  
	        n = VitesseAttaque;
	        for(int i = 1; i <= VitesseAttaque; i++)
	        {
	          i++;
	        }
	       return "je lance ma boule  "+i;
	  }
}
