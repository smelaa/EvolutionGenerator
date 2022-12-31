package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    private Label averageDeadAgeLevel;
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
    private SimulationEngine engine;

    public void initial(SimulationEngine engine) {
        this.engine=engine;
        updateLabels();
        map.setGridLinesVisible(true);
        mapBox.getChildren().addAll(map);
    }
    @FXML
    public void playButtonAction(){
        engine.play();
    }
    @FXML
    public void pauseButtonAction(){
        engine.pause();
    }
    @FXML
    public void stopButtonAction(){
        engine.play();
        engine.stop();
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
            averageDeadAgeLevel.setText(String.valueOf(engine.getMapStats().averageAgeDead()));
    }
    private void updateFollowedAnimalLabels(){
        //updatuje informacje w labelach śledzonego zwierzaka
    }

    public void renderMap(){
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
                        newPane.setStyle("-fx-background-color: #b9fcf4;");
                    }
                    else{
                        newPane.setStyle("-fx-background-color: #ffa8ec;");
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

    }

    public void newDayUpdate(){
        updateLabels();
        renderMap();
        //updateFollowedAnimalLabels
    }

    public void showEndScene() {
        mapBox.getChildren().clear();
        try {
            ImageView image = new ImageView(new Image(new FileInputStream("oolab/src/main/resources/theend.jpg")));
            image.setFitWidth(WIDTH);
            image.setFitHeight(HEIGHT);
            mapBox.getChildren().addAll(image);

        } catch (FileNotFoundException e) {
            mapBox.getChildren().addAll(new Label("THE END :("));
        }

    }

}
