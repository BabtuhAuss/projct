package vues;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modeles.Data;
import modeles.Evenement;
import modeles.Menu;

public class MenuTab extends Group{
	
	TextArea fieldEntree;
	TextArea fieldPlat;
	TextArea fieldDessert;
	TextArea fieldCanape;
	
	public MenuTab() {
		
		final GridPane grid = new GridPane();
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(0, 10, 0, 10));
	
		
		Label labelEntree = new Label(Data.MENU_ENTREE);
		fieldEntree = new TextArea();

		Label labelPlat = new Label(Data.MENU_PLAT);
		fieldPlat = new TextArea();
		
		Label labelDessert = new Label(Data.MENU_DESSERT);
		fieldDessert = new TextArea();
		
		Label labelCanape = new Label(Data.MENU_CANAPE);
		fieldCanape = new TextArea();
		
		grid.add(labelEntree, 1, 1, 1, 1);
		grid.add(fieldEntree, 2, 1, 1, 1);
		
		grid.add(labelPlat, 1, 2, 1, 1);
		grid.add(fieldPlat, 2, 2, 1, 1);
		
		grid.add(labelDessert, 3, 1, 1, 1);
		grid.add(fieldDessert, 4, 1, 1, 1);
		
		grid.add(labelCanape, 3, 2, 1, 1);
		grid.add(fieldCanape, 4, 2, 1, 1);
		
		this.getChildren().add(grid);
	}
	
	public void resetTab() {
		fieldEntree.setText("");
		fieldPlat.setText("");
		fieldDessert.setText("");
		fieldCanape.setText("");
	}
	
	public void setMenuToFields(Menu menu) {
		fieldEntree.setText(menu.getEntrees());
		fieldPlat.setText(menu.getPlats());
		fieldDessert.setText(menu.getDesserts());
		fieldCanape.setText(menu.getCanapes());
	}
	
	public void updateMenuWithFields(Menu menu) {
		menu.setEntrees(fieldEntree.getText());
		menu.setPlats(fieldPlat.getText());
		menu.setDesserts(fieldDessert.getText());
		menu.setCanapes(fieldCanape.getText());
	}
}
