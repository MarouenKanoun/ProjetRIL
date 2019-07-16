
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Plante GrossePlante = new Plante();
       Plante GrosseNoix=new Noix();
       
       System.out.println(GrossePlante.Attaque("La Grosse Boule"));
       System.out.println("je suis une noix est ma vitesse est " +GrosseNoix.getVitesseAttaquePlante());
       System.out.println("je suis une noix est mes degas sont " + GrosseNoix.getDegasPlante());
       
	}

}
