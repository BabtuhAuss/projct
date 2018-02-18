package vues;

import java.sql.DriverManager;
import java.sql.SQLException;

import enums.ActionEvenement;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import modeles.*;

public class FenetrePrincipale extends Application {
	
	/**
	 * La scene qui nous permet d'ajouter nos panels
	 */
	Scene scene;
	
	/**
	 * La fenêtre principale
	 */
	Stage primaryStage;
	
	/**
	 * Un panel qui permet de placer
	 * des composants/panels aux différents pôles
	 * (nord, sud, ouest, est, ...)
	 */
	BorderPane borderPane;
	
	AccueilPanel accueilPanel;
	GestionEvenementPanel gestionEvenementPanel;
	
	public static void main(String[] args) {
		try {
		    Class.forName( "com.mysql.jdbc.Driver" );
		} catch ( ClassNotFoundException e ) {
		    
		}
		Application.launch(FenetrePrincipale.class, args);
	}
	
	@Override
	  public void start(Stage primaryStage) throws Exception {

    	this.primaryStage = primaryStage;
    	
        primaryStage.setTitle(Data.TITRE_FENETRE);
        primaryStage.getIcons().add(new Image(Data.PATH_ICONIMAGE));
        
        borderPane = new BorderPane();

        scene = new Scene(borderPane, 1400, 700);
        scene.getStylesheets().add("bootstrap3.css");
        

        accueilPanel = new AccueilPanel(this);
        gestionEvenementPanel = new GestionEvenementPanel(this);

        borderPane.prefHeightProperty().bind(scene.heightProperty());
        borderPane.prefWidthProperty().bind(scene.widthProperty());
        
        borderPane.setCenter(accueilPanel);
        
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(1000);
        primaryStage.show();
	  }
	
	/**
     * Méthode qui permet de changer de panel
     * 
     */
    public void changerPanel(String panel, Evenement event) {
    	if(panel.equals("start")) {
    		borderPane.setCenter(accueilPanel);
    		accueilPanel.refreshData();
    	}
    	if(panel.equals("viewevent")) {
    		borderPane.setCenter(gestionEvenementPanel);
    		gestionEvenementPanel.openWithAction(ActionEvenement.CONSULTATION, event);
    	}
    	if(panel.equals("addevent")) {
    		borderPane.setCenter(gestionEvenementPanel);
    		gestionEvenementPanel.openWithAction(ActionEvenement.AJOUT, null);
    	}
    	if(panel.equals("editevent")) {
    		borderPane.setCenter(gestionEvenementPanel);
    		gestionEvenementPanel.openWithAction(ActionEvenement.MODIFICATION, event);
    	}
    }
}
