package com.example.example2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register extends AppCompatActivity {

    EditText registerName, registerUsername, registerPasswor, registerPhoneNumber;
    Button signUp;
    TextView toLogin;

    private static final String url = "jdbc:mysql://192.168.8.115/pizzaappdatabase";
    private static final String user = "Pizza";
    private static final String pass = "pica";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        registerName = findViewById(R.id.registerNameEditText);
        registerUsername = findViewById(R.id.registerUsernameEditText);
        registerPasswor = findViewById(R.id.registerPasswordEditText);
        registerPhoneNumber = findViewById(R.id.registerPhoneNumberEditText);

        signUp = findViewById(R.id.signUpBtn);
        toLogin = findViewById(R.id.toLoginTextView);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startLogin = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startLogin);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterMySql register = new RegisterMySql();
                register.execute();
            }
        });
    }

    private class RegisterMySql extends AsyncTask<String, Void, String> {

        String name = registerName.getText().toString();
        String username = registerUsername.getText().toString();
        String password = registerPasswor.getText().toString();
        String phoneNum = registerPhoneNumber.getText().toString();
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
                int registr = 0;
                PreparedStatement ps1 = con.prepareStatement("SELECT COUNT(*) FROM customers WHERE username = (?);");
                ps1.setString(1, username);

                check = ps1.executeQuery();

                while(check.next()) {
                    result += check.getString(1).toString();
                }

                int rezultatas = Integer.parseInt(result.trim());

                if(rezultatas > 0){
                    result = "Username already exists";
                    res = result;
                }else if(name.equals("") || username.equals("") || password.equals("") || phoneNum.equals("")){
                    result = "Please enter all fields";
                    res = result;
                } else if (rezultatas == 0) {
                    PreparedStatement ps = con.prepareStatement("INSERT INTO `customers` (`name`, `username`, `password`, `phoneNumber`) VALUES (?, ?, ?, ?);");

                    ps.setString(1, name);
                    ps.setString(2, username);
                    ps.setString(3, password);
                    ps.setString(4, phoneNum);
                    registr = ps.executeUpdate();
                    result = "Registration successful";
                    res = result;
                }else{
                    result = "Something went wrong";
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
            Toast.makeText(Register.this, result, Toast.LENGTH_LONG).show();

            if(result.equals("Registration successful")) {
                Intent startLogin = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startLogin);
            }
        }
    }
}