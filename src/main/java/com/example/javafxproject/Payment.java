package com.example.javafxproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Payment implements Initializable {

    public TextField cno;
    public TextField expiry;
    public TextField cvv;
    public TextField amount;
    public ComboBox<String> card;
    private FXMLLoader root;
    private Stage stage;

    public void cancel(ActionEvent actionEvent) {
        try {
            root = new FXMLLoader(getClass().getResource("cart.fxml"));
            stage = (Stage) (cno.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void pay(ActionEvent actionEvent) {
        String c = card.getValue();
        if (c == null) {
            db.showDialog("", "Please Select a card type", JOptionPane.WARNING_MESSAGE);
        } else if (cno.getText().length() != 16) {
            db.showDialog("", "Please enter correct card number", JOptionPane.WARNING_MESSAGE);
        } else if (expiry.getText().equals("")) {
            db.showDialog("", "Please enter Expiry of Card", JOptionPane.WARNING_MESSAGE);
        } else if (cvv.getText().length() != 3 || cvv.getText().equals("")) {
            db.showDialog("", "Please enter correct cvv", JOptionPane.WARNING_MESSAGE);
        } else {

            Cart.chkout();
            try {
                root = new FXMLLoader(getClass().getResource("cart.fxml"));
                stage = (Stage) (cno.getScene().getWindow());
                stage.setScene(new Scene(root.load()));
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        amount.setText("Amount: "+Integer.toString(Cart.total));
        List<String> list = new ArrayList<>();
        list.add("VISA");
        list.add("Master Card");
        list.add("RuPay");
        list.add("American Express");
        ObservableList<String> los = FXCollections.observableArrayList(list);
        card.setItems(los);

    }
}
