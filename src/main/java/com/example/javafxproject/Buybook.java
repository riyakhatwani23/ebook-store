package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Buybook implements Initializable {

    private ObservableList<BookModel> ls;
    private ArrayList<Integer> ls1;

    @FXML
    private TableColumn<BookModel, String> author;

    @FXML
    private TableColumn<BookModel, Integer> bid;

    @FXML
    private TableColumn<BookModel, String> bname;

    @FXML
    private TextField bookidtxt;

    @FXML
    private TableView<BookModel> booksavailabletv;

    @FXML
    private TableColumn<BookModel, Integer> price;

    private FXMLLoader root;
    private Stage stage;

    public void addToCart(ActionEvent actionEvent) {

        if(bookidtxt.getText().equals("")){
            db.showDialog("","Please enter Book ID", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(ls1.contains(Integer.parseInt(bookidtxt.getText()))){
            if(db.addToCart(Integer.parseInt(bookidtxt.getText()))){
                db.showDialog("", "Book Added to Cart", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                db.showDialog("Error", "Error Occured", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            db.showDialog("","Please enter correct Book ID", JOptionPane.INFORMATION_MESSAGE);
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        author.setCellValueFactory(new PropertyValueFactory<>("author"));
        bid.setCellValueFactory(new PropertyValueFactory<>("id"));
        bname.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ls = FXCollections.observableArrayList();
        ls.addAll(db.getBooks());
        ls1 = new ArrayList<>();
        booksavailabletv.setItems(ls);
        for(BookModel e:ls){
            ls1.add(e.id);
        }

    }

    public void back(ActionEvent actionEvent) {
        try{

        root = new FXMLLoader(getClass().getResource("user.fxml"));
        stage = (Stage) (booksavailabletv.getScene().getWindow());
        stage.setScene(new Scene(root.load()));
        stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}