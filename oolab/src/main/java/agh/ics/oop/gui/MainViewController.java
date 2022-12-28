package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.SimulationVar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    @FXML
    private Label numberOfAnimals;
    @FXML
    private Label amountOfGrass;
    @FXML
    private Label numberOfEmptyFields;
    @FXML
    private Label theMostPopularGenome;
    @FXML
    private Label averageEnergyLevel;
    @FXML
    private Label genome;
    @FXML
    private Label activeGene;
    @FXML
    private Label energy;
    @FXML
    private Label amountOfEatenGrass;
    @FXML
    private Label numberOfChildren;
    @FXML
    private Label daysAlive;
    @FXML
    private Label dayOfDeath;
    @FXML
    private GridPane map;
    private Animal followedAnimal;

    public MainViewController(SimulationVar var) { //będzie przyjmował path lub już wczytane rzeczy do statystyk
        //w konstruktorze trzeba ustawić wystartować wątek, engine itd itp
        //wątek powinien być początkowo zastopowany i dopiero po kliknięciu playbutton aktywowany
        //wątek i engine powinno być w tej klasie atrybutem
        //ten controller powinien być observerem engine, a engine po nowym dniu powinno wywoływać newDayUpdate
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //UPDATE LABELS
        //update map o odpowiednią liczbę pól
    }
    @FXML
    public void playButtonAction(){

    }
    @FXML
    public void pauseButtonAction(){

    }
    @FXML
    public void stopFollowingAnimal(){

    }

    private void updateLabels(){
        //updatuje informacje w labelach
    }
    private void updateFollowedAnimalLabels(){
        //updatuje informacje w labelach śledzonego zwierzaka
    }

    public void renderMap(){
        //updatuje mapę o aktualne położenie zwierzakó
    }

    public void newDayUpdate(){
        //renderMap
        //updateLabels
        //updateFollowedAnimalLabels
    }

}
