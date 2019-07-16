
public class Plante {
	
	private Boolean EstVivante;
	//private String NomPlante;
	
	private Integer ViePlante;
	private Integer DegasPlante;
	private Integer VitesseAttaquePlante;
	
	public Boolean getEstVivante() {
		return EstVivante;
	}
	public void setEstVivante(Boolean estVivante) {
		EstVivante = estVivante;
	}
    public  String Attaque(String MonAttaque)
	{
		return "Je fait mon Attaque de "+ MonAttaque ;
	}
	public Integer getVitesseAttaquePlante() {
		return VitesseAttaquePlante;
	}
	public void setVitesseAttaquePlante(Integer vitesseAttaquePlante) {
		VitesseAttaquePlante = vitesseAttaquePlante;
	}
	public Integer getDegasPlante() {
		return DegasPlante;
	}
	public void setDegasPlante(Integer degasPlante) {
		DegasPlante = degasPlante;
	}
	public Integer getViePlante() {
		return ViePlante;
	}
	public void setViePlante(Integer viePlante) {
		ViePlante = viePlante;
	}

	
	

}
