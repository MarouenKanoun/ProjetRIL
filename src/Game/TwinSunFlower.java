package Game;

public class TwinSunFlower extends Plante
{
	public static String desc = "TwinSunFlower : N'attaque pas. Produit 100 soleils. Placement sur SunFlower";
	public boolean firstThrow = true;
  	public int sunValue = 100;
	public TwinSunFlower()
	{
		  super(300,24,0,150, "src/Game/TwinSunFlower.png", "src/Game/TwinSunFlowerOpaque.png",desc);
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
			 Game.SoldeSoleil += sunValue;
			 Game.RefreshGarden();
			 Game.BoutonSoldeSoleil.setText(Game.SoldeSoleil +" Soleils");
			 Cooldown = CooldownDefault;
			 return 0;
		 }
		 return -1;
		}
}
