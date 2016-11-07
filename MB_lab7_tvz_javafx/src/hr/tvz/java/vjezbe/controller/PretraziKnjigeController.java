package hr.tvz.java.vjezbe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.vjezbe.baza.PodaciIzBazeKnjige;
import hr.tvz.java.vjezbe.entitet.Knjiga;
import hr.tvz.java.vjezbe.enumeracije.Jezik;
import hr.tvz.java.vjezbe.enumeracije.VrstaPublikacije;
import hr.tvz.java.vjezbe.glavna.Main;
import hr.tvz.java.vjezbe.sucelja.MyDialogs;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PretraziKnjigeController implements MyDialogs {

	public PretraziKnjigeController() {
	}

	@FXML
	private TextField textFieldPretra�i;
	@FXML
	private TableView<Knjiga> knjigeTablica;
	@FXML
	private TableColumn<Knjiga, String> nazivKnjigeColumn;
	@FXML
	private TableColumn<Knjiga, VrstaPublikacije> vrstaKnjigeColumn;
	@FXML
	private TableColumn<Knjiga, Integer> godinaKnjigeColumn;
	@FXML
	private TableColumn<Knjiga, Integer> brojStranicaKnjigeColumn;
	@FXML
	private TableColumn<Knjiga, Jezik> jezikKnjigeColumn;
	@FXML
	private TableColumn<Knjiga, String> nazivizdavacaKnjigeColumn;

	@FXML
	public void initialize() {

		nazivKnjigeColumn.setCellValueFactory(
				new PropertyValueFactory<Knjiga, String>("NazivPublikacije"));

		vrstaKnjigeColumn.setCellValueFactory(
				new PropertyValueFactory<Knjiga, VrstaPublikacije>(
						"VrstaPublikacije"));

		godinaKnjigeColumn.setCellValueFactory(
				new PropertyValueFactory<Knjiga, Integer>("GodinaIzdanja"));

		brojStranicaKnjigeColumn
				.setCellValueFactory(new PropertyValueFactory<Knjiga, Integer>(
						"BrojStranicaPublikacije"));

		jezikKnjigeColumn.setCellValueFactory(
				new PropertyValueFactory<Knjiga, Jezik>("JezikKnjige"));

		nazivizdavacaKnjigeColumn.setCellValueFactory(
				new PropertyValueFactory<Knjiga, String>("IzdavacKnjige"));

		knjigeTablica.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() > 1) {
					Knjiga knjiga = (Knjiga) knjigeTablica.getSelectionModel()
							.getSelectedItem();
					try {
						FXMLLoader fxmlLoader = new FXMLLoader();

						URL location = PretraziClanoveController.class
								.getResource(
										"/FXML/FXML_clanPretrazivanje.fxml");
						fxmlLoader.setLocation(location);
						fxmlLoader
								.setBuilderFactory(new JavaFXBuilderFactory());
						Parent root = (Parent) fxmlLoader
								.load(location.openStream());

						PretraziClanoveController controller = (PretraziClanoveController) fxmlLoader
								.getController();
						controller.setPublikacija(knjiga);

						Stage stage = new Stage();
						stage.setTitle("Odabir �lana za posudbu knjige "
								+ knjiga.getNazivPublikacije());
						stage.setScene(new Scene(root, 550, 350));
						stage.show();
						controller.setStage(stage);

					} catch (IOException e) {
						prikazDialogaGreske("GRE�KA!! ERROR!! ",
								"DOGODILA SE GRE�KA: " + e.getMessage()
										+ "  UZROK: " + e.getCause()
										+ " PORUKA: " + e.toString());
					}
				}
			}
		});
	}

	public void prikaziKnjige() {

		List<Knjiga> ListaKnjiga = null;
		try {
			ListaKnjiga = PodaciIzBazeKnjige.dohvatiKnjigeIzBaze();
			List<Knjiga> FiltriraneKnjige = new ArrayList<Knjiga>();
			if (textFieldPretra�i.getText().isEmpty() == false) {
				FiltriraneKnjige = ListaKnjiga.stream()
						.filter(e -> e.getNazivPublikacije()
								.contains(textFieldPretra�i.getText()))
						.collect(Collectors.toList());
			} else {
				FiltriraneKnjige = ListaKnjiga;
			}
			ObservableList<Knjiga> ListaKnjigaZaPrikaz = FXCollections
					.observableArrayList(FiltriraneKnjige);
			knjigeTablica.setItems(ListaKnjigaZaPrikaz);

		} catch (Exception e1) {
			prikazDialogaGreske("GRE�KA!! ERROR!! ",
					"DOGODILA SE GRE�KA: " + e1.getMessage() + "  UZROK: "
							+ e1.getCause() + " PORUKA: " + e1.toString());
		}

	}

	public void obrisi() {
		Knjiga K = knjigeTablica.getSelectionModel().getSelectedItem();
		knjigeTablica.getItems().remove(K);
		try {
			PodaciIzBazeKnjige.obrisiKnjigu(K);
			prikazIspravnogUnosa("INFORMATIVNO", "KNJIGA "
					+ K.getNazivPublikacije() + " JE USPJE�NO OBRISANA!!");

		} catch (Exception e2) {
			prikazDialogaGreske("GRE�KA!! ERROR!! ",
					"DOGODILA SE GRE�KA: " + e2.getMessage() + "  UZROK: "
							+ e2.getCause() + " PORUKA: " + e2.toString());
		}
	}

	public void uredi() {

		try {
			FXMLLoader loadFXML = new FXMLLoader(
					getClass().getResource("/fxml/fxml_UnosKnjige.fxml"));
			BorderPane root = (BorderPane) loadFXML.load();
			UnosKnjigeController cont = loadFXML
					.<UnosKnjigeController>getController();

			cont.urediParametreKnjige(
					knjigeTablica.getSelectionModel().getSelectedItem());
			knjigeTablica.getItems().remove(
					knjigeTablica.getSelectionModel().getSelectedItem());
			Main.setCenterPane(root);

		} catch (IOException e) {
			prikazDialogaGreske("GRE�KA!! ERROR!! ",
					"DOGODILA SE GRE�KA: " + e.getMessage() + "  UZROK: "
							+ e.getCause() + " PORUKA: " + e.toString());
		}
	}

}
