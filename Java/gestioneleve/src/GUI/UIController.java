package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class UIController implements Initializable {

@FXML
private JFXButton btnconges;

    
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
 

            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }


       @FXML
    void toEleve(ActionEvent event) throws IOException {
  Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("afficherEleve.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.setTitle("Menu");
        
      stage2.show();
   
    }


    @FXML
    void CONGE(ActionEvent event) throws IOException{
        try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLtest1.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    }
             
    




    

