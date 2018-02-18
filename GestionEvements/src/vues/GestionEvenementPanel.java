package vues;

import java.sql.SQLException;

import enums.ActionEvenement;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import modeles.Data;
import modeles.Evenement;
import sql.DatabaseAccess;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabClosingPolicy;

public class GestionEvenementPanel extends Group {

	/**
	 * Une référence à la fenêtre principale
	 */
	FenetrePrincipale fenetrePrincipale;
	
	ActionEvenement actionEvent;
	
	GeneralTab generalTab;
	MenuTab menuTab;
	ArguComTab arguComTab;
	
	Button saveEventButton;
	Button saveAndQuitEventButton;
	Button cancelButton;
	
	Evenement evenement;
	
	public GestionEvenementPanel(final FenetrePrincipale fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
		
		TabPane tabPane = new TabPane();
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		Tab tabGeneral = new Tab();
		tabGeneral.setText(Data.TAB_GENERAL);
		generalTab = new GeneralTab();
		tabGeneral.setContent(generalTab);
        
        Tab tabMenu = new Tab();
        tabMenu.setText(Data.TAB_MENU);
        menuTab = new MenuTab();
        tabMenu.setContent(menuTab);
        
        Tab tabArguCom = new Tab();
        tabArguCom.setText(Data.TAB_ARGUCOM);
        arguComTab = new ArguComTab();
        tabArguCom.setContent(arguComTab);
        
        tabPane.getTabs().addAll(tabGeneral, tabMenu, tabArguCom);
        
        saveEventButton = new Button(Data.BUTTON_SAVEEVENT);
		saveAndQuitEventButton = new Button(Data.BUTTON_SAVEANDQUITEVENT);
		cancelButton = new Button(Data.BUTTON_CANCEL);
		
		saveEventButton.getStyleClass().add("success");
		saveAndQuitEventButton.getStyleClass().add("success");
		cancelButton.getStyleClass().add("warning");
		
		saveEventButton.setOnAction(this::handleSaveEvent);
		saveAndQuitEventButton.setOnAction(this::handleSaveAndQuitEvent);
		cancelButton.setOnAction(this::handleCancelEvent);
		
		final HBox hbox2 = new HBox();
        hbox2.setSpacing(50);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(saveEventButton, saveAndQuitEventButton, cancelButton);
		
		BorderPane borderPane = new BorderPane();

        borderPane.setCenter(tabPane);
        borderPane.setBottom(hbox2);
        
        borderPane.prefHeightProperty().bind(fenetrePrincipale.scene.heightProperty());
        borderPane.prefWidthProperty().bind(fenetrePrincipale.scene.widthProperty());
        
        this.getChildren().add(borderPane);
	}
	
	public void openWithAction(ActionEvenement action, Evenement event) {
		this.actionEvent = action;
		evenement = null;
		generalTab.resetTab();
		menuTab.resetTab();
		arguComTab.resetTab();
		
		switch (action) {
		case AJOUT:
			prepareAjout();
			break;
		case CONSULTATION:
			prepareConsultation(event);
			break;
		case MODIFICATION:
			prepareModification(event);
			break;
		}
	}
	
	private void prepareAjout() {
		saveEventButton.setVisible(true);
		saveAndQuitEventButton.setVisible(true);
		evenement = new Evenement();
	}

	private void prepareConsultation(Evenement event) {
		saveEventButton.setVisible(false);
		saveAndQuitEventButton.setVisible(false);
		try {
			evenement = DatabaseAccess.getEvenementFromShortEvent(event);
			generalTab.setEvenementToFields(evenement);
			menuTab.setMenuToFields(evenement.getMenu());
			arguComTab.setArguCommToFields(evenement.getArguComm());
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert2 = new Alert(AlertType.ERROR, Data.ALERT_GETEVENT_KO, ButtonType.OK);
    		alert2.showAndWait();
    		fenetrePrincipale.changerPanel("start", null);
		}
	}

	private void prepareModification(Evenement event) {
		saveEventButton.setVisible(true);
		saveAndQuitEventButton.setVisible(true);
		try {
			evenement = DatabaseAccess.getEvenementFromShortEvent(event);
			generalTab.setEvenementToFields(evenement);
			menuTab.setMenuToFields(evenement.getMenu());
			arguComTab.setArguCommToFields(evenement.getArguComm());
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert2 = new Alert(AlertType.ERROR, Data.ALERT_GETEVENT_KO, ButtonType.OK);
    		alert2.showAndWait();
    		fenetrePrincipale.changerPanel("start", null);
		}
	}

	public boolean handleSaveEvent(ActionEvent event) {
		generalTab.updateEvenementWithFields(evenement);
		menuTab.updateMenuWithFields(evenement.getMenu());
		arguComTab.updateArguCommWithFields(evenement.getArguComm());
		if(actionEvent == ActionEvenement.AJOUT) {
			try {
				DatabaseAccess.addEvenement(evenement);
			} catch (SQLException e) {
				e.printStackTrace();
				Alert alert2 = new Alert(AlertType.ERROR, Data.ALERT_ADDEVENT_KO, ButtonType.OK);
	    		alert2.showAndWait();
	    		return false;
			}
			actionEvent = ActionEvenement.MODIFICATION;
		}
		else if(actionEvent == ActionEvenement.MODIFICATION) {
			try {
				DatabaseAccess.updateEvenement(evenement);
			} catch (SQLException e) {
				e.printStackTrace();
				Alert alert2 = new Alert(AlertType.ERROR, Data.ALERT_MODIFEVENT_KO, ButtonType.OK);
	    		alert2.showAndWait();
	    		return false;
			}
		}
		return true;
	}
	
	public void handleSaveAndQuitEvent(ActionEvent event) {
		handleSaveEvent(null);
		fenetrePrincipale.changerPanel("start", null);
	}
	
	public void handleCancelEvent(ActionEvent event) {
		fenetrePrincipale.changerPanel("start", null);
	}
}
