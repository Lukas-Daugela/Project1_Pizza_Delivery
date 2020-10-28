package com.example.example2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MainActivity extends AppCompatActivity {

    private static final String url = "jdbc:mysql://192.168.8.115/pizzaappdatabase";
    private static final String user = "Pizza";
    private static final String pass = "pica";

    Button btnLogin;
    EditText loginUsername, loginPassword;
    TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.logInBtn);
        loginUsername = findViewById(R.id.loginUsernameEditText);
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
                LoginMySql login = new LoginMySql();
                login.execute();
            }
        });

    }

    private class LoginMySql extends AsyncTask<String, Void, String> {

        String username = loginUsername.getText().toString();
        String password = loginPassword.getText().toString();
        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, pass);

                String result = "";

                ResultSet check;
                PreparedStatement ps1 = con.prepareStatement("SELECT COUNT(*) FROM customers WHERE username = (?) AND password = (?);");
                ps1.setString(1, username);
                ps1.setString(2, password);

                check = ps1.executeQuery();

                while (check.next()) {
                    result += check.getString(1).toString();
                }

                int rezultatas = Integer.parseInt(result.trim());

                if (rezultatas == 0) {
                    result = "Wrong username or password";
                    res = result;
                } else{
                    result = "Login successful";
                    res = result;
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
                Intent startLogin = new Intent(getApplicationContext(), PizzaPlaces.class);
                startActivity(startLogin);
            }
        }
    }
}