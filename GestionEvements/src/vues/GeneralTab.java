package vues;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import modeles.Data;
import modeles.Evenement;

public class GeneralTab extends Group{
	
	private TextField fieldName;
	private Spinner<Integer> fieldPersonNumber;
	private TextField fieldStartDate;
	private TextField fieldRoom;
	private TextField fieldType;
	private TextArea fieldVaisselle;
	private TextArea fieldDressageCouvert;
	
	public GeneralTab() {
		
		final GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));
		
		Label labelName = new Label("Nom:");
		fieldName = new TextField();
		
		Label labelPersonNumber = new Label("Nombre de personnes:");
		fieldPersonNumber = new Spinner<>(1, Integer.MAX_VALUE, 1);
		fieldPersonNumber.setEditable(true);
		
		fieldPersonNumber.focusedProperty().addListener((observable, oldValue, newValue) -> {
			  if (!newValue) {
				  fieldPersonNumber.increment(0);
			  }
			});
		
		Label labelStartDate = new Label("Horaire de début :");
		fieldStartDate = new TextField();
		
		Label labelRoom = new Label("Salon :");
		fieldRoom = new TextField();
		
		Label labelType = new Label("Type de récéption :");
		fieldType = new TextField();
		
		Label labelVaisselle = new Label(Data.GENERAL_VAISSELLE);
		fieldVaisselle = new TextArea();
		
		Label labelDressageCouvert = new Label(Data.GENERAL_COUVERT);
		fieldDressageCouvert = new TextArea();
		
		grid.add(labelName, 1, 1, 1, 1);
		grid.add(fieldName, 2, 1, 3, 1);
		
		grid.add(labelPersonNumber, 1, 2, 2, 1);
		grid.add(fieldPersonNumber, 4, 2, 1, 1);
		
		grid.add(labelStartDate, 1, 3, 1, 1);
		grid.add(fieldStartDate, 4, 3, 1, 1);
		
		grid.add(labelRoom, 1, 4, 1, 1);
		grid.add(fieldRoom, 4, 4, 1, 1);
		
		grid.add(labelType, 10, 1, 1, 1);
		grid.add(fieldType, 11, 1, 1, 1);
		
		grid.add(labelVaisselle, 10, 2, 2, 1);
		grid.add(fieldVaisselle, 10, 3, 3, 2);
		
		grid.add(labelDressageCouvert, 10, 5, 1, 1);
		grid.add(fieldDressageCouvert, 10, 6, 3, 2);
		this.getChildren().add(grid);
	}
	
	public void resetTab() {
		fieldName.setText("");
		fieldPersonNumber.getValueFactory().setValue(1);
		fieldStartDate.setText("");
		fieldRoom.setText("");
		fieldType.setText("");
		fieldVaisselle.setText("");
		fieldDressageCouvert.setText("");
	}
	
	public void setEvenementToFields(Evenement event) {
		fieldName.setText(event.getNom());
		fieldPersonNumber.getValueFactory().setValue(event.getNbPersonnes());
		fieldStartDate.setText(event.getHoraireDebut());
		fieldRoom.setText(event.getSalon());
		fieldType.setText(event.getTypeReception());
		fieldVaisselle.setText(event.getVaisselle());
		fieldDressageCouvert.setText(event.getDressageCouvert());
	}
	
	public void updateEvenementWithFields(Evenement event) {
		event.setNom(fieldName.getText());
		event.setNbPersonnes(fieldPersonNumber.getValueFactory().getValue());
		event.setHoraireDebut(fieldStartDate.getText());
		event.setSalon(fieldRoom.getText());
		event.setTypeReception(fieldType.getText());
		event.setVaisselle(fieldVaisselle.getText());
		event.setDressageCouvert(fieldDressageCouvert.getText());
	}
}
