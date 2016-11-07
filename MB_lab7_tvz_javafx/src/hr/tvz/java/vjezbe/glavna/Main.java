package hr.tvz.java.vjezbe.glavna;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	// TODO: NAPISATI DOKUMENTACIJU ZA KLASE
	// TODO: IZBACITI GREŠKU KAD SE NE MOŽE SPOJITI NA BAZU PODATAKA
	// TODO: CSS STYLE IMPLEMENTIRATI
	// TODO: NAPISATI DOKUMENTACIJU I GENERIRATI JAVADOC
	// TODO: PREVESTI SVE SA HRV NA ENG (VARIJABLE, METODE, OBJEKTE...)
	// TODO: SNIMITI NA GITHUB
	// TODO: POBRISATI KOMENTARE I NEPOTREBNE DATOTEKE
	// TODO: DOVRŠITI LOG ZAPISE
	// TODO: DODATI SLIKE U APLIKACIJU
	// TODO: KREIRATI METODU ZA BRISANJE POSUDBE KNJIGA I ÈASOPISA
	// TODO: POJEDNOSTAVITI PROGRAM (ŠTO MANJE LINIJA KODA)
	// TODO: KREIRATI VIDEO O APLIKACIJI
	// TODO: VELIÈINA/DIMENZIJE PROZORA APLIKACIJE PRILIKOM ODABIRA ÈLANA
	// TODO: IMPLEMENTIRATI AUTOMATSKO POKRETANJE BAZE PODATAKA..
	// TODO: PROVJERITI STIL PISANJA KODA(FORMAT?)
	// TODO: OGRANIÈIT - JEDNA KNJIGA JEDAN ÈLAN

	// TODO: PROŠAO SAM SVE CONTROLORE

	private Stage primaryStage;
	private static BorderPane root;
	public static final Logger log = LoggerFactory.getLogger(Main.class);

	@Override
	public void start(Stage stage) {
		primaryStage = stage;
		try {
			BorderPane rootPane = FXMLLoader
					.load(Main.class.getResource("/fxml/fxml_Izbornik.fxml"));
			root = rootPane;
			Scene scene = new Scene(root, 650, 450);
			scene.getStylesheets().add(getClass()
					.getResource("/css/application.css").toExternalForm());
			primaryStage.setTitle("Evidencija publikacije app");
			primaryStage.getIcons().add(new Image("/images/book.png"));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		log.info("KORISNIK JE POKRENIO APLIKACIJU!");
		launch(args);
		log.info("KORISNIK JE UGASIO APLIKACIJU!");
	}

	public static void setCenterPane(BorderPane centarPane) {
		root.setCenter(centarPane);
	}

}
