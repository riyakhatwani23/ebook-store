package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Yourorder implements Initializable {

    @FXML
    private TableColumn<BookModel, String> author;

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<BookModel, String> bname;

    @FXML
    private TableColumn<BookModel, Integer> price;

    @FXML
    private TableView<BookModel> yourordertv;
    private FXMLLoader root;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bname.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        yourordertv.setItems(db.getOrders());

        backbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    root = new FXMLLoader(getClass().getResource("user.fxml"));
                    stage = (Stage) (backbtn.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}