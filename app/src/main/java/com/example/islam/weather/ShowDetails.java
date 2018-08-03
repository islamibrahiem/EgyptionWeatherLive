package com.example.islam.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ShowDetails extends AppCompatActivity {
    TextView tvCityName, tvDate, tvHighNumer, tvLowNumber, tvWind, tvHumidity, tvSunRiseNumer, tvSunSetNumber;
    String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvCityName = findViewById(R.id.tv_cityName);
        tvDate = findViewById(R.id.tv_date);
        tvHighNumer = findViewById(R.id.tv_highNumber);
        tvLowNumber = findViewById(R.id.tv_lowNumber);
        tvWind = findViewById(R.id.tv_windNumber);
        tvHumidity = findViewById(R.id.tv_humidityNumber);
        tvSunRiseNumer = findViewById(R.id.tv_sunRiseNumber);
        tvSunSetNumber = findViewById(R.id.tv_sunSetNumber);

        cityName = getIntent().getStringExtra("cityname");
        tvCityName.setText(cityName);

        showDetials();

    }


    public void showDetials() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getIntent().getStringExtra("cityurl");
        final int id = getIntent().getIntExtra("id", 100);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject root = new JSONObject(response);
                            JSONObject query = root.getJSONObject("query");
                            JSONObject results = query.getJSONObject("results");
                            final JSONObject channel = results.getJSONObject("channel");
                            JSONObject item = channel.getJSONObject("item");
                            final JSONArray jsonArray = item.getJSONArray("forecast");
                            JSONObject forecast;
                            forecast = jsonArray.getJSONObject(id);
                            String date = forecast.getString("date");
                            tvDate.setText(date);
                            double high = ((forecast.getInt("high")) - 32) * .5556;
                            long highRound = Math.round(high);
                            String highResult = highRound + " °C";
                            tvHighNumer.setText(highResult);
                            double low = ((forecast.getInt("low")) - 32) * .5556;
                            long lowRound = Math.round(low);
                            String lowResult = lowRound + " °C";
                            tvLowNumber.setText(lowResult);
                            JSONObject wind = channel.getJSONObject("wind");
                            String speed = wind.getString("speed");
                            tvWind.setText(speed + " Km/h");
                            JSONObject atmosphere = channel.getJSONObject("atmosphere");
                            String humidity = atmosphere.getString("humidity");
                            tvHumidity.setText(humidity);
                            JSONObject astronomy = channel.getJSONObject("astronomy");
                            String sunRise = astronomy.getString("sunrise");
                            String sunset = astronomy.getString("sunset");
                            tvSunRiseNumer.setText(sunRise);
                            tvSunSetNumber.setText(sunset + "\n");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
