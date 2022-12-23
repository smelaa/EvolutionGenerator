package agh.ics.oop.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.net.URL;

public class App extends Application{

//    @Override
//    public void init() throws Exception{
//        try {
////            String[] args = getParameters().getRaw().toArray(new String[0]);
////            map = new GrassField(10);
////            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
////            engine = new SimulationEngine( map, positions, this);
////            engine.setMoveDelay(300);
//        }
//        catch (IllegalArgumentException exception){
//            exception.printStackTrace();
//        }
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Evolution Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader2 = new FXMLLoader(getClass().getResource("/configuration.fxml"));
        Scene scene2 = new Scene(fxmlLoader2.load());
        stage.setTitle("START");
        stage.setScene(scene2);
        stage.show();
    }

}
