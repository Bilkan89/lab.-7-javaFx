package hr.tvz.java.vjezbe.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.vjezbe.baza.PodaciIzBazeCasopisi;
import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.sucelja.MyDialogs;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

public class PosudbaCasopisaController implements MyDialogs {

	public PosudbaCasopisaController() {
	}

	@FXML
	private TextField pretraziPosudbeCasopisaTextField;

	@FXML
	private TableView<Posudba<Casopis>> viewCasopisi;

	@FXML
	private TableColumn<Posudba<Casopis>, String> columnNazivCasopisa;

	@FXML
	private TableColumn<Posudba<Casopis>, String> columnImeKorisnika;

	@FXML
	private TableColumn<Posudba<Casopis>, String> columnPrezimeKorisnika;

	@FXML
	private TableColumn<Posudba<Casopis>, String> columnDatumPosudbe;

	@FXML
	public void initialize() {

		columnNazivCasopisa.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Casopis>, String> data) {
						return new ReadOnlyObjectWrapper<String>(data.getValue()
								.getPublikacijaVar().getNazivPublikacije());
					}
				});

		columnPrezimeKorisnika.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Casopis>, String> data) {
						return new ReadOnlyObjectWrapper<String>(
								data.getValue().getClanVar().getPrezime());
					}
				});

		columnImeKorisnika.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Casopis>, String> data) {
						return new ReadOnlyObjectWrapper<String>(
								data.getValue().getClanVar().getIme());
					}
				});

		columnDatumPosudbe.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Casopis>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Casopis>, String> data) {
						DateTimeFormatter format = DateTimeFormatter
								.ofPattern("dd.MM.yyyy");
						return new ReadOnlyObjectWrapper<String>(format
								.format(data.getValue().getDatumPosudbe()));
					}
				});
	}

	public void PretraziPosudbuCasopisa() {

		List<Posudba<Casopis>> dohvaceniPosudeniCasopisi;

		try {
			dohvaceniPosudeniCasopisi = PodaciIzBazeCasopisi
					.dohvacanjePosudbeCasopisa();

			List<Posudba<Casopis>> listaPosudbenaCasopisaFilter = new ArrayList<>();

			if (pretraziPosudbeCasopisaTextField.getText().isEmpty() == false) {
				listaPosudbenaCasopisaFilter = dohvaceniPosudeniCasopisi
						.stream()
						.filter(e -> e.getPublikacijaVar().getNazivPublikacije()
								.contains(pretraziPosudbeCasopisaTextField
										.getText()))
						.collect(Collectors.toList());
			} else {
				listaPosudbenaCasopisaFilter = dohvaceniPosudeniCasopisi;
			}
			ObservableList<Posudba<Casopis>> gotovaLista = FXCollections
					.observableArrayList(listaPosudbenaCasopisaFilter);
			viewCasopisi.setItems(gotovaLista);

		} catch (Exception e1) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e1.getMessage() + "  UZROK: "
							+ e1.getCause() + " PORUKA: " + e1.toString());
		}
	}

	public void obrisiPosudbu() {
		try {
			Posudba<Casopis> casopisZabrisanje = viewCasopisi
					.getSelectionModel().getSelectedItem();
			PodaciIzBazeCasopisi.obrisiPosudbuCasopisa(casopisZabrisanje);
			viewCasopisi.getItems().remove(casopisZabrisanje);
			prikazIspravnogUnosa("INFORMATIVNO",
					"USPJEŠNO SI OBRISAO POSUDBU KNJIGE. "
							+ "Naziv publikacije: "
							+ casopisZabrisanje.getPublikacijaVar()
									.getNazivPublikacije()
							+ " Ime èlana: "
							+ casopisZabrisanje.getClanVar().getIme());
		} catch (Exception e) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e.getMessage() + "  UZROK: "
							+ e.getCause() + " PORUKA: " + e.toString());
		}
	}

}
