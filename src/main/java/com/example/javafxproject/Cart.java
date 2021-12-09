package com.example.javafxproject;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Cart implements Initializable {

    public static ObservableList<BookModel> ls;

    private FXMLLoader root;
    private Stage stage;

    @FXML
    private Button backbtn;

    @FXML
    private TableColumn<BookModel, String> bname;

    @FXML
    private TableView<BookModel> carttableview;

    @FXML
    private Button checkoutbtn;

    @FXML
    private TableColumn<BookModel, Integer> price;

    @FXML
    private Label totalprice;
    public static int total;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bname.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        ls = db.getCart();
        carttableview.setItems(ls);
        total = 0;
        for(BookModel e: ls){
            total += e.getPrice();
        }
        totalprice.setText(Integer.toString(total));

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

        checkoutbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(total!=0){

                try {
                    root = new FXMLLoader(getClass().getResource("payment.fxml"));
                    stage = (Stage) (backbtn.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                }
                else{
                    db.showDialog("","Please add any book to cart",JOptionPane.WARNING_MESSAGE);
                }


            }
        });

    }
    public static void chkout(){
        if(db.orderNow(ls)){
            db.showDialog("Order Confirmed", "Your Order has been placed", JOptionPane.INFORMATION_MESSAGE);

        }
        else {
            db.showDialog("Problem Occured", "There was an error while making your order", JOptionPane.WARNING_MESSAGE);
        }

    }

}