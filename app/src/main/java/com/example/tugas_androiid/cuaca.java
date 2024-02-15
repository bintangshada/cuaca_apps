package com.example.tugas_androiid;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;



public class cuaca extends AppCompatActivity {

    private EditText etCity;
    private TextView tvCity, tvTemp, tvDesc;
    private Button btnGetWeather;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuaca);

        etCity = findViewById(R.id.et_city);
        tvCity = findViewById(R.id.tv_city);
        tvTemp = findViewById(R.id.tv_temp);
        tvDesc = findViewById(R.id.tv_desc);
        btnGetWeather = findViewById(R.id.btn_get_weather);

        btnGetWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String city = etCity.getText().toString().trim();
                if (city.isEmpty()) {
                    Toast.makeText(cuaca.this, "Masukkan nama kota", Toast.LENGTH_SHORT).show();
                    return;
                }

                getWeather(city);
            }
        });
    }

    private void getWeather(String city) {
        String apiKey = "d5c50f1fcedf46b43d1ebf4b409f0baa";

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String cityName = jsonObject.getString("name");
                    double temperature = jsonObject.getJSONObject("main").getDouble("temp");
                    String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");

                    tvCity.setText(cityName);
                    tvTemp.setText(String.format("%.2f°C", temperature));
                    tvDesc.setText(description);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(cuaca.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
}
