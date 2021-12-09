package com.example.javafxproject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class db  {
    public static Connection con;
    public static Statement s;
    public static String FALSE="1";
    public static String True="0";
    public static String currentUser="";

    public static String signup(String userName, String pwd, String name, String phoneNo,String email,String address){
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con= DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineBookStore","root","");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement st = con.prepareStatement("select * from users where userName = '"+userName+"';");
            ResultSet rs = st.executeQuery();
            if(!rs.next()){
                PreparedStatement st1 = con.prepareStatement("INSERT INTO `users`(`name`, `phoneNo`, `email`, `address`, `userName`, `password`) " +
                        "VALUES ('" + name + "','" + phoneNo + "','" + email + "','" + address + "','" + userName + "','" + pwd + "')");
                st1.execute();
                return "User Registered Successfully";
            }
            else{
                return "User Already Present";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error Occured";

    }
    public static String logIn(String uname, String pwd){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineBookStore","root","");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        try {
            PreparedStatement st = con.prepareStatement("select * from users where userName = '"+uname+"';");
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                if(rs.getString("password").equals(pwd)){
                    currentUser = uname;
                    return True;
                }
                else{
                    return "Check UserID and Password";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FALSE;

    }

    public static ObservableList<BookModel> getBooks(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/OnlineBookStore","root","");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        ObservableList<BookModel> ls = FXCollections.observableArrayList();

        try {
            PreparedStatement st = con.prepareStatement("select * from book;");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                ls.add(new BookModel(rs.getString("bookName"),rs.getString("author"),
                        rs.getInt("bookId"),rs.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public static boolean addBook(String name, String author, int price){
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO `book`(`bookName`, `author`, `price`) VALUES ('"+name+"','"+author+"',"+price+");");
            st.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void showDialog(String title, String message, int type){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, message, title,type);
            }
        });
    }

    public static ObservableList<viewOrderModel> getAllOrder(){
        ObservableList<viewOrderModel> ls = FXCollections.observableArrayList();
        try {
            PreparedStatement st = con.prepareStatement("select * from users natural join (book natural JOIN orders);");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ls.add(new viewOrderModel(rs.getString("name"), rs.getString("address"),
                        rs.getString("phoneNo"), rs.getString("bookName"), rs.getInt("bookId"),
                        rs.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;

    }

    public static ObservableList<BookModel> getOrders(){
        ObservableList ls = FXCollections.observableArrayList();
        try {
            PreparedStatement st = con.prepareStatement(
                    "SELECT * FROM `book` WHERE bookId in (select bookId from orders where userId = '"+currentUser+"');;");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                ls.add(new BookModel(rs.getString("bookName"), rs.getString("author"),
                        rs.getInt("price")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }
    public static boolean addToCart(int bid){
        try {
            PreparedStatement st = con.prepareStatement("INSERT INTO `cart`(`userId`, `bid`) VALUES ('"+currentUser+"',"+bid+");");
            st.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static ObservableList<BookModel> getCart(){
        ObservableList<BookModel> ls = FXCollections.observableArrayList();
        try{
            PreparedStatement st = con.prepareStatement(
                    "select * from book where bookId in (select bid from cart where userId = '"+currentUser+"');");
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                ls.add(new BookModel(rs.getString("bookName"), rs.getInt("price"), rs.getInt("bookId")));
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }




        return ls;
    }

    public static boolean orderNow(ObservableList<BookModel> ls){
        try {
            try{
                for(BookModel r : ls){

                    PreparedStatement st = con.prepareStatement(
                            "INSERT INTO orders(`userId`, `bookId`) VALUES ('"+currentUser+"',"+r.getId()+");");
                    st.execute();

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PreparedStatement st = con.prepareStatement(
                    "delete from cart where userId = '"+currentUser+"';");
            st.execute();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;



        }
        public static boolean deleteBook(String id){
            try{
                PreparedStatement st = con.prepareStatement("delete from book where bookId = '" + id + "';");
                st.execute();
                return true;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return false;
        }

    }






