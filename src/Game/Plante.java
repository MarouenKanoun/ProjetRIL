package Game;
import java.awt.*;


import javax.swing.*;
public class Plante {

	public int ViePlante;
  	public int Cooldown;
  	public int CooldownDefault;
	public int Price;
	public int DegatPlante;
	public String Description;
	public ImageIcon ImagePlante;
	public ImageIcon ImagePlanteOpaque;
	public Plante(int vie, int vitesse, int degat,  int price, String pathImage, String pathImageOpaque,String description) {
		ViePlante = vie;
		DegatPlante = degat;
		Cooldown = 0;
		CooldownDefault = vitesse;
		Price = price;
		Description = description+" Price : "+ price;
		ImagePlante = new ImageIcon(new ImageIcon(pathImage).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		ImagePlanteOpaque = new ImageIcon(new ImageIcon(pathImageOpaque).getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		}

	public int getDegatPlante() {
		return DegatPlante;
	}
	public void setDegatPlante(int degatPlante) {
		DegatPlante = degatPlante;
	}
	public int getViePlante() {
		return ViePlante;
	}
	public void setViePlante(int viePlante) {
		ViePlante = viePlante;
	}

	public int Action(Game gl) {
		return -1;
	}

	public int Attaque() {
		return 0;
	}




}
