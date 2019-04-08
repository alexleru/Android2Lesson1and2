package ru.alexey.weather;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ru.alexey.weather.CustomView.CustomView;
import ru.alexey.weather.Fragments.FragmentSearch;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SingletonForPreferences singleton = SingletonForPreferences.getInstance();
    TextView textViewTemp;
    TextView textViewHum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initSensor();
    }

    private void initViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.inflateHeaderView(R.layout.nav_header_main);

        CustomView customView = header.findViewById(R.id.custom_view);
        customView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        textViewTemp = findViewById(R.id.temperature);
        textViewHum = findViewById(R.id.humidity);
    }

    private void initSensor() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensorTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sensorTemp != null){
            sensorManager.registerListener(sensorEventListenerTemp,
                    sensorTemp, SensorManager.SENSOR_DELAY_NORMAL);
        }
        Sensor sensorHum = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (sensorHum != null){
            sensorManager.registerListener(sensorEventListenerHum,
                    sensorHum, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    SensorEventListener sensorEventListenerTemp = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            textViewTemp.setText("Temp: " + event.values[0] + "°C");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    SensorEventListener sensorEventListenerHum = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            textViewHum.setText("Hum: " + event.values[0] + "%");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.show_wind_menu:
                singleton.setAddData(0, !item.isChecked());
                item.setChecked(!item.isChecked());
                return true;
            case R.id.show_humidity_menu:
                singleton.setAddData(1, !item.isChecked());
                item.setChecked(!item.isChecked());
                return true;
            case R.id.show_pressure_menu:
                singleton.setAddData(2, !item.isChecked());
                item.setChecked(!item.isChecked());
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSearch fragmentSearch;
        switch (id){
            case R.id.about_app:
                fragmentSearch =
                        (FragmentSearch) fragmentManager.findFragmentById(R.id.fragment_search);
                fragmentSearch.onClickMenuAboutApp();
                closeNav();
                return true;
            case R.id.feedback:
                fragmentSearch =
                        (FragmentSearch) fragmentManager.findFragmentById(R.id.fragment_search);
                fragmentSearch.onClickMenuFeedback();
                closeNav();
                return true;
            default:
                closeNav();
                return false;
        }
    }

    private void closeNav() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
