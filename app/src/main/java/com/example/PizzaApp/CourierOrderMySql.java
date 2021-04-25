package com.example.PizzaApp;

import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CourierOrderMySql extends AsyncTask<String, Void, String> {

    static String pizzaPlace;
    String res = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Connection con = connect();

            String resultPlace = "";
            String resultPhoneNumber = "";
            String resultPizzaList = "";
            String pizzaTotalPrice = "";
            int newId = 1;
            String resultAddress = "";

            Statement st0 = con.createStatement();
            ResultSet resultSet = st0.executeQuery("SELECT phone, pizzaPlace, pizzaList, price, address FROM orders");

            while (resultSet.next()) {

                resultPhoneNumber = resultSet.getString(1);
                resultPlace = resultSet.getString(2);
                resultPizzaList = resultSet.getString(3);
                pizzaTotalPrice = resultSet.getString(4);
                resultAddress = resultSet.getString(5);

                PizzaPlaceForOrders newPlace = new PizzaPlaceForOrders(resultPlace);
                infoForCourier.groupList.add(newPlace);
                OrderForCourier newOrder = new OrderForCourier(resultPhoneNumber, resultPlace, resultPizzaList, pizzaTotalPrice, resultAddress);
                infoForCourier.courierOrders.add(newOrder);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String result) {
    }

    public static Connection connect() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://192.168.8.115/pizzaappdatabase";
        String user = "Pizza";
        String pass = "pica";

        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
