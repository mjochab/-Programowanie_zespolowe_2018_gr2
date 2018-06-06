/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warsztatsamochodowy.database.entity;

/**
 *
 * @author Artur Pasciak
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Potwierdzenie {

    static Label label;
    static Button yes, no;
    static boolean answer;

    public static boolean display(String title, String message) {//kala podaje tytuł okna oraz label
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);//tytułokana
        window.setMinWidth(400);//rozmiary 
        window.setMinHeight(100);
        label = new Label();
        label.setText(message);//text w label
        label.getStyleClass().add("tekst_wyjscie");

        yes = new Button("Tak");
        yes.getStyleClass().add("button_yes");
        yes.setMinSize(80, 20);
        no = new Button("Nie");
        no.getStyleClass().add("button_no");

        no.setMinSize(80, 20);
        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer = true;
                window.close();
            }

        });
        no.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer = false;
                window.close();
            }

        });

        HBox layout = new HBox(15);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(label, yes, no);
        layout.setAlignment(Pos.CENTER);// na środku

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();// czeka aż okno zostanie zamkniete 

        return answer;
    }

}
