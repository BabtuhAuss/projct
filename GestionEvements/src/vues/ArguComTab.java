package vues;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import modeles.ArguComm;
import modeles.Data;
import modeles.Menu;

public class ArguComTab extends Group {
	
	TextArea fieldEntree;
	TextArea fieldPlat;
	TextArea fieldDessert;
	TextField fieldVinB;
	TextField fieldVinR;
	TextField fieldChampagne;
	
	public ArguComTab() {
		
		final GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));
	
		
		Label labelEntree = new Label(Data.ARGUCOM_ENTREE);
		fieldEntree = new TextArea();

		Label labelPlat = new Label(Data.ARGUCOM_PLAT);
		fieldPlat = new TextArea();
		
		Label labelDessert = new Label(Data.ARGUCOM_DESSERT);
		fieldDessert = new TextArea();
		
		Label labelVinB = new Label(Data.ARGUCOM_VIN_B);
		fieldVinB = new TextField();

		Label labelVinR = new Label(Data.ARGUCOM_VIN_R);
		fieldVinR = new TextField();
		
		Label labelChampagne = new Label(Data.ARGUCOM_CHPG);
		fieldChampagne = new TextField();
		
		grid.add(labelEntree, 1, 1, 1, 1);
		grid.add(fieldEntree, 2, 1, 2, 3);
		
		grid.add(labelPlat, 4, 1, 1, 1);
		grid.add(fieldPlat, 5, 1, 2, 3);
		
		grid.add(labelDessert, 1, 4, 1, 1);
		grid.add(fieldDessert, 2, 4, 2, 3);
		
		grid.add(labelVinB, 4, 4, 2, 1);
		grid.add(fieldVinB, 6, 4, 1, 1);
		
		grid.add(labelVinR, 4, 5, 2, 1);
		grid.add(fieldVinR, 6, 5, 2, 1);
		
		grid.add(labelChampagne, 4, 6, 2, 1);
		grid.add(fieldChampagne, 6, 6, 2, 1);
		
		this.getChildren().add(grid);
	}
	
	
	public void resetTab() {
		fieldEntree.setText("");
		fieldPlat.setText("");
		fieldDessert.setText("");
		fieldVinB.setText("");
		fieldVinR.setText("");
		fieldChampagne.setText("");
	}
	
	public void setArguCommToFields(ArguComm arguComm) {
		fieldEntree.setText(arguComm.getEntrees());
		fieldPlat.setText(arguComm.getPlats());
		fieldDessert.setText(arguComm.getDesserts());
		fieldVinB.setText(arguComm.getVinBlanc());
		fieldVinR.setText(arguComm.getVinRouge());
		fieldChampagne.setText(arguComm.getChampagne());
	}

	public void updateArguCommWithFields(ArguComm arguComm) {
		arguComm.setEntrees(fieldEntree.getText());
		arguComm.setPlats(fieldPlat.getText());
		arguComm.setDesserts(fieldDessert.getText());
		arguComm.setVinBlanc(fieldVinB.getText());
		arguComm.setVinRouge(fieldVinR.getText());
		arguComm.setChampagne(fieldChampagne.getText());
	}
}
