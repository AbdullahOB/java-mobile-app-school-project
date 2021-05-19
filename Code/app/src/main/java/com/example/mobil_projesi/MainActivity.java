package com.example.mobil_projesi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


// api key = b2995496130a9d0fb0722f2c1e056c6d;
// City Id = 298806 Turgutlu
public class MainActivity extends AppCompatActivity {
    TextView tempUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tempUI = (TextView) findViewById(R.id.weatherFromApi);
        findWeather();


    }

    public void findWeather(){

        String url = "https://api.openweathermap.org/data/2.5/weather?id=298806&units=metric&appid=b2995496130a9d0fb0722f2c1e056c6d";

        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET,url , null , response -> {
        try{

            JSONObject obj = response.getJSONObject("main");
            JSONArray array = response.getJSONArray("weather");
            String temp = String.valueOf(obj.getDouble("temp"));
            double temp_int = Double.parseDouble(temp);
            int i = (int) temp_int;
            tempUI.setText(String.valueOf(i));

        }catch(JSONException e){
            e.printStackTrace();
        }
        }, error -> { });

        RequestQueue q = Volley.newRequestQueue(this);
        q.add(jor);
    }

    public void LoginPageBtn(View view){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);

    }
    public void RegisterPageBtn(View view){
        Intent intent = new Intent(this,RegisterPage.class);
        startActivity(intent);
    }


}