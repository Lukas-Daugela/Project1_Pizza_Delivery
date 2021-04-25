package com.example.PizzaApp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectMySql extends AsyncTask<String, Void, String> {

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
            String resultName = "";
            String resultDesc = "";
            String pizzaPrice = "";
            int newId = 1;
            String resultLDesc = "";

            Statement st0 = con.createStatement();
            ResultSet resultSet = st0.executeQuery("SELECT pizzaPlace, pizzaName, shortDesc, longDesc, image, price FROM pizzameniuen");

            while (resultSet.next()) {

                resultPlace = resultSet.getString(1);
                resultName = resultSet.getString(2);
                resultDesc = resultSet.getString(3);
                resultLDesc = resultSet.getString(4);
                Blob pictureInBlob = resultSet.getBlob(5);

                byte[] imgBytes = pictureInBlob.getBytes(1, (int) pictureInBlob.length());
                Bitmap pictureBitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                pizzaPrice = resultSet.getString(6);

                if (PizzaList.pizzas.size() == 0) {
                    newId = 1;
                }else if(PizzaList.pizzas.size() > 0){
                    int size = PizzaList.pizzas.size();
                    Pizza lastPizza = PizzaList.pizzas.get(size - 1);
                    newId = lastPizza.getId() + 1;
                }

                pizzaPlace = resultPlace;
                Pizza newPizza = new Pizza(newId, resultName, resultDesc, resultLDesc, pizzaPrice, pictureBitmap);
                PizzaList.pizzas.add(newPizza);

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

