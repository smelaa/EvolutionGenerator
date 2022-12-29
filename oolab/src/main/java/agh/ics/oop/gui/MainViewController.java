package agh.ics.oop.gui;

import agh.ics.oop.Animal;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.SimulationVar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController {
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
    private Label averageDeadEnergyLevel;
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
    private HBox mapBox;
    private GridPane map=new GridPane();
    private Thread engineThread;
    private SimulationEngine engine;

    public void initial(SimulationEngine engine, Thread engineThread) {
        this.engine=engine;
        this.engineThread=engineThread;
        updateLabels();
        //add label xy
        Label labelxy = new Label("y/x");
        map.getColumnConstraints().add(new ColumnConstraints());
        map.getRowConstraints().add(new RowConstraints());
        map.add(labelxy, 0, 0);
        //add columns
        for (int i = 1; i <= engine.getMapWidth(); i++){
            Label label = new Label(Integer.toString(i));
            map.getColumnConstraints().add(new ColumnConstraints());
            map.add(label, i, 0);
        }
        //add rows
        for (int i =1 ; i <= engine.getMapHeight(); i++){
            Label label = new Label(Integer.toString(i));
            map.getRowConstraints().add(new RowConstraints());
            map.add(label, 0, i);
        }
        map.setGridLinesVisible(true);
        mapBox.getChildren().addAll(map);
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
        numberOfAnimals.setText(String.valueOf(engine.getMapStats().getAmountOfAnimals()));
        amountOfGrass.setText(String.valueOf(engine.getMapStats().getAmountOfGrass()));
        numberOfEmptyFields.setText(String.valueOf(engine.getMapStats().freeSpots()));
        theMostPopularGenome.setText(engine.getMapStats().theMostCommonGenotype());
        averageEnergyLevel.setText(String.valueOf(engine.getMapStats().averageEnergyAlive()));
        averageDeadEnergyLevel.setText(String.valueOf(engine.getMapStats().averageEnergyDead()));
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
        //jeżeli jest 0 zwierzaków to wywołaj stopSimulation
    }

    public void stopSimulation(){
        engineThread.interrupt();
        //cos tam nam zrob
    }

}
