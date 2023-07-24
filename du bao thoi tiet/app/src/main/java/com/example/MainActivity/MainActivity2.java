package com.example.nhom14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {
    EditText edtsearch;
    Button btnsearch,btntiep,info;
    TextView txtname,txtcontry,txtnhietdo,txttrangthai,txtdoam,txtmay,txtgio,txtday;
    ImageView imgIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edtsearch=(EditText) findViewById(R.id.edtsearch);
        btnsearch=(Button) findViewById(R.id.btnsearch);
        btntiep=(Button) findViewById(R.id.btntiep);
        txtname =(TextView) findViewById(R.id.txtname);
        txtcontry =(TextView) findViewById(R.id.txtcontry);
        txtnhietdo=(TextView) findViewById(R.id.txtnhietdo);
        txttrangthai=(TextView) findViewById(R.id.txttrangthai);
        txtdoam=(TextView) findViewById(R.id.txtdoam);
        txtmay=(TextView) findViewById(R.id.txtmay);
        txtgio=(TextView) findViewById(R.id.txtgio);
        txtday=(TextView) findViewById(R.id.txtday);
        imgIcon=(ImageView) findViewById(R.id.imgIcon);
        info=(Button) findViewById(R.id.info);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city =edtsearch.getText().toString();
                GetCurrentWeatherData(city);
            }
        });
    }
    public void GetCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity2.this);
        String url ="https://api.openweathermap.org/data/2.5/weather?q="+data+"&appid=53fbf527d52d4d773e828243b90c1f8e&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject=new JSONObject(s);
                            String day= jsonObject.getString("dt");
                            String name= jsonObject.getString("name");
                            txtname.setText("Tên Thành Phố:"+name);
                            long l = Long.valueOf(day);
                            Date date =new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat= new SimpleDateFormat("EEEE dd-MM-yyyy HH-mm-ss ");
                            String Day = simpleDateFormat.format(date);
                            txtday.setText(Day);

                            JSONArray jsonArrayWeather =jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather =jsonArrayWeather.getJSONObject(0);
                            String status =jsonObjectWeather.getString("main");
                            String icon =jsonObjectWeather.getString("icon");
                            Picasso.with(MainActivity2.this).load("https://openweathermap.org/img/wn/"+icon+".png").into(imgIcon);
                            txttrangthai.setText(status);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String nhietdo =jsonObjectMain.getString("temp");
                            String doam =jsonObjectMain.getString("humidity");

                            Double a = Double.valueOf(nhietdo);
                            String Nhietdo = String.valueOf(a.intValue());

                            txtnhietdo.setText(Nhietdo + "°C");
                            txtdoam.setText(doam+"%");

                            JSONObject jsonObjectWind= jsonObject.getJSONObject("wind");
                            String gio = jsonObjectWind.getString("speed");
                            txtgio.setText(gio+"m/s");

                            JSONObject jsonObjectClouds= jsonObject.getJSONObject("clouds");
                            String may =jsonObjectClouds.getString("all");
                            txtmay.setText(may+"%");

                            JSONObject jsonObjectSys =jsonObject.getJSONObject("sys");
                            String country= jsonObjectSys.getString("country");
                            txtcontry.setText("Tên Quốc Gia:"+country);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });
        requestQueue.add(stringRequest);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(i);
            }
        });
    }
}