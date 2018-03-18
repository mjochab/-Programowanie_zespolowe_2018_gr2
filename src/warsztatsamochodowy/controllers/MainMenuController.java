package warsztatsamochodowy.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class MainMenuController implements Initializable {

    @FXML
    private Pane logout;

    @FXML
    private Pane settings;

    @FXML
    private Pane clients;

    @FXML
    private Pane visits;

    @FXML
    private Pane team;

    @FXML
    private Pane parts;

    @FXML
    private Pane tasks;

    @FXML
    private Pane orders;
    
        String stanowisko = "";
    String username = "";
    boolean lock_tasks,lock_orders,lock_parts,lock_team,lock_clients,lock_visits,lock_settings,lock_logout = false;

  
            public void przygotujMenu(String username, String stanowisko) {
           this.stanowisko = stanowisko;
           this.username = username;
           
 switch (stanowisko) {
     
                 case "Kierownik":
lock_tasks = true;
lock_orders = true;
lock_parts = true;
lock_clients = true;
lock_visits = true;
tasks.setOpacity(0.45);
orders.setOpacity(0.45);
parts.setOpacity(0.45);
clients.setOpacity(0.45);
visits.setOpacity(0.45);
                     break;

            case "Recepcjonistka":
lock_team = true;
lock_parts = true;
lock_orders = true;
team.setOpacity(0.45);
orders.setOpacity(0.45);
parts.setOpacity(0.45);
                     break;
            case "Mechanik":
lock_team = true;
lock_clients = true;
lock_visits = true;
team.setOpacity(0.45);
clients.setOpacity(0.45);
visits.setOpacity(0.45);
    }
            }
    
            
            
             @FXML
    public void logout(MouseEvent event) {
        if(lock_logout == false) {
            
                    try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/warsztatsamochodowy/views/Login.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();

            Stage login_scene = new Stage();
            login_scene.setScene(new Scene(root1));
            login_scene.setTitle("Warsztat samochodowy - Logowanie");
            login_scene.show();
            Stage mainmenu_scene = (Stage) logout.getScene().getWindow();
            mainmenu_scene.close();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }


    }
            
               @FXML
    void clients(MouseEvent event) {
        if(lock_clients == false) {
            
            
        }

    }

 

    @FXML
    void orders(MouseEvent event) {
        if(lock_orders == false) {
            
            
        }
    }

    @FXML
    void parts(MouseEvent event) {
        if(lock_parts == false) {
            
            
        }
    }

    @FXML
    void settings(MouseEvent event) {
        if(lock_settings == false) {
            
            
        }
    }

    @FXML
    void tasks(MouseEvent event) {
        if(lock_tasks == false) {
            
            
        }
    }

    @FXML
    void team(MouseEvent event) {
        if(lock_team == false) {
            
            
        }
    }

    @FXML
    void visits(MouseEvent event) {
        if(lock_visits == false) {
            
            
        }
    }
 
            
            
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }


}
