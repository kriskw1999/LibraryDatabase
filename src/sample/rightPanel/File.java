package sample.rightPanel;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class File extends VBox {
    private static File file;

    private File(){
        setSpacing(10);
        setMinWidth(400);
        setPadding(new Insets(20,20,10,20));
        setStyle("-fx-background-color: #dfdfdf");

        // label
        Label title = new Label("Database");
        title.setFont(new Font("arial",20));

        // text field
        TextField dbName = new TextField();

        // button
        Button createBtn = new Button("Submit");
        createBtn.setPrefWidth(100);
        createBtn.setPrefHeight(30);

        getChildren().addAll(title, dbName, createBtn);
    }

    public static File getFile(){
        if(file == null)
            file = new File();

        return file;
    }
}
