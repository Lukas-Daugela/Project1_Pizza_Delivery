package com.example.PizzaApp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.example2.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MainActivity extends AppCompatActivity {

    private String username;
    static String name;
    static int phoneNumber;

    Button btnLogin;
    EditText loginUsername, loginPassword;
    TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.logInBtn);
        loginUsername = findViewById(R.id.addressEditText);
        loginPassword = findViewById(R.id.loginPasswordEditText);
        registerTextView = (TextView)findViewById(R.id.registerTextView);

        registerTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startRegister = new Intent(getApplicationContext(), Register.class);
                startActivity(startRegister);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = loginUsername.getText().toString();
                LoginMySql login = new LoginMySql();
                login.execute();
            }
        });
    }

    public String getName() {
        return name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    private class LoginMySql extends AsyncTask<String, Void, String> {

        String password = loginPassword.getText().toString();
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = ConnectMySql.connect();

                String customerResult = "";
                String courierResult = "";

                ResultSet check;
                PreparedStatement ps1 = con.prepareStatement("SELECT COUNT(*) FROM customers WHERE username = (?) AND password = (?);");
                ps1.setString(1, username);
                ps1.setString(2, password);

                check = ps1.executeQuery();

                while (check.next()) {
                    customerResult += check.getString(1).toString();
                }

                int rezultatas = Integer.parseInt(customerResult.trim());

                if (rezultatas == 0) {
                    ResultSet checkForCourier;
                    PreparedStatement ps2 = con.prepareStatement("SELECT COUNT(*) FROM courier WHERE userName = (?) AND password = (?);");
                    ps2.setString(1, username);
                    ps2.setString(2, password);

                    checkForCourier = ps2.executeQuery();

                    while (checkForCourier.next()) {
                        courierResult += checkForCourier.getString(1).toString();
                    }
                    int rezultas = Integer.parseInt((courierResult.trim()));

                    if (rezultas == 0) {
                        courierResult = "Wrong username or password";
                        res = courierResult;
                }else if(rezultas == 1){
                        courierResult = "Courier login successful";
                        res = courierResult;
                    }
                } else{
                        customerResult = "Login successful";
                    res = customerResult;
                }

                if(res.equals("Login successful")) {
                    ResultSet getInfo;
                    PreparedStatement ps2 = con.prepareStatement("SELECT name, phoneNumber FROM customers WHERE username = (?)");
                    ps2.setString(1, username);

                    getInfo = ps2.executeQuery();
                    while(getInfo.next()){

                        name = getInfo.getString(1);
                        phoneNumber = getInfo.getInt(2);

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();

            if(result.equals("Login successful")) {
                Intent startLogin = new Intent(getApplicationContext(), CityForOrder.class);
                startActivity(startLogin);
            }

            if(result.equals("Courier login successful")) {
                Intent startCourierLogin = new Intent(getApplicationContext(), infoForCourier.class);
                startActivity(startCourierLogin);
            }
        }
    }
}