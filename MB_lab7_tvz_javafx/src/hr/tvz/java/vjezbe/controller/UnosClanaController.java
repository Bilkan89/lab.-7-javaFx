package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.PodaciIzBazeClan;
import hr.tvz.java.vjezbe.controller.base.UrediBase;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.sucelja.MyDialogs;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UnosClanaController extends UrediBase implements MyDialogs {

	private boolean isEdit;
	private Clan zaPrikaz;

	@FXML
	private TextField imeClanaTextField;
	@FXML
	private TextField prezimeClanaTextField;
	@FXML
	private TextField oibClanaTextField;

	@FXML
	public void unesiClana() {

		if (!(validirajVrijednost(imeClanaTextField)
				& validirajVrijednost(prezimeClanaTextField)
				& validacijaVrijednostiIbrojeva(oibClanaTextField))) {

			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"Molim ispravite unesene podatke jer ste unijeli krive!!! Hvala!");
			return;
		}

		try {
			int idCasopisa = PodaciIzBazeClan.najveciIdBazeClan() + 1;
			Clan zaUnosUbazu = new Clan(idCasopisa, imeClanaTextField.getText(),
					prezimeClanaTextField.getText(),
					Integer.parseInt(oibClanaTextField.getText()));

			if (isEdit) {
				zaUnosUbazu.setIdClana(zaPrikaz.getIdClana());
				PodaciIzBazeClan.promijeniClana(zaUnosUbazu);
				RemoveValue();
				prikazIspravnogUnosa("*INFORMATIVNO*", "ÈLAN "
						+ zaPrikaz.getIme() + " " + zaPrikaz.getPrezime()
						+ " JE USPJEŠNO PROMJENJEN U " + zaUnosUbazu.getIme()
						+ " " + zaUnosUbazu.getPrezime());

			} else {
				PodaciIzBazeClan.spremiClanaUBazu(zaUnosUbazu);
				prikazIspravnogUnosa("INFORMATIVNO",
						"ÈLAN " + zaUnosUbazu.getIme()
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

		imeClanaTextField.clear();
		prezimeClanaTextField.clear();
		oibClanaTextField.clear();
	}

	public void urediParametreClana(Clan zaPrikaz) {

		this.zaPrikaz = zaPrikaz;
		this.isEdit = true;
		imeClanaTextField.setText(zaPrikaz.getIme());
		prezimeClanaTextField.setText(zaPrikaz.getPrezime());
		oibClanaTextField.setText(String.valueOf(zaPrikaz.getOib()));

	}

}
