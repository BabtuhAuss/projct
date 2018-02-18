package vues;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.cell.PropertyValueFactory;
import modeles.Data;
import modeles.Evenement;
import sql.DatabaseAccess;
import javafx.collections.ObservableList;
import javafx.application.Platform;
import javafx.collections.FXCollections;

public class AccueilPanel extends Group {
	
	/**
	 * Une référence à la fenêtre principale
	 */
	final FenetrePrincipale fenetrePrincipale;
	
	final TableView<Evenement> table = new TableView<Evenement>();

	public AccueilPanel(final FenetrePrincipale fenetrePrincipale) {
		this.fenetrePrincipale = fenetrePrincipale;
		
		Insets insetsBottom = new Insets(5, 0, 5, 5);
		Insets insetsTop = new Insets(20, 0, 10, 0);
		Insets insetsRight = new Insets(0, 5, 0, 5);
		
		Label labelListe = new Label(Data.LABEL_LISTE);
		
		Button ajouterEventButton = new Button(Data.BUTTON_ADDEVENT);
		Button changeEventButton = new Button(Data.BUTTON_CHANGEEVENT);
		Button deleteEventButton = new Button(Data.BUTTON_DELETEEVENT);
		Button refreshListButton = new Button(Data.BUTTON_REFRESH);
		Button aboutButton = new Button(Data.BUTTON_ABOUT);
		
		ajouterEventButton.getStyleClass().add("success");
		changeEventButton.getStyleClass().add("info");
		deleteEventButton.getStyleClass().add("danger");
		
		ajouterEventButton.setOnAction(this::handleAjoutEvent);
		changeEventButton.setOnAction(this::handleChangeEvent);
		deleteEventButton.setOnAction(this::handleSupprimerEvent);
		refreshListButton.setOnAction(this::handleRefreshEvent);
		aboutButton.setOnAction(this::handleAbout);
		
		table.setEditable(false);
		table.setMinHeight(430);
        
        TableColumn<Evenement,String> columnName = new TableColumn<Evenement,String>(Data.COLUMN_NAME);
        TableColumn<Evenement,String> columnPersonNumber = new TableColumn<Evenement,String>(Data.COLUMN_PERSONNUMBER);
        TableColumn<Evenement,String> columnStartDate = new TableColumn<Evenement,String>(Data.COLUMN_STARTDATE);
        TableColumn<Evenement,String> columnRoom = new TableColumn<Evenement,String>(Data.COLUMN_ROOM);
		
        columnName.setCellValueFactory(
			    new PropertyValueFactory<Evenement,String>("nom")
			);
        columnPersonNumber.setCellValueFactory(
			    new PropertyValueFactory<Evenement,String>("nbPersonnes")
			);
        columnStartDate.setCellValueFactory(
			    new PropertyValueFactory<Evenement,String>("horaireDebut")
			);
        columnRoom.setCellValueFactory(
			    new PropertyValueFactory<Evenement,String>("salon")
			);
        
        columnName.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
        columnPersonNumber.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        columnStartDate.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        columnRoom.prefWidthProperty().bind(table.widthProperty().multiply(0.2));

        columnName.setResizable(false);
        columnPersonNumber.setResizable(false);
        columnStartDate.setResizable(false);
        columnRoom.setResizable(false);
        
        table.getColumns().addAll(columnName, columnPersonNumber, columnStartDate, columnRoom);

		final HBox hbox2 = new HBox();
        hbox2.setSpacing(50);
        hbox2.setAlignment(Pos.CENTER);
        hbox2.getChildren().addAll(ajouterEventButton, changeEventButton, deleteEventButton);
        
        BorderPane borderPaneTop = new BorderPane();
        borderPaneTop.setLeft(labelListe);
        borderPaneTop.setRight(aboutButton);
        borderPaneTop.setCenter(refreshListButton);
        borderPaneTop.setMargin(labelListe, new Insets(10, 0, 0, 0));
        
        BorderPane borderPane = new BorderPane();

        borderPane.setTop(borderPaneTop);
        borderPane.setCenter(table);
        borderPane.setBottom(hbox2);
        
        borderPane.setMargin(borderPaneTop, insetsTop);
        borderPane.setMargin(hbox2, insetsTop);
        borderPane.setMargin(borderPaneTop, insetsRight);
        borderPane.setMargin(table, insetsRight);
        
        borderPane.prefHeightProperty().bind(fenetrePrincipale.scene.heightProperty());
        borderPane.prefWidthProperty().bind(fenetrePrincipale.scene.widthProperty());

        this.getChildren().add(borderPane);
        
        table.setRowFactory( tv -> {
            TableRow<Evenement> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Evenement rowData = row.getItem();
                    handleEvent(rowData);
                }
            });
            return row ;
        });
        
        Thread t1 = new Thread(new RefreshEventSafe());
        t1.start();
	}
	
	public void handleEvent(Evenement event) {
		fenetrePrincipale.changerPanel("viewevent", event);
	}
	
	public void handleAjoutEvent(ActionEvent event) {
		fenetrePrincipale.changerPanel("addevent", null);
	}
	
	public void handleChangeEvent(ActionEvent event) {
		Evenement evenement = table.getSelectionModel().getSelectedItem();
		if(evenement != null)
			fenetrePrincipale.changerPanel("editevent", evenement);
		else {
			Alert alert2 = new Alert(AlertType.WARNING, Data.ALERT_CHOOSEEVENT, ButtonType.OK);
    		alert2.showAndWait();
		}
	}
	
	public void handleSupprimerEvent(ActionEvent event) {
		Evenement evenement = table.getSelectionModel().getSelectedItem();
		if(evenement == null) {
			Alert alert2 = new Alert(AlertType.WARNING, Data.ALERT_CHOOSEEVENT, ButtonType.OK);
    		alert2.showAndWait();
    		return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION, Data.ALERT_ASK_DELETE, ButtonType.YES, ButtonType.NO);
		if(alert.showAndWait().get() == ButtonType.NO) {
			return;
		}
		try {
			DatabaseAccess.deleteEvenement(evenement);
			Alert alert2 = new Alert(AlertType.INFORMATION, Data.ALERT_DELETE_OK, ButtonType.OK);
    		alert2.showAndWait();
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alert2 = new Alert(AlertType.ERROR, Data.ALERT_DELETE_KO, ButtonType.OK);
    		alert2.showAndWait();
		}
		refreshData();
	}
	
	public void handleRefreshEvent(ActionEvent event) {
		refreshData();
	}
	
	private void fillEventList() {
		try {
			ObservableList<Evenement> list = FXCollections.observableArrayList(DatabaseAccess.getAllEventsShorted());
			table.setItems(list);
		} catch (SQLException e) {
			Alert alert2 = new Alert(AlertType.ERROR, Data.ALERT_REFRESH_KO, ButtonType.OK);
    		alert2.showAndWait();
		}
	}
	
	public void refreshData() {
		table.getItems().clear();
		fillEventList();
	}

	public class RefreshEventSafe implements Runnable {

		@Override
		public void run() {
			try {
				ObservableList<Evenement> list = FXCollections.observableArrayList(DatabaseAccess.getAllEventsShorted());
				Platform.runLater(() -> {
					table.setItems(list);
				});
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void handleAbout(ActionEvent action) {
		Alert alert = new Alert(AlertType.NONE, Data.ALERT_ABOUT, ButtonType.CLOSE);
		alert.showAndWait();
	}
}
