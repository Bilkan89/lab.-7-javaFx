package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.PodaciIzBazeCasopisi;
import hr.tvz.java.vjezbe.controller.base.UrediBase;
import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;
import hr.tvz.java.vjezbe.sucelja.MyDialogs;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UnosCasopisaController extends UrediBase implements MyDialogs {

	private boolean isEditCasopis;
	private Casopis prikazCasopisa;

	@FXML
	private TextField NazivCasopisaTextField;
	@FXML
	private TextField GodinaCasopisaTextField;
	@FXML
	private TextField MjesecCasopisaTextField;
	@FXML
	private TextField BrojStranicaCasopisaTextField;
	@FXML
	private ComboBox<VrstaPublikacije> VrstaCasopisaComboBox;

	@FXML
	public void initialize() {
		VrstaCasopisaComboBox.getItems()
				.addAll(VrstaPublikacije.VrijednostiVrstePub());
	}

	@FXML
	private void unesiCasopis() {

		if (!(validirajVrijednost(NazivCasopisaTextField)
				& validirajVrijednost(VrstaCasopisaComboBox)
				& validacijaVrijednostiIbrojeva(GodinaCasopisaTextField)
				& validacijaVrijednostiIbrojeva(BrojStranicaCasopisaTextField)
				& validacijaVrijednostiIbrojeva(MjesecCasopisaTextField))) {

			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"Molim ispravite unesene podatke jer ste unijeli krive!!! Hvala!");
			return;
		}
		try {
			int brojstr = Integer
					.valueOf(BrojStranicaCasopisaTextField.getText());
			int idCasopisa = PodaciIzBazeCasopisi.najveciIdBazeCasopis() + 1;
			Casopis casopisZaBazu = new Casopis(idCasopisa,
					NazivCasopisaTextField.getText(),
					Integer.parseInt(GodinaCasopisaTextField.getText()),
					brojstr, Integer.valueOf(MjesecCasopisaTextField.getText()),
					VrstaCasopisaComboBox.getValue(), 10.00 / brojstr);
			if (isEditCasopis) {
				casopisZaBazu.setIdCasopisa(prikazCasopisa.getIdCasopisa());
				PodaciIzBazeCasopisi.promijeniCasopis(casopisZaBazu);
				RemoveValue();
				prikazIspravnogUnosa("INFORMATIVNO",
						"ÈASOPIS " + prikazCasopisa.getNazivPublikacije()
								+ " JE USPJEŠNO PROMJENJEN U "
								+ casopisZaBazu.getNazivPublikacije());

			} else {
				PodaciIzBazeCasopisi.spremiCasopisUBazu(casopisZaBazu);
				prikazIspravnogUnosa("INFORMATIVNO",
						"ÈASOPIS " + casopisZaBazu.getNazivPublikacije()
								+ " JE USPJEŠNO UNESEN U BAZU PODATAKA!!");
				RemoveValue();
			}
		} catch (Exception e) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e.getMessage() + "  UZROK: "
							+ e.getCause() + " PORUKA: " + e.toString());
			RemoveValue();
		}
	}

	private void RemoveValue() {
		NazivCasopisaTextField.clear();
		GodinaCasopisaTextField.clear();
		BrojStranicaCasopisaTextField.clear();
		MjesecCasopisaTextField.clear();
		VrstaCasopisaComboBox.valueProperty().set(null);
	}

	public void urediParametreCasopisa(Casopis zaPrikaz) {

		this.isEditCasopis = true;
		this.prikazCasopisa = zaPrikaz;
		NazivCasopisaTextField.setText(zaPrikaz.getNazivPublikacije());
		GodinaCasopisaTextField
				.setText(String.valueOf(zaPrikaz.getGodinaIzdanja()));
		BrojStranicaCasopisaTextField
				.setText(String.valueOf(zaPrikaz.getBrojStranicaPublikacije()));
		MjesecCasopisaTextField
				.setText(String.valueOf(zaPrikaz.getMjesecCasopisa()));
		VrstaCasopisaComboBox.setValue(zaPrikaz.getVrstaPublikacije());
	}

}
