package sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import modeles.ArguComm;
import modeles.Evenement;
import modeles.Menu;

public class DatabaseAccess {
	
	private static String dbInfos = "jdbc:mysql://145.239.72.20:3306/test_bap";
	private static String user = "root";
	private static String passwd = "s3cur3$78180";
	
	private static Connection createConnection() {
		try {
			return DriverManager.getConnection(dbInfos, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Evenement> getAllEventsShorted() throws SQLException{
		List<Evenement> listEvents = new ArrayList<Evenement>();
		Connection db = createConnection();
		Statement stmt = db.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT nom, nbpersonnes, horaireDebut, salon, id FROM evenement");
		while(rset.next()) {
			Evenement event = new Evenement(rset.getString(1), rset.getInt(2), rset.getString(3), rset.getString(4), rset.getInt(5));
			listEvents.add(event);
		}
		rset.close();
		stmt.close();
		db.close();
		return listEvents;
	}

	public static void deleteEvenement(Evenement event) throws SQLException {
		
		event = getEvenementFromShortEvent(event);
		
		Connection db = createConnection();
		Statement stmt = db.createStatement();
		
		stmt.executeUpdate("DELETE FROM evenement WHERE id = " + event.getID());
		
		stmt.executeUpdate("INSERT INTO evenement_archive VALUES(null, '" + event.getNom() + "', " + event.getNbPersonnes() + ", '" + event.getHoraireDebut() + "', '" + event.getSalon() + "', '" + event.getTypeReception() + "', " + event.getMenu().getId() + ", " + event.getArguComm().getId() + ", '" + event.getVaisselle() + "', '" + event.getDressageCouvert() + "')");
		stmt.close();
		db.close();
	}
	
	public static Evenement getEvenementFromShortEvent(Evenement event) throws SQLException {
		
		Connection db = createConnection();
		Statement stmt = db.createStatement();
		ResultSet rset = stmt.executeQuery("SELECT idMenu, idArguComm, typeReception, vaisselle, dressageCouvert FROM evenement WHERE id = " + event.getID());
		rset.first();
		event.setTypeReception(rset.getString(3));
		event.setVaisselle(rset.getString(4));
		event.setDressageCouvert(rset.getString(5));
		int idMenu = rset.getInt(1);
		int idArguComm = rset.getInt(2);
		rset.close();
		rset = stmt.executeQuery("SELECT entrees, plats, dessert, canape, id FROM menu WHERE id = " + idMenu);
		rset.first();
		Menu menu = new Menu(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getInt(5));
		rset.close();
		event.setMenu(menu);
		rset = stmt.executeQuery("SELECT entrees, plats, dessert, vinBlanc, vinRouge, champagne, id FROM argucommercial WHERE id = " + idArguComm);
		rset.first();
		ArguComm arguComm = new ArguComm(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5), rset.getString(6), rset.getInt(7));
		rset.close();
		event.setArguComm(arguComm);
		stmt.close();
		db.close();
		return event;
	}
	
	public static void addEvenement(Evenement event) throws SQLException {
		Connection db = createConnection();
		
		int idMenu = 0;
		int idArguComm = 0;
		int idEvent = 0;
		
		String sqlRequestMenu = "INSERT INTO menu VALUES (null, ?, ?, ?, ?)";
		
		PreparedStatement stmt = db.prepareStatement(sqlRequestMenu,
                Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, event.getMenu().getEntrees());
		stmt.setString(2, event.getMenu().getPlats());
		stmt.setString(3, event.getMenu().getDesserts());
		stmt.setString(4, event.getMenu().getCanapes());
		
		stmt.executeUpdate();
		ResultSet generateKeys = stmt.getGeneratedKeys();
		generateKeys.next();
		idMenu = generateKeys.getInt(1);
		stmt.close();
		
		String sqlRequestArguComm = "INSERT INTO argucommercial VALUES (null, ?, ?, ?, ?, ?, ?)";
		
		stmt = db.prepareStatement(sqlRequestArguComm,
                Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, event.getArguComm().getEntrees());
		stmt.setString(2, event.getArguComm().getPlats());
		stmt.setString(3, event.getArguComm().getDesserts());
		stmt.setString(4, event.getArguComm().getVinBlanc());
		stmt.setString(5, event.getArguComm().getVinRouge());
		stmt.setString(6, event.getArguComm().getChampagne());
		
		stmt.executeUpdate();
		generateKeys = stmt.getGeneratedKeys();
		generateKeys.next();
		idArguComm = generateKeys.getInt(1);
		stmt.close();
		
		String sqlRequestEvenement = "INSERT INTO evenement VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		stmt = db.prepareStatement(sqlRequestEvenement,
                Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, event.getNom());
		stmt.setInt(2, event.getNbPersonnes());
		stmt.setString(3, event.getHoraireDebut());
		stmt.setString(4, event.getSalon());
		stmt.setString(5, event.getTypeReception());
		stmt.setInt(6, idMenu);
		stmt.setInt(7, idArguComm);
		stmt.setString(8, event.getVaisselle());
		stmt.setString(9, event.getDressageCouvert());
		
		stmt.executeUpdate();
		generateKeys = stmt.getGeneratedKeys();
		generateKeys.next();
		idEvent = generateKeys.getInt(1);
		stmt.close();
		db.close();
		
		event.getMenu().setId(idMenu);
		event.getArguComm().setId(idArguComm);
		event.setID(idEvent);
	}
	
	public static void updateEvenement(Evenement event) throws SQLException {
		Connection db = createConnection();
		
		String sqlRequestMenu = "UPDATE menu SET entrees = ?, plats = ?, dessert = ?, canape = ? WHERE id = ?";
		
		PreparedStatement stmt = db.prepareStatement(sqlRequestMenu,
                Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, event.getMenu().getEntrees());
		stmt.setString(2, event.getMenu().getPlats());
		stmt.setString(3, event.getMenu().getDesserts());
		stmt.setString(4, event.getMenu().getCanapes());
		stmt.setInt(5, event.getMenu().getId());
		
		stmt.executeUpdate();
		stmt.close();
		
		String sqlRequestArguComm = "UPDATE argucommercial SET entrees = ?, plats = ?, dessert = ?, vinBlanc = ?, vinRouge = ?, champagne = ? WHERE id = ?";
		
		stmt = db.prepareStatement(sqlRequestArguComm,
                Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, event.getArguComm().getEntrees());
		stmt.setString(2, event.getArguComm().getPlats());
		stmt.setString(3, event.getArguComm().getDesserts());
		stmt.setString(4, event.getArguComm().getVinBlanc());
		stmt.setString(5, event.getArguComm().getVinRouge());
		stmt.setString(6, event.getArguComm().getChampagne());
		stmt.setInt(7, event.getArguComm().getId());
		
		stmt.executeUpdate();
		stmt.close();
		
		String sqlRequestEvenement = "UPDATE evenement SET nom = ?, nbpersonnes = ?, horaireDebut = ?, salon = ?, typeReception = ?, vaisselle = ?, dressageCouvert = ? WHERE id = ?";
		
		stmt = db.prepareStatement(sqlRequestEvenement,
                Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, event.getNom());
		stmt.setInt(2, event.getNbPersonnes());
		stmt.setString(3, event.getHoraireDebut());
		stmt.setString(4, event.getSalon());
		stmt.setString(5, event.getTypeReception());
		stmt.setString(6, event.getVaisselle());
		stmt.setString(7, event.getDressageCouvert());
		stmt.setInt(8, event.getID());
		
		stmt.executeUpdate();
		stmt.close();
		db.close();
	}

}
