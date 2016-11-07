package hr.tvz.java.vjezbe.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.vjezbe.baza.PodaciIzBazeCasopisi;
import hr.tvz.java.vjezbe.entitet.Casopis;
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

public class PretraziCasopiseController implements MyDialogs {

	public PretraziCasopiseController() {
	}

	@FXML
	private TextField UnosCasopisaTextField;
	@FXML
	private TableView<Casopis> ViewCasopisi;
	@FXML
	private TableColumn<Casopis, String> ColumnNazivCasopisa;
	@FXML
	private TableColumn<Casopis, VrstaPublikacije> ColumnVrstaCasopisa;
	@FXML
	private TableColumn<Casopis, Integer> ColumnGodinaCasopisa;
	@FXML
	private TableColumn<Casopis, Integer> ColumnMjesecCasopisa;
	@FXML
	private TableColumn<Casopis, Integer> ColumnBrojStranica;
	@FXML
	private TableColumn<Casopis, Integer> ColumnMjesecCasopisa2;

	@FXML
	public void initialize() {

		ColumnNazivCasopisa.setCellValueFactory(
				new PropertyValueFactory<Casopis, String>("NazivPublikacije"));
		ColumnVrstaCasopisa.setCellValueFactory(
				new PropertyValueFactory<Casopis, VrstaPublikacije>(
						"VrstaPublikacije"));
		ColumnGodinaCasopisa.setCellValueFactory(
				new PropertyValueFactory<Casopis, Integer>("GodinaIzdanja"));
		ColumnMjesecCasopisa.setCellValueFactory(
				new PropertyValueFactory<Casopis, Integer>("MjesecCasopisa"));
		ColumnBrojStranica
				.setCellValueFactory(new PropertyValueFactory<Casopis, Integer>(
						"BrojStranicaPublikacije"));

		ViewCasopisi.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {

				if (event.getClickCount() > 1) {

					Casopis casopis = (Casopis) ViewCasopisi.getSelectionModel()
							.getSelectedItem();
					try {
						FXMLLoader fxmlLoader = new FXMLLoader();
						URL location = PretraziClanoveController.class
								.getResource("/fxml/fxml_PretraziClanove.fxml");

						fxmlLoader.setLocation(location);
						fxmlLoader
								.setBuilderFactory(new JavaFXBuilderFactory());
						Parent root = (Parent) fxmlLoader
								.load(location.openStream());
						PretraziClanoveController controller = (PretraziClanoveController) fxmlLoader
								.getController();
						controller.setPublikacija(casopis);

						Stage stage = new Stage();
						stage.setTitle("Odabir èlana za posudbu èasopisa: "
								+ casopis.getNazivPublikacije());
						stage.setScene(new Scene(root, 650, 350));
						stage.show();
						controller.setStage(stage);

					} catch (IOException e) {
						prikazDialogaGreske("GREŠKA!! ERROR!! ",
								"DOGODILA SE GREŠKA: " + e.getMessage()
										+ "  UZROK: " + e.getCause()
										+ " PORUKA: " + e.toString());
					}
				}
			}
		});
	}

	public void PretraziCasopise() {

		List<Casopis> DohvaceniCasopisi;
		try {
			DohvaceniCasopisi = PodaciIzBazeCasopisi.dohvatiCasopiseIzBaze();
			List<Casopis> ListaCasopisaFilter = new ArrayList<Casopis>();
			if (UnosCasopisaTextField.getText().isEmpty() == false) {
				ListaCasopisaFilter = DohvaceniCasopisi.stream()
						.filter(e -> e.getNazivPublikacije()
								.contains(UnosCasopisaTextField.getText()))
						.collect(Collectors.toList());
			} else {
				ListaCasopisaFilter = DohvaceniCasopisi;
			}
			ObservableList<Casopis> GotovaLista = FXCollections
					.observableArrayList(ListaCasopisaFilter);
			ViewCasopisi.setItems(GotovaLista);

		} catch (Exception e1) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e1.getMessage() + "  UZROK: "
							+ e1.getCause() + " PORUKA: " + e1.toString());
		}

	}

	public void obrisi() {
		Casopis C = ViewCasopisi.getSelectionModel().getSelectedItem();
		ViewCasopisi.getItems().remove(C);
		try {
			PodaciIzBazeCasopisi.obrisiCasopis(C);
			prikazIspravnogUnosa("INFORMATIVNO", "ÈASOPIS "
					+ C.getNazivPublikacije() + " JE USPJEŠNO OBRISAN!!");
		} catch (Exception e2) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e2.getMessage() + "  UZROK: "
							+ e2.getCause() + " PORUKA: " + e2.toString());
		}

	}

	public void uredi() {
		try {
			FXMLLoader loadFXML = new FXMLLoader(
					getClass().getResource("/fxml/fxml_UnosCasopisa.fxml"));
			BorderPane root = (BorderPane) loadFXML.load();
			UnosCasopisaController cont = loadFXML
					.<UnosCasopisaController>getController();

			cont.urediParametreCasopisa(
					ViewCasopisi.getSelectionModel().getSelectedItem());
			ViewCasopisi.getItems()
					.remove(ViewCasopisi.getSelectionModel().getSelectedItem());

			Main.setCenterPane(root);

		} catch (IOException e) {
			prikazDialogaGreske("GREŠKA!! ERROR!! ",
					"DOGODILA SE GREŠKA: " + e.getMessage() + "  UZROK: "
							+ e.getCause() + " PORUKA: " + e.toString());
		}
	}

}
