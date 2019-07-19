package Game;

public class TwinSunFlower extends Plante
{
	public boolean firstThrow = true;
  	public int sunValue = 50;
	public TwinSunFlower()
	{
		  super(300,24,0,150, "image/TwinSunFlower.png");
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
			 gl.BoutonSoldeSoleil.setText(gl.SoldeSoleil +" Soleils");
			 Cooldown = CooldownDefault;
			 return 0;
		 }
		 return -1;
		}
}
