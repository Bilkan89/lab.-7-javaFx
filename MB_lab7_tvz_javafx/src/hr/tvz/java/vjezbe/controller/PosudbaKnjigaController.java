package hr.tvz.java.vjezbe.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.vjezbe.baza.PodaciIzBazeKnjige;
import hr.tvz.java.vjezbe.entitet.Knjiga;
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

public class PosudbaKnjigaController implements MyDialogs {

	public PosudbaKnjigaController() {
	}

	@FXML
	private TextField unosKnjigeTextField;
	@FXML
	private TableView<Posudba<Knjiga>> tableViewKnjige;
	@FXML
	private TableColumn<Posudba<Knjiga>, String> columnNazivKnjige;
	@FXML
	private TableColumn<Posudba<Knjiga>, String> columnImeKorisnika;
	@FXML
	private TableColumn<Posudba<Knjiga>, String> columnPrezimeKorisnika;
	@FXML
	private TableColumn<Posudba<Knjiga>, String> columnDatumPosudbe;

	@FXML
	public void initialize() {

		columnNazivKnjige.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Knjiga>, String> data) {
						return new ReadOnlyObjectWrapper<String>(data.getValue()
								.getPublikacijaVar().getNazivPublikacije());
					}
				});

		columnPrezimeKorisnika.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Knjiga>, String> data) {
						return new ReadOnlyObjectWrapper<String>(
								data.getValue().getClanVar().getPrezime());
					}
				});

		columnImeKorisnika.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Knjiga>, String> data) {
						return new ReadOnlyObjectWrapper<String>(
								data.getValue().getClanVar().getIme());
					}
				});

		columnDatumPosudbe.setCellValueFactory(
				new Callback<CellDataFeatures<Posudba<Knjiga>, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(
							CellDataFeatures<Posudba<Knjiga>, String> data) {
						DateTimeFormatter format = DateTimeFormatter
								.ofPattern("dd.MM.yyyy");
						return new ReadOnlyObjectWrapper<String>(format
								.format(data.getValue().getDatumPosudbe()));
					}
				});
	}

	public void pretraziPosudbeKnjiga() {

		List<Posudba<Knjiga>> dohvacenePosudeneKnjige;

		try {
			dohvacenePosudeneKnjige = PodaciIzBazeKnjige
					.dohvacanjePosudbeKnjiga();

			List<Posudba<Knjiga>> listaPosudbenihKnjigaFilter = new ArrayList<>();

			if (unosKnjigeTextField.getText().isEmpty() == false) {
				listaPosudbenihKnjigaFilter = dohvacenePosudeneKnjige.stream()
						.filter(e -> e.getPublikacijaVar().getNazivPublikacije()
								.contains(unosKnjigeTextField.getText()))
						.collect(Collectors.toList());
			} else {
				listaPosudbenihKnjigaFilter = dohvacenePosudeneKnjige;
			}
			ObservableList<Posudba<Knjiga>> GotovaLista = FXCollections
					.observableArrayList(listaPosudbenihKnjigaFilter);
			tableViewKnjige.setItems(GotovaLista);

		} catch (Exception e1) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e1.getMessage() + "  UZROK: "
							+ e1.getCause() + " PORUKA: " + e1.toString());
		}
	}

	public void obrisiPosudbu() {
		try {
			Posudba<Knjiga> knjigaZabrisanje = tableViewKnjige
					.getSelectionModel().getSelectedItem();
			PodaciIzBazeKnjige.obrisiPosudbuKnjige(knjigaZabrisanje);
			tableViewKnjige.getItems().remove(knjigaZabrisanje);
			prikazIspravnogUnosa("INFORMATIVNO",
					"USPJEŠNO SI OBRISAO POSUDBU KNJIGE. "
							+ "Naziv publikacije: "
							+ knjigaZabrisanje.getPublikacijaVar()
									.getNazivPublikacije()
							+ " Ime èlana: "
							+ knjigaZabrisanje.getClanVar().getIme());
		} catch (Exception e) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e.getMessage() + "  UZROK: "
							+ e.getCause() + " PORUKA: " + e.toString());
		}
	}
}
