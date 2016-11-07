package hr.tvz.java.vjezbe.controller.base;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogHelpers {

	public void greskaPovezivenjeDB() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Greška!!");
		alert.setContentText("GREŠKA PRILIKOM PRISTUPANJA BAZI PODATAKA!");
		alert.showAndWait();
	}

}
