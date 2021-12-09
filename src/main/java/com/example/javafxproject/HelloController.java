package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    public RadioButton admin;
    public RadioButton user;
    private FXMLLoader root;
    private Stage stage;
    private ToggleGroup tg;
    @FXML
    private Button btnlogin;

    @FXML
    private Button btnsignup;

    @FXML
    private PasswordField txtpass;

    @FXML
    private TextField txtuname;
    PreparedStatement pst;
    ResultSet rs;


    @FXML
    void login(ActionEvent event) {

        RadioButton rb = (RadioButton) tg.getSelectedToggle();
        if(rb.getText().equals("User")){
            String uname= txtuname.getText();
            String pass=txtpass.getText();
            if(uname.equals("") || pass.equals("")){
                JOptionPane.showMessageDialog(null,"Username or password not entered");

            }

            else{
                String ret = db.logIn(uname,pass);
                if (ret.equals("Check UserID and Password")) {
                    showDialog("Wrong Credetials","Please Check your username and password",JOptionPane.WARNING_MESSAGE);
                }
                else if(ret.equals(db.FALSE)){
                    showDialog("Database Error","there's an error while connecting to db",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    showDialog("Login Successful","LogIn Done",JOptionPane.INFORMATION_MESSAGE);
                    try {
                        root = new FXMLLoader(getClass().getResource("user.fxml"));
                        stage = (Stage) (btnsignup.getScene().getWindow());
                        stage.setScene(new Scene(root.load()));
                        stage.show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }

        }
        else {
            String uname= txtuname.getText();
            String pass=txtpass.getText();
            if(uname.equals("Admin") && pass.equals("admin")){
                try {
                    showDialog("Login Successful","LogIn Done",JOptionPane.INFORMATION_MESSAGE);
                    root = new FXMLLoader(getClass().getResource("admin.fxml"));
                    stage = (Stage) (btnsignup.getScene().getWindow());
                    stage.setScene(new Scene(root.load()));
                    stage.show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                db.showDialog("wrong credentials","Wrong id and password combination or user doesn't exist",
                        JOptionPane.WARNING_MESSAGE);
            }
        }



    }

    @FXML
    void signup(ActionEvent event) {
        try {
            root = new FXMLLoader(getClass().getResource("signup.fxml"));
            stage = (Stage) (btnsignup.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tg = new ToggleGroup();
        admin.setToggleGroup(tg);
        user.setToggleGroup(tg);
    }
    public static void showDialog(String title, String message, int type){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                try{
                    JOptionPane.showMessageDialog(null, message, title,type);
                } catch (HeadlessException e) {
                    System.out.println();
                }
            }
        });
    }
}