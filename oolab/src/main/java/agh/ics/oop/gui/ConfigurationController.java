package agh.ics.oop.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {
    @FXML
    private TextField filePath;
    @FXML
    private Label infoLabel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText("Start first simulation!");
    }
    @FXML
    public void startSimulation(){
        try{
            String path= filePath.getText();
            //tu trzeba spróbować wczytać plik konfiguracyjny
            infoLabel.setText("Your simulation is opened in a new window. You can start another one.");
            //i odpalić symulacje w nowym wątku
        }
        catch(Exception exception){infoLabel.setText("Wrong config file path. Try again.");}
    }
}
