package agh.ics.oop.gui;


import agh.ics.oop.SimulationEngine;
import agh.ics.oop.SimulationVar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MainViewApp extends Application {
    private Thread engineThread;
    private SimulationEngine engine;
    private MainViewController controller;
    public void runApp(SimulationVar var) throws IOException {
        engine=new SimulationEngine(var, this);
        engineThread=new Thread(engine);
        start(new Stage());
        engineThread.start();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        controller=fxmlLoader.getController();
        controller.initial(engine);
        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            ((MainViewController) fxmlLoader.getController()).stopButtonAction();
        });
        primaryStage.setTitle("Evolution simulation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void newDayUpdate(){
        controller.newDayUpdate();
    }
    public void showEndScene() {controller.showEndScene();}


}
