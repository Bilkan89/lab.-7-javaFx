package hr.tvz.java.vjezbe.controller.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

public abstract class UrediBase {

	private void prikaziGresku(Control element, String poruka) {
		element.getStyleClass().add("error");
		element.setTooltip(new Tooltip(poruka));
	}

	private void ukloniGresku(Control element) {
		element.getStyleClass().remove("error");
		element.setTooltip(null);
	}

	protected boolean validirajVrijednost(Control element) {
		ukloniGresku(element);
		boolean pametna = false;
		if (element instanceof TextField) {

			if (((TextField) element).getText() != null
					&& !((TextField) element).getText().equals("")) {
				ukloniGresku(element);
				pametna = true;
			} else {
				prikaziGresku(element,
						"Potrebno je unijeti vrijednost! Nemože se prazno spremiti!!");
				pametna = false;
			}

		} else if (element instanceof ComboBox<?>) {
			if (((ComboBox<?>) element).getValue() != null) {
				ukloniGresku(element);
				pametna = true;
			} else {
				prikaziGresku(element,
						"Potrebno je unijeti vrijednost! Nemože se prazno spremiti!!");
				pametna = false;
			}
		}
		return pametna;
	}

	protected boolean validacijaVrijednostiIbrojeva(Control element2) {
		ukloniGresku(element2);
		if (validirajVrijednost(element2) == true) {

			if (element2 instanceof TextField) {

				String ZaProvjeru = ((TextField) element2).getText();
				String uvjet = "^(\\d+)$";

				Pattern pattern = Pattern.compile(uvjet);
				Matcher matcher = pattern.matcher(ZaProvjeru);
				if (matcher.find()) {
					ukloniGresku(element2);
					return true;
				}
			}
		}
		prikaziGresku(element2,
				"Potrebno je unijeti vrijednost! Nemože se prazno spremiti!!");
		return false;
	}

}
