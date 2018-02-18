package modeles;

public class ArguComm {

	private String entrees;
	private String plats;
	private String desserts;
	private String vinBlanc;
	private String vinRouge;
	private String champagne;
	private int id;
	
	public ArguComm() {
		this.entrees = "";
		this.plats = "";
		this.desserts = "";
		this.vinBlanc = "";
		this.vinRouge = "";
		this.champagne = "";
		this.id = 0;
	}
	
	public ArguComm(String entrees, String plats, String desserts, String vinBlanc, String vinRouge, String champagne, int id) {
		super();
		this.entrees = entrees;
		this.plats = plats;
		this.desserts = desserts;
		this.vinBlanc = vinBlanc;
		this.vinRouge = vinRouge;
		this.champagne = champagne;
		this.id = id;
	}
	public String getEntrees() {
		return entrees;
	}
	public void setEntrees(String entrees) {
		this.entrees = entrees;
	}
	public String getPlats() {
		return plats;
	}
	public void setPlats(String plats) {
		this.plats = plats;
	}
	public String getDesserts() {
		return desserts;
	}
	public void setDesserts(String desserts) {
		this.desserts = desserts;
	}
	public String getVinBlanc() {
		return vinBlanc;
	}
	public void setVinBlanc(String vinBlanc) {
		this.vinBlanc = vinBlanc;
	}
	public String getVinRouge() {
		return vinRouge;
	}
	public void setVinRouge(String vinRouge) {
		this.vinRouge = vinRouge;
	}
	public String getChampagne() {
		return champagne;
	}
	public void setChampagne(String champagne) {
		this.champagne = champagne;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
