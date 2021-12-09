package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class User implements Initializable {

    @FXML
    private Button buybook;

    @FXML
    private Button cart;

    @FXML
    private Button logoutbtn;

    @FXML
    private Button yourorders;

    private FXMLLoader root;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buybook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    root = new FXMLLoader(getClass().getResource("buybook.fxml"));
                    stage = (Stage) (buybook.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        cart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    root = new FXMLLoader(getClass().getResource("cart.fxml"));
                    stage = (Stage) (buybook.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        yourorders.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    root = new FXMLLoader(getClass().getResource("yourorder.fxml"));
                    stage = (Stage) (buybook.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        logoutbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    root = new FXMLLoader(getClass().getResource("login.fxml"));
                    stage = (Stage) (buybook.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}