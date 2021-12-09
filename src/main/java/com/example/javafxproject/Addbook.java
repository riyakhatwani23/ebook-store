package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Addbook implements Initializable {

    @FXML
    private TextField addauthor;

    @FXML
    private TextField addbname;

    @FXML
    private TextField bookPrice;

    @FXML
    private Button btnadd;
    private FXMLLoader root;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnadd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (db.addBook(addbname.getText(), addauthor.getText(), Integer.parseInt(bookPrice.getText()))) {
                    db.showDialog("Succes", "Book has been added Successfully", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    db.showDialog("Failed", "There's an error while adding the book", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    public void back(ActionEvent actionEvent) {
        try {
            root = new FXMLLoader(getClass().getResource("admin.fxml"));
            stage = (Stage) (btnadd.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}