package hr.tvz.java.vjezbe.controller;

import hr.tvz.java.vjezbe.baza.PodaciIzBazeKnjige;
import hr.tvz.java.vjezbe.controller.base.UrediBase;
import hr.tvz.java.vjezbe.entitet.Izdavac;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.enumeracije.Jezik;
import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;
import hr.tvz.java.vjezbe.sucelja.MyDialogs;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class UnosKnjigeController extends UrediBase implements MyDialogs {

	public UnosKnjigeController() {
	}

	@FXML
	private TextField NazivKnjigeTextField;

	@FXML
	private TextField GodinaTextField;

	@FXML
	private TextField BrStranicaTextField;

	@FXML
	private TextField NazivIzdavacaTextField;

	@FXML
	private TextField DrzavaIzdavacaTextField;

	@FXML
	private ComboBox<VrstaPublikacije> VrstaKnjigeComboBox;

	@FXML
	private ComboBox<Jezik> JezikComboBox;

	private boolean isEdit;
	private Knjiga zaPrikaz;

	@FXML
	public void initialize() {
		JezikComboBox.getItems().addAll(Jezik.VrijednostiJezika());
		VrstaKnjigeComboBox.getItems()
				.addAll(VrstaPublikacije.VrijednostiVrstePub());
	}

	@FXML
	private void unesiKnjigu() {

		if (!(validirajVrijednost(NazivIzdavacaTextField)
				& validirajVrijednost(DrzavaIzdavacaTextField)
				& validirajVrijednost(VrstaKnjigeComboBox)
				& validirajVrijednost(JezikComboBox)
				& validirajVrijednost(NazivKnjigeTextField)
				& validacijaVrijednostiIbrojeva(GodinaTextField)
				& validacijaVrijednostiIbrojeva(BrStranicaTextField))) {

			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"Molim ispravite unesene podatke jer ste unijeli krive!!! Hvala!");
			return;
		}

		try {
			Jezik jezik = JezikComboBox.getValue();
			int idKnjige = PodaciIzBazeKnjige.najveciIdBazeKnjige() + 1;
			float cijenaStranice = (jezik == Jezik.HRVATSKI) ? 0.45f : 0.75f;
			int idIzdavaca = PodaciIzBazeKnjige.najveciIdBazeIzdavac();
			Knjiga knjigaZaBazu = new Knjiga(idKnjige,
					NazivKnjigeTextField.getText(), jezik,
					new Izdavac(idIzdavaca, NazivIzdavacaTextField.getText(),
							DrzavaIzdavacaTextField.getText()),
					Integer.parseInt(GodinaTextField.getText()),
					VrstaKnjigeComboBox.getValue(),
					Integer.valueOf(BrStranicaTextField.getText()),
					cijenaStranice);

			if (isEdit) {
				knjigaZaBazu.setIdKnjige(zaPrikaz.getIdKnjige());
				knjigaZaBazu.getIzdavacKnjige().setIdIzdavac(
						zaPrikaz.getIzdavacKnjige().getIdIzdavac());
				PodaciIzBazeKnjige.promijeniKnjigu(knjigaZaBazu);
				prikazIspravnogUnosa("INFORMATIVNO",
						"KNJIGA " + zaPrikaz.getNazivPublikacije()
								+ " JE USPJEŠNO PROMJENJENA U "
								+ knjigaZaBazu.getNazivPublikacije());
				RemoveValue();
			} else {
				PodaciIzBazeKnjige.spremiKnjiguUBazu(knjigaZaBazu);
				RemoveValue();
				prikazIspravnogUnosa("INFORMATIVNO",
						"KNJIGA " + knjigaZaBazu.getNazivPublikacije()
								+ " JE USPJEŠNO UNESENA U BAZU PODATAKA!!");
			}
		} catch (Exception e) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e.getMessage() + "  UZROK: "
							+ e.getCause() + " PORUKA: " + e.toString());
			RemoveValue();
		}

	}

	private void RemoveValue() {
		NazivKnjigeTextField.clear();
		NazivIzdavacaTextField.clear();
		DrzavaIzdavacaTextField.clear();
		GodinaTextField.clear();
		VrstaKnjigeComboBox.valueProperty().set(null);
		JezikComboBox.valueProperty().set(null);
		BrStranicaTextField.clear();
	}

	/**
	 * Sprema u polja grafièkog prikaza podatke za promjenu/izmjenu
	 *
	 * @param knjige
	 * @param zaPrikaz
	 */
	public void urediParametreKnjige(Knjiga zaPrikaz) {

		this.isEdit = true;
		this.zaPrikaz = zaPrikaz;
		NazivKnjigeTextField.setText(zaPrikaz.getNazivPublikacije());
		GodinaTextField.setText(String.valueOf(zaPrikaz.getGodinaIzdanja()));
		BrStranicaTextField
				.setText(String.valueOf(zaPrikaz.getBrojStranicaPublikacije()));
		NazivIzdavacaTextField.setText(
				String.valueOf(zaPrikaz.getIzdavacKnjige().getNazivIzdavaca()));
		DrzavaIzdavacaTextField.setText(String
				.valueOf(zaPrikaz.getIzdavacKnjige().getDrzavaIzdavaca()));
		VrstaKnjigeComboBox.setValue(zaPrikaz.getVrstaPublikacije());
		JezikComboBox.setValue(zaPrikaz.getJezikKnjige());
	}

}
