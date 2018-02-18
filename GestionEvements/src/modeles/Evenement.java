package modeles;

import javafx.collections.ObservableList;

public class Evenement {
	
	private String nom;
	private int nbPersonnes;
	private String horaireDebut;
	private String salon;
	private String typeReception;
	private Menu menu;
	private ArguComm arguComm;
	private String vaisselle;
	private String dressageCouvert;
	private int id;
	
	public Evenement() {
		this.nom = "";
		this.nbPersonnes = 0;
		this.horaireDebut = "";
		this.salon = "";
		this.typeReception = "";
		this.menu = new Menu();
		this.arguComm = new ArguComm();
		this.vaisselle = "";
		this.dressageCouvert = "";
		this.id = 0;
	}
	
	public Evenement(String nom, int nbPersonnes, String horaireDebut, String salon, String typeReception, Menu menu,
			ArguComm arguComm, String vaisselle, String dressageCouvert) {
		super();
		this.nom = nom;
		this.nbPersonnes = nbPersonnes;
		this.horaireDebut = horaireDebut;
		this.salon = salon;
		this.typeReception = typeReception;
		this.menu = menu;
		this.arguComm = arguComm;
		this.vaisselle = vaisselle;
		this.dressageCouvert = dressageCouvert;
	}

	public Evenement(String nom, int nbPersonnes, String horaireDebut, String salon, int id) {
		super();
		this.nom = nom;
		this.nbPersonnes = nbPersonnes;
		this.horaireDebut = horaireDebut;
		this.salon = salon;
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getNbPersonnes() {
		return nbPersonnes;
	}

	public void setNbPersonnes(int nbPersonnes) {
		this.nbPersonnes = nbPersonnes;
	}

	public String getHoraireDebut() {
		return horaireDebut;
	}

	public void setHoraireDebut(String horaireDebut) {
		this.horaireDebut = horaireDebut;
	}

	public String getSalon() {
		return salon;
	}

	public void setSalon(String salon) {
		this.salon = salon;
	}

	public String getTypeReception() {
		return typeReception;
	}

	public void setTypeReception(String typeReception) {
		this.typeReception = typeReception;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public ArguComm getArguComm() {
		return arguComm;
	}

	public void setArguComm(ArguComm arguComm) {
		this.arguComm = arguComm;
	}

	public String getVaisselle() {
		return vaisselle;
	}

	public void setVaisselle(String vaisselle) {
		this.vaisselle = vaisselle;
	}

	public String getDressageCouvert() {
		return dressageCouvert;
	}

	public void setDressageCouvert(String dressageCouvert) {
		this.dressageCouvert = dressageCouvert;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String[] convertShortEventToList() {
		String[] result = {nom, String.valueOf(nbPersonnes), horaireDebut, salon};
		return result;
	}
}
