package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Admin implements Initializable {

    @FXML
    private Button addbook;

    @FXML
    private TableColumn<BookModel, String > author;

    @FXML
    private TableColumn<BookModel, Integer> bookid;

    @FXML
    private TableColumn<BookModel, String > bookname;

    @FXML
    private TableView<BookModel> booktable;

    @FXML
    private TableColumn<BookModel, Integer > price;

    private FXMLLoader root;
    private Stage stage;

    @FXML
    private Button vieworder;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addbook.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    root = new FXMLLoader(getClass().getResource("addbook.fxml"));
                    stage = (Stage) (addbook.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        bookid.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookname.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));


        booktable.setItems(db.getBooks());

        vieworder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try {
                    root = new FXMLLoader(getClass().getResource("vieworder.fxml"));
                    stage = (Stage) (addbook.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void logOut(ActionEvent actionEvent) {
        try {
            root = new FXMLLoader(getClass().getResource("login.fxml"));
            stage = (Stage) (vieworder.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(ActionEvent actionEvent) {
        try {
            root = new FXMLLoader(getClass().getResource("deletebook.fxml"));
            stage = (Stage) (vieworder.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}