package Game;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Plante GrossePlante = new Plante();
       Plante GrosseNoix=new Noix();
      Poire GrossePoire=new Poire();
       Soleil GrosseSoleil= new Soleil();
       
       System.out.println(GrossePlante.Attaque("La Grosse Boule"));
       System.out.println("je suis une noix est ma vitesse est " +GrosseNoix.getVitesseAttaquePlante());
       System.out.println("je suis une noix est mes degas sont " + GrosseNoix.getDegasPlante());
       System.out.println("----------------------------------------------------------------------------");
       System.out.println("je suis une une poire est ma vitesse est " +GrossePoire.getVitesseAttaquePlante());
       System.out.println("je suis une poire est mes degas sont " +GrossePoire.getDegasPlante());
       System.out.println("----------------------------------------------------------------------------");
       System.out.println("je suis une soleil ma vitesse est " + GrosseSoleil.getVitesseAttaquePlante() +" mes degas sont "+ GrosseSoleil.getDegasPlante() + " voila ce que je peut faire  :" + GrosseSoleil.Planter());
       System.out.println("----------------------------------------------------------------------------");
       GrossePoire.LanceBoule(10);
     //  PanelLocal panel = new PanelLocal();
      // panel.ajouterCasesBlanches(x);
}
}
