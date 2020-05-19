/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.eleve;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import services.EleveService;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class Stat2Controller implements Initializable {

    @FXML
    private BarChart<String,Integer> barchart;
  private ToggleGroup tgGroup;
       @FXML
    private RadioButton btn;
    EleveService ps = new EleveService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          XYChart.Series<String, Integer> series11 = new XYChart.Series<>();
         
        try {
            for (eleve t1 : ps.getEleve()) {
                
                XYChart.Data<String, Integer> data11;
                
                
                
               
                  
                    data11 = new XYChart.Data<String, Integer>(t1.getNom(), t1.getAge());
                    
                    series11.getData().add(data11);
                 
                
                
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Stat2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
          barchart.getData().addAll(series11);
                XYChart.Series<String, Double> series = new XYChart.Series<>();
                 
          tgGroup = new ToggleGroup();
			btn.setToggleGroup(tgGroup);
    }    
    
}
