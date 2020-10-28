package com.example.example2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.example.example2.PizzaList.pizzas;

public class ConnectMySql extends AsyncTask<String, Void, String> {
    String res = "";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String url = "jdbc:mysql://192.168.8.115/pizzaappdatabase";
            String user = "Pizza";
            String pass = "pica";

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            String resultName = "";
            String resultDesc = "";
            String pizzaPr = "";
            int newId = 1;
            String resultLDesc = "";

            Statement st0 = con.createStatement();
            ResultSet resultSet = st0.executeQuery("SELECT DISTINCT pizzaName, shortDesc, longDesc, image, price FROM pizzameniuen");

            while (resultSet.next()) {

                resultName = resultSet.getString(1).toString();

                resultDesc = resultSet.getString(2).toString();

                resultLDesc = resultSet.getString(3);

                Blob output = resultSet.getBlob(4);
                byte[] imgBytes = output.getBytes(1, (int) output.length());
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.length);

                pizzaPr = resultSet.getString(5).toString();

                if (pizzas.size() == 0) {
                    newId = 1;
                }else if(pizzas.size() > 0){
                    int size = pizzas.size();
                    Pizza lastPizza = pizzas.get(size - 1);
                    newId = lastPizza.getId() + 1;
                }

                Pizza newPizza = new Pizza(newId, resultName, resultDesc, resultLDesc, pizzaPr, bitmap);
                pizzas.add(newPizza);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String result) {

    }
}

