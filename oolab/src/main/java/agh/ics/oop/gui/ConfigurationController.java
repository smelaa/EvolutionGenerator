package agh.ics.oop.gui;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationVar;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
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
            SimulationVar var=  new OptionsParser().parse(filePath.getText());
            infoLabel.setText("Your simulation is opened in a new window. You can start another one.");
            new MainViewController(var);
        }
        catch(FileNotFoundException exception){infoLabel.setText("Wrong config file path. Try again.");}
    }
}
