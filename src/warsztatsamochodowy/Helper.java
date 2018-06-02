package warsztatsamochodowy;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Pomocnicze metody przdatne np: przy zmianie sceny
 *
 * error - Wyswietlnie okna z komunikatem "Wystąpił błąd"
 * message - Wyswietlnie okna z komunikatem "Komunikat"
 * powrotDoMenu - metoda która wyśweitla okno głowne programu
 * sceneSwitcher - metoda do szybkiej zmiany sceny
 * 
 * @author lukasz
 */
public class Helper {

    public void error(String message) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Wystąpił błąd");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void message(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Komunikat");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void powrotDoMenu() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/warsztatsamochodowy/views/MainMenu.fxml"));
        Stage mainMenu = new Stage();
        Parent root = (Parent) fxmlLoader.load();
        mainMenu.setScene(new Scene(root));
        mainMenu.show();
        mainMenu.setTitle("Warsztat samochodowy - Menu główne");

    }

    public void sceneSwitcher(String SciezkaFXML, String tytul) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SciezkaFXML));
        Stage settings_scene = new Stage();
        Parent root = (Parent) fxmlLoader.load();
        settings_scene.setScene(new Scene(root));
        settings_scene.setTitle(tytul);
        settings_scene.show();
    }
    
}
