package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.Database.Database;

import java.sql.SQLException;

public class Main extends Application {

    public static Main main;

    @Override
    public void start(Stage window) throws SQLException {

        window.setTitle("Library");
        window.setMaximized(true);
        SceneManager scene = SceneManager.getSm();
        window.setScene(scene);

        window.show();
    }


    public static void main(String[] args){
        launch(args);
    }

}
