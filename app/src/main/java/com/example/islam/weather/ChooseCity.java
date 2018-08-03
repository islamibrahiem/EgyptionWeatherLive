package com.example.islam.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ChooseCity extends AppCompatActivity {

    Spinner spCitiesList;
    ArrayAdapter<CharSequence> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);
        spCitiesList = findViewById(R.id.sp_cityList);
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.citiesList, android.R.layout.simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCitiesList.setAdapter(spinnerAdapter);

        spCitiesList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void setURL(String cityTitle) {
                Intent intent = new Intent(ChooseCity.this, MainActivity.class);
                intent.putExtra("city", "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + cityTitle + "%2C%20eg%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
                intent.putExtra("cityTitle", cityTitle);
                startActivity(intent);
            }

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int postion, long id) {
                switch (postion) {
                    case 1:
                        setURL("cairo");
                        break;
                    case 2:
                        setURL("giza");
                        break;
                    case 3:
                        setURL("qalyubia");
                        break;
                    case 4:
                        setURL("Alexandria");
                        break;
                    case 5:
                        setURL("Beheira");
                        break;
                    case 6:
                        setURL("Damietta");
                        break;
                    case 7:
                        setURL("Dakahlia");
                        break;
                    case 8:
                        setURL("Gharbia");
                        break;
                    case 9:
                        setURL("Monufia");
                        break;
                    case 10:
                        setURL("sharqia");
                        break;
                    case 11:
                        setURL("ismailia");
                        break;
                    case 12:
                        setURL("Suez");
                        break;
                    case 13:
                        setURL("Faiyum");
                        break;
                    case 14:
                        setURL("Minya");
                        break;
                    case 15:
                        setURL("Asyut");
                        break;
                    case 16:
                        setURL("Sohag");
                        break;
                    case 17:
                        setURL("Qena");
                        break;
                    case 18:
                        setURL("Luxor");
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.choose_city, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_Refresh:
                Intent intent = new Intent(ChooseCity.this, ChooseCity.class);
                startActivity(intent);
                this.finish();
                break;
        }
        return true;
    }

}
