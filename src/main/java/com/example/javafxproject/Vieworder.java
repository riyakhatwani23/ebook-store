package com.example.javafxproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Vieworder implements Initializable {

    private FXMLLoader root;
    private Stage stage;

    public TableView<viewOrderModel> vieworder;
    @FXML
    private TableColumn<viewOrderModel, String> address;

    @FXML
    private TableColumn<viewOrderModel, Integer> bookid;

    @FXML
    private TableColumn<viewOrderModel, String> bookname;

    @FXML
    private TableColumn<viewOrderModel, String> name;

    @FXML
    private TableColumn<viewOrderModel, String> pno;

    @FXML
    private TableColumn<viewOrderModel, Integer> price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        bookid.setCellValueFactory(new PropertyValueFactory<>("bid"));
        bookname.setCellValueFactory(new PropertyValueFactory<>("bname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        pno.setCellValueFactory(new PropertyValueFactory<>("phone"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        vieworder.setItems(db.getAllOrder());


    }

    public void back(ActionEvent actionEvent) {
        try {
            root = new FXMLLoader(getClass().getResource("admin.fxml"));
            stage = (Stage) (vieworder.getScene().getWindow());
            stage.setScene(new Scene(root.load()));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}