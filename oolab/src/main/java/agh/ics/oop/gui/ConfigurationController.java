package agh.ics.oop.gui;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.SimulationVar;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfigurationController implements Initializable {
    @FXML
    private TextField filePath;
    @FXML
    private Label infoLabel;
    @FXML
    private Button start;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setText("Start first simulation!");
    }
    public Button getStartButton() {
        return start;
    }
    @FXML
    public void startSimulation(){
        try{
            SimulationVar var=  new OptionsParser().parse(filePath.getText());
            infoLabel.setText("Your simulation is opened in a new window. You can start another one.");
            new MainViewApp().runApp(var);
        }
        catch(FileNotFoundException exception){infoLabel.setText("Wrong config file path. Try again.");}
        catch(IllegalArgumentException exception){infoLabel.setText("Wrong arguments in config file. Try again.");}
        catch(Exception exception){infoLabel.setText("Wszyscy zginiemy"); exception.printStackTrace();}
    }
}
