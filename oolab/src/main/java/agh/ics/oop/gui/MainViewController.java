package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import static java.lang.Math.min;

public class MainViewController {
    private final Double HEIGHT=667.0;
    private final Double WIDTH=667.0;
    @FXML
    private Label numberOfDays;
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

    private Animal followedAnimal;

    private boolean amIPsychopath=false;

    public void initial(SimulationEngine engine) {
        this.engine=engine;
        updateLabels();
        cleanFollowedAnimalLabels();
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
        amIPsychopath=false;
        cleanFollowedAnimalLabels();
    }

    private void updateLabels(){
            numberOfDays.setText(String.valueOf(engine.getHowManyDays()));
            numberOfAnimals.setText(String.valueOf(engine.getMapStats().getAmountOfAnimals()));
            amountOfGrass.setText(String.valueOf(engine.getMapStats().getAmountOfGrass()));
            numberOfEmptyFields.setText(String.valueOf(engine.getMapStats().freeSpots()));
            theMostPopularGenome.setText(engine.getMapStats().theMostCommonGenotype());
            averageEnergyLevel.setText(String.valueOf(engine.getMapStats().averageEnergyAlive()));
            averageDeadAgeLevel.setText(String.valueOf(engine.getMapStats().averageAgeDead()));
    }
    private void updateFollowedAnimalLabels(){
        if (amIPsychopath){
            genome.setText(followedAnimal.genesToString());
            activeGene.setText(followedAnimal.getActiveJeans().toString());
            energy.setText(String.valueOf(followedAnimal.getEnergy()));
            amountOfEatenGrass.setText(String.valueOf(followedAnimal.getGrassEaten()));
            numberOfChildren.setText(String.valueOf(followedAnimal.getChildren()));
            daysAlive.setText(followedAnimal.ageToString());
            dayOfDeath.setText(followedAnimal.diedDateToString());
        }
    }

    private void cleanFollowedAnimalLabels(){
        genome.setText("-");
        activeGene.setText("-");
        energy.setText("-");
        amountOfEatenGrass.setText("-");
        numberOfChildren.setText("-");
        daysAlive.setText("-");
        dayOfDeath.setText("-");
    }

    public void renderMap(){
            map.setGridLinesVisible(false);
            map.getChildren().clear();
            map.getColumnConstraints().clear();
            map.getRowConstraints().clear();
            map.setGridLinesVisible(true);
            for (int x=0;x<engine.getMap().getWidth();x++){
                for (int y=engine.getMap().getHeight()-1;y>=0 ;y--){
                    Vector2d position = new Vector2d(x,y);
                    GridPane newPane=new GridPane();
                    newPane.getColumnConstraints().add(new ColumnConstraints(WIDTH/engine.getMap().getWidth()));
                    newPane.getRowConstraints().add(new RowConstraints(HEIGHT/engine.getMap().getHeight()));
                    newPane.setGridLinesVisible(true);
                    if (engine.getMap().isGrassThere(position)){
                        newPane.setStyle("-fx-background-color: #fa75dd;");
                    }
                    else{
                        newPane.setStyle("-fx-background-color: #facaf0;");
                    }
                    if (amIPsychopath && followedAnimal.isAlive() && followedAnimal.getPosition().equals(position))
                    {
                        Circle animalImage=new Circle(min(WIDTH/engine.getMap().getWidth(),HEIGHT/engine.getMap().getHeight())/2.0, new Color(0,1,0,1));
                        newPane.add(animalImage,0,0);
                        //newPane.setHalignment(animalImage, HPos.CENTER);
                        newPane.setAlignment(Pos.CENTER);
                    }
                    else if (engine.getMap().isAnimalThere(position)){
                        Animal animal=engine.getMap().getAnimalOnSpot(position);
                        Circle animalImage=animal.getImage(min(WIDTH/engine.getMap().getWidth(),HEIGHT/engine.getMap().getHeight()),this);
                        newPane.add(animalImage,0,0);
                        //newPane.setHalignment(animalImage, HPos.CENTER);
                        newPane.setAlignment(Pos.CENTER);
                    }
                    map.add(newPane,x,engine.getMap().getHeight()-1-y);

                }
            }

    }

    public void newDayUpdate(){
        updateLabels();
        renderMap();
        updateFollowedAnimalLabels();
    }

    public void showEndScene() {
        mapBox.getChildren().clear();
        try {
            ImageView image = new ImageView(new Image(new FileInputStream(getEndPicPath())));
            image.setFitWidth(WIDTH);
            image.setFitHeight(HEIGHT);
            mapBox.getChildren().addAll(image);

        } catch (FileNotFoundException e) {
            mapBox.getChildren().addAll(new Label("THE END :("));
        }

    }

    public void follow(Animal animal){
        amIPsychopath=true;
        followedAnimal=animal;
        updateFollowedAnimalLabels();
    }

    public String getEndPicPath(){
        Random random = new Random();
        int number=random.nextInt(10);
        return switch (number){
            case 0 -> "oolab/src/main/resources/theendpics/theend0.jpg";
            case 1 -> "oolab/src/main/resources/theendpics/theend1.jpg";
            case 2 -> "oolab/src/main/resources/theendpics/theend2.jpg";
            case 3 -> "oolab/src/main/resources/theendpics/theend3.jpg";
            case 4 -> "oolab/src/main/resources/theendpics/theend4.jpg";
            case 5 -> "oolab/src/main/resources/theendpics/theend5.jpg";
            case 6 -> "oolab/src/main/resources/theendpics/theend6.jpg";
            case 7 -> "oolab/src/main/resources/theendpics/theend7.jpg";
            case 8 -> "oolab/src/main/resources/theendpics/theend8.jpg";
            case 9 -> "oolab/src/main/resources/theendpics/theend9.jpg";
            default -> "oolab/src/main/resources/theendpics/theend.jpg";
        };

    }

}
