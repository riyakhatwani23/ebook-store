package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class DeleteBook {

    @FXML
    private Button backbtn;

    @FXML
    private TextField bid;

    @FXML
    private Button dltbtn;

    private FXMLLoader root;
    private Stage stage;
    public void back(ActionEvent actionEvent) {
        try {
            root = new FXMLLoader(getClass().getResource("admin.fxml"));
            stage = (Stage) (dltbtn.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(ActionEvent actionEvent) {
        if(bid.getText().equals("")){
            db.showDialog("", "Please Enter Correct Book ID", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(db.deleteBook(bid.getText())){
            db.showDialog("Deleted","Book has been deleted",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            db.showDialog("Error","Some Error has occured",JOptionPane.ERROR_MESSAGE);
        }
    }
}