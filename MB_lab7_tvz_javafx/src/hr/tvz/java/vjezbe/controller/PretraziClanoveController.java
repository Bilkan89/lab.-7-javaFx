package hr.tvz.java.vjezbe.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.vjezbe.apstraktne.Publikacija;
import hr.tvz.java.vjezbe.baza.PodaciIzBazeCasopisi;
import hr.tvz.java.vjezbe.baza.PodaciIzBazeClan;
import hr.tvz.java.vjezbe.baza.PodaciIzBazeKnjige;
import hr.tvz.java.vjezbe.entitet.Casopis;
import hr.tvz.java.vjezbe.entitet.Clan;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.entitet.Posudba;
import hr.tvz.java.vjezbe.glavna.Main;
import hr.tvz.java.vjezbe.sucelja.MyDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PretraziClanoveController implements MyDialogs {

	private Publikacija publikacija;
	private Stage stage;

	public PretraziClanoveController() {
	}

	@FXML
	private TextField UnosClanaTextField;
	@FXML
	private TableView<Clan> ViewClan;
	@FXML
	private TableColumn<Clan, String> ColumnImeClana;
	@FXML
	private TableColumn<Clan, String> ColumnPrezimeClana;
	@FXML
	private TableColumn<Clan, Integer> ColumnOIBClana;

	@FXML
	public void initialize() {

		ColumnImeClana.setCellValueFactory(
				new PropertyValueFactory<Clan, String>("Ime"));
		ColumnPrezimeClana.setCellValueFactory(
				new PropertyValueFactory<Clan, String>("Prezime"));
		ColumnOIBClana.setCellValueFactory(
				new PropertyValueFactory<Clan, Integer>("Oib"));

		ViewClan.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (publikacija == null)
					return;
				if (event.getClickCount() > 1) {
					Clan clan = (Clan) ViewClan.getSelectionModel()
							.getSelectedItem();

					if (publikacija instanceof Knjiga) {
						Posudba<Knjiga> posudbaKnji = new Posudba<>(clan,
								(Knjiga) publikacija, LocalDate.now());
						try {
							PodaciIzBazeKnjige
									.spremanjePosudbeKnjige(posudbaKnji);
						} catch (Exception e) {
							System.err.println("GREŠKA: " + e.getMessage()
									+ " CODE: " + e.toString());
						}
					} else if (publikacija instanceof Casopis) {
						Posudba<Casopis> posudbaCas = new Posudba<>(clan,
								(Casopis) publikacija, LocalDate.now());
						try {
							PodaciIzBazeCasopisi
									.spremanjePosudbeCasopisa(posudbaCas);
						} catch (Exception e) {
							System.err.println("GREŠKA: " + e.getMessage()
									+ " CODE: " + e.toString());
						}
					}
					prikazIspravnogUnosa("INFORMATIVNO",
							"POSUDBA JE USPJEŠNO KREIRANA");
					stage.close();
				}

			}
		});

	}

	public void PretraziClana() {
		List<Clan> DohvaceniClanovi;
		try {
			DohvaceniClanovi = PodaciIzBazeClan.dohvatiClanoveIzBaze();
			List<Clan> ListaClanovaFilter = new ArrayList<Clan>();
			if (UnosClanaTextField.getText().isEmpty() == false) {
				ListaClanovaFilter = DohvaceniClanovi.stream().filter(
						e -> e.getIme().contains(UnosClanaTextField.getText()))
						.collect(Collectors.toList());
			} else {
				ListaClanovaFilter = DohvaceniClanovi;
			}
			ObservableList<Clan> GotovaLista = FXCollections
					.observableArrayList(ListaClanovaFilter);
			ViewClan.setItems(GotovaLista);

		} catch (Exception e1) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e1.getMessage() + "  UZROK: "
							+ e1.getCause() + " PORUKA: " + e1.toString());
		}
	}

	public void obrisi() {
		Clan clan = ViewClan.getSelectionModel().getSelectedItem();
		ViewClan.getItems().remove(clan);
		try {
			PodaciIzBazeClan.obrisiClana(clan);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informativno");
			alert.setContentText("ÈLAN " + clan.getIme() + " "
					+ clan.getPrezime() + " JE USPJEŠNO OBRISAN!!");
			alert.showAndWait();
		} catch (Exception e2) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e2.getMessage() + "  UZROK: "
							+ e2.getCause() + " PORUKA: " + e2.toString());
		}
	}

	public void uredi() {
		try {
			FXMLLoader loadFXML = new FXMLLoader(
					getClass().getResource("/fxml/fxml_UnosClana.fxml"));
			BorderPane root = (BorderPane) loadFXML.load();
			UnosClanaController cont = loadFXML
					.<UnosClanaController>getController();
			cont.urediParametreClana(
					ViewClan.getSelectionModel().getSelectedItem());
			ViewClan.getItems()
					.remove(ViewClan.getSelectionModel().getSelectedItem());
			Main.setCenterPane(root);

		} catch (IOException e) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e.getMessage() + "  UZROK: "
							+ e.getCause() + " PORUKA: " + e.toString());
		}
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Publikacija getPublikacija() {
		return publikacija;
	}

	public void setPublikacija(Publikacija publikacija) {
		this.publikacija = publikacija;
	}
}
