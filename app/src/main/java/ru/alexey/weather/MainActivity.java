package ru.alexey.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    Singleton singleton = Singleton.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
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
}
