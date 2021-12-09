package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class signup {
    @FXML
    public TextField txtaddress;
    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtname;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtpno;

    @FXML
    private TextField txtusername;

    private FXMLLoader root;
    private Stage stage;

    @FXML
    void btnsignupok(ActionEvent event) {
        if(txtusername.getText().equals("")|| txtpassword.getText().equals("")|| txtname.getText().equals("")||
                txtpno.getText().equals("")|| txtemail.getText().equals("")|| txtaddress.getText().equals("")){
            db.showDialog("Empty Field","Please Enter all the details correctly",JOptionPane.WARNING_MESSAGE);
        }
        else {

        String ret = db.signup(txtusername.getText(), txtpassword.getText(), txtname.getText(),
                txtpno.getText(), txtemail.getText(), txtaddress.getText());
            showDialog("Message",ret,JOptionPane.INFORMATION_MESSAGE);
        }
        }

        public static void showDialog(String title, String message, int type){
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(null, message, title,type);
                }
            });
        }

    public void back(ActionEvent actionEvent) {
        try {
            root = new FXMLLoader(getClass().getResource("login.fxml"));
            stage = (Stage) (txtpno.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




