package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import static java.lang.Math.min;

public class MainViewController {
    private final Double HEIGHT=667.0;
    private final Double WIDTH=667.0;
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
        Platform.runLater(()->{
            numberOfAnimals.setText(String.valueOf(engine.getMapStats().getAmountOfAnimals()));
            amountOfGrass.setText(String.valueOf(engine.getMapStats().getAmountOfGrass()));
            numberOfEmptyFields.setText(String.valueOf(engine.getMapStats().freeSpots()));
            theMostPopularGenome.setText(engine.getMapStats().theMostCommonGenotype());
            averageEnergyLevel.setText(String.valueOf(engine.getMapStats().averageEnergyAlive()));
            averageDeadEnergyLevel.setText(String.valueOf(engine.getMapStats().averageEnergyDead()));
        });
    }
    private void updateFollowedAnimalLabels(){
        //updatuje informacje w labelach śledzonego zwierzaka
    }

    public void renderMap(){
        Platform.runLater(()->{
            map.setGridLinesVisible(false);
            map.getChildren().clear();
            map.getColumnConstraints().clear();
            map.getRowConstraints().clear();
            map.setGridLinesVisible(true);
            for (int x=0;x<engine.getMap().getWidth();x++){
                for (int y=0;y< engine.getMap().getHeight();y++){
                    Vector2d position = new Vector2d(x,y);
                    GridPane newPane=new GridPane();
                    newPane.getColumnConstraints().add(new ColumnConstraints(WIDTH/engine.getMap().getWidth()));
                    newPane.getRowConstraints().add(new RowConstraints(HEIGHT/engine.getMap().getHeight()));
                    newPane.setGridLinesVisible(true);
                    if (engine.getMap().isGrassThere(position)){
                        newPane.setStyle("-fx-background-color: #65ff00;");
                    }
                    else{
                        newPane.setStyle("-fx-background-color: #aa67f1;");
                    }
                    if(engine.getMap().isAnimalThere(position)){
                        Animal animal=engine.getMap().getAnimalOnSpot(position);
                        Circle animalImage=animal.getImage(min(WIDTH/engine.getMap().getWidth(),HEIGHT/engine.getMap().getHeight()));
                        newPane.add(animalImage,0,0);
                        newPane.setHalignment(animalImage, HPos.CENTER);
                    }
                    map.add(newPane,x,y);

                }
            }
        });
    }


    public void newDayUpdate(){
        updateLabels();
        renderMap();
        //updateFollowedAnimalLabels
    }

    public void stopSimulation(){
        engineThread.interrupt();
        //cos tam nam zrob
    }

}
