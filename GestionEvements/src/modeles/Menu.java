package modeles;

public class Menu {
	
	private String entrees;
	private String plats;
	private String desserts;
	private String canapes;
	private int id;
	
	public Menu() {
		this.entrees = "";
		this.plats = "";
		this.desserts = "";
		this.canapes = "";
		this.id = 0;
	}
	
	public Menu(String entrees, String plats, String desserts, String canapes, int id) {
		super();
		this.entrees = entrees;
		this.plats = plats;
		this.desserts = desserts;
		this.canapes = canapes;
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
	public String getCanapes() {
		return canapes;
	}
	public void setCanapes(String canapes) {
		this.canapes = canapes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
