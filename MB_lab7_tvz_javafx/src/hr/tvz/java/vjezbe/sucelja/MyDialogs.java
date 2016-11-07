package hr.tvz.java.vjezbe.sucelja;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public interface MyDialogs {

	public default void prikazDialogaGreske(String Title, String ContentText) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(Title + "!");
		alert.setContentText(ContentText + "!");
		alert.showAndWait();
	}

	public default void prikazIspravnogUnosa(String Title, String ContentText) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Title);
		alert.setContentText(ContentText);
		alert.showAndWait();
	}

}
