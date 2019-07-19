package Game;

public class SunFlower extends Plante
{
	public static String desc = "SunFlower : N'attaque pas. Produit 25 soleils";
	public boolean firstThrow = true;
	public final int sunValue = 25;
	  public SunFlower()
	  {
		    super(300,24,0,50, "image/SunFlower.png","image/SunFlowerOpaque.png",desc);

	  }

	  @Override
		 public int Action(Game gl)
		  {
			  if(firstThrow){
				  firstThrow = false;
				  Cooldown = 7;
			  }

		   Cooldown--;
		   if(Cooldown == 0) {
			   gl.SoldeSoleil += sunValue;
				 gl.RefreshGarden();
				 gl.BoutonSoldeSoleil.setText(gl.SoldeSoleil +" Soleils");
			   Cooldown = CooldownDefault;
			   return 0;
		   }
		   return -1;
		  }
}
