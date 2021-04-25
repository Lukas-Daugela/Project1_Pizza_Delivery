package com.example.PizzaApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.example2.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import static com.example.PizzaApp.ConnectMySql.connect;

public class OrderPizza extends AppCompatActivity {

    private String name;
    private int phoneNum;
    private String pizzaPlc;
    private String price;
    private String address;

    public static ArrayList<String> pizzaLst = new ArrayList<String>();
    public static ArrayList<Orders> orders = new ArrayList<Orders>();
    public static ArrayList<String> prices = new ArrayList<String>();
    ListView pizzaList;
    TextView check;
    Button getPizza;
    EditText adres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.order_pizza);

        pizzaList = findViewById(R.id.listViewCheck);
        check = findViewById(R.id.textView2);
        getPizza = findViewById(R.id.btnGetPizzas);
        adres = findViewById(R.id.addressEditText);

        OrderListAdapter adapter = new OrderListAdapter(this, R.layout.order_row, orders);
        pizzaList.setAdapter(adapter);

        check.setText("Total price: " + orderPriceSum() + "€");

        getPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getInfo();
                address = adres.getText().toString();
                if(address.equals("")){
                    Toast.makeText(OrderPizza.this, "You have to fill address field", Toast.LENGTH_SHORT).show();
                }else {
                    RegisterOrderInfo registerOrderInfo = new RegisterOrderInfo();
                    registerOrderInfo.execute();
                }
            }
        });

    }

    private void getInfo() {
        this.name = MainActivity.name;
        this.phoneNum = MainActivity.phoneNumber;
        this.pizzaPlc = ConnectMySql.pizzaPlace;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    class OrderListAdapter extends ArrayAdapter<Orders> {

        private Context mContext;
        int mResource;

        public OrderListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Orders> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            String name = getItem(position).getPizzaName();
            String price = getItem(position).getPrice();

            Orders ord = new Orders(name, price);

            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);

            TextView pizzaName = (TextView) convertView.findViewById(R.id.textView1);
            TextView prices = (TextView) convertView.findViewById(R.id.textView12);

            pizzaName.setText(name);
            prices.setText(price + "€");

            return convertView;
        }
    }

    public String orderPriceSum() {

        double[] doubleList = new double[prices.size()];
        double sum = 0;
        for (int i = 0; i < orders.size(); ++i) {
            doubleList[i] = Double.parseDouble((prices.get(i)));
            sum += doubleList[i];
        }
        price = String.valueOf(sum);

        return price;
    }

    private class RegisterOrderInfo extends AsyncTask<String, Void, String> {

        String res = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = connect();
                String result = "";
                ResultSet check;
                int registr = 0;
                PreparedStatement ps = con.prepareStatement("INSERT INTO `orders` (`id`, `name`, `phone`, `pizzaPlace`, `pizzaList`, `price`, `address`) VALUES (NULL, ?, ?, ?, ?, ?, ?);");

                ps.setString(1, name);
                ps.setInt(2, phoneNum);
                ps.setString(3, pizzaPlc);
                ps.setString(4, String.valueOf(pizzaLst));
                ps.setString(5, price);
                ps.setString(6, address);
                registr = ps.executeUpdate();
                result = "Order successful";
                res = result;

            } catch (Exception e) {
                e.printStackTrace();
                res = e.toString();
            }
            return res;
        }
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(OrderPizza.this, result, Toast.LENGTH_LONG).show();

            if(result.equals("Order successful")) {
                Intent start = new Intent(getApplicationContext(), OrderFinish.class);
                startActivity(start);
            }
        }
    }

}