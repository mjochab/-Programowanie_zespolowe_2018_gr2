
package warsztatsamochodowy;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import warsztatsamochodowy.database.HibernateHelper;

public class Main extends Application {
    

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("views/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Warsztat samochodowy - Logowanie");
        stage.show();
    }

    @Override
    public void stop(){
       //Closing helper
        HibernateHelper.closeHelper();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
