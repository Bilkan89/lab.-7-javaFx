package hr.tvz.java.vjezbe.controller;

import java.io.IOException;

import hr.tvz.java.vjezbe.glavna.Main;
import hr.tvz.java.vjezbe.sucelja.MyDialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;

public class IzbornikController implements MyDialogs {
	
	public void PretraziKnjige(ActionEvent event) throws IOException {

		try {
			Main.log.info("Korisnik je odabrao pretra�ivanje knjiga.");
			Main.setCenterPane(FXMLLoader.load(
					Main.class.getResource("/FXML/fxml_PretraziKnjige.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void UnosNoveKnjige(ActionEvent event) throws IOException {

		try {
			Main.log.info("Korisnik je odabrao unos nove knjige.");
			Main.setCenterPane(FXMLLoader.load(
					Main.class.getResource("/FXML/fxml_UnosKnjige.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void PretraziC(ActionEvent event) throws IOException {

		try {
			Main.log.info("Korisnik je odabrao pretra�ivanje �asopisa.");
			Main.setCenterPane(FXMLLoader.load(Main.class
					.getResource("/FXML/fxml_PretraziCasopise.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void UnosNovogCasopisa(ActionEvent event) throws IOException {

		try {
			Main.log.info("Korisnik je odabrao unos novog �asopisa.");
			Main.setCenterPane(FXMLLoader.load(
					Main.class.getResource("/FXML/fxml_UnosCasopisa.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void PretraziClana(ActionEvent event) throws IOException {

		try {
			Main.log.info("Korisnik je odabrao pretra�ivanje �lanova.");
			Main.setCenterPane(FXMLLoader.load(
					Main.class.getResource("/FXML/fxml_PretraziClanove.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void UnosNovogClana(ActionEvent event) throws IOException {

		try {
			Main.log.info("Korisnik je odabrao unos novog �lana.");
			Main.setCenterPane(FXMLLoader
					.load(Main.class.getResource("/FXML/fxml_UnosClana.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void StanjePosudbeKnjiga(ActionEvent event) throws IOException {

		try {
			Main.log.info("Korisnik je odabrao pretra�ivanje posudbe knjiga.");
			Main.setCenterPane(FXMLLoader.load(
					Main.class.getResource("/FXML/fxml_PosudbaKnjige.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void StanjePosudbeCasopisa(ActionEvent event) throws IOException {

		try {
			Main.log.info(
					"Korisnik je odabrao pretra�ivanje posudbe �asopisa.");
			Main.setCenterPane(FXMLLoader.load(
					Main.class.getResource("/FXML/fxml_PosudbaCasopisa.fxml")));
		} catch (Exception e) {
			popUP(e);
		}
	}

	public void popUP(Throwable e1) {
		prikazDialogaGreske("GRE�KA!! ERROR!! ",
				"DOGODILA SE GRE�KA: " + e1.getMessage() + "  UZROK: "
						+ e1.getCause() + " PORUKA: " + e1.toString());
	}

}
