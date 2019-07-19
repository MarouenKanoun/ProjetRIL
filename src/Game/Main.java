package Game;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Plante GrosseNoix=new Nut();
       Plante GrossePoire=new PeaShooter();
       Plante GrosseSoleil= new SunFlower();

       System.out.println("je suis une noix est ma vitesse est " +GrosseNoix.Cooldown);
       System.out.println("je suis une noix est mes degats sont " + GrosseNoix.getDegatPlante());
       System.out.println("----------------------------------------------------------------------------");
       System.out.println("je suis une une poire est ma vitesse est " +GrossePoire.Cooldown);
       System.out.println("je suis une poire est mes degats sont " +GrossePoire.getDegatPlante());
       System.out.println("----------------------------------------------------------------------------");
       System.out.println("je suis une soleil ma vitesse est " + GrosseSoleil.Cooldown +" mes degats sont "+ GrosseSoleil.getDegatPlante());
       System.out.println("----------------------------------------------------------------------------");

     //  PanelLocal panel = new PanelLocal();
      // panel.ajouterCasesBlanches(x);
}
}
