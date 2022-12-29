package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.net.URL;

public class App extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/configuration.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("START");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
