package com.example.islam.weather;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView lvWeekDays;
    ArrayList<String> weekDays;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String cityTitle = getIntent().getStringExtra("cityTitle");
        final String cityURL = getIntent().getStringExtra("city");
        this.setTitle(cityTitle);
        lvWeekDays = findViewById(R.id.lv_WeekDays);
        weekDays = new ArrayList<>();
        getdata();
        adapter = new ArrayAdapter<String>(this, R.layout.row, R.id.tv_rowtext, weekDays);
        lvWeekDays.setAdapter(adapter);
        lvWeekDays.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int postion, long l) {
                Intent intent = new Intent(MainActivity.this, ShowDetails.class);
                intent.putExtra("cityurl", cityURL);
                intent.putExtra("id", postion);
                intent.putExtra("cityname", cityTitle);
                startActivity(intent);

            }
        });
    }


    public void getdata() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = getIntent().getStringExtra("city");
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
                            String date;
                            String day;
                            String result;
                            for (int arrlistindex = 0; arrlistindex < 10; arrlistindex++) {
                                forecast = jsonArray.getJSONObject(arrlistindex);
                                date = forecast.getString("date");
                                day = forecast.getString("day");
                                result = day + "\n" + date;
                                weekDays.add(result);
                                adapter.notifyDataSetChanged();

                            }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Refresh:

                Intent intent = new Intent(MainActivity.this, ChooseCity.class);
                startActivity(intent);
                this.finish();
                break;
            case R.id.action_chooseCity:
                Intent intent1 = new Intent(MainActivity.this, ChooseCity.class);
                startActivity(intent1);
                this.finish();
                break;

        }
        return true;
    }

}
