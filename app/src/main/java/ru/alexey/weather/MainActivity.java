package ru.alexey.weather;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ru.alexey.weather.Fragments.FragmentSearch;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SingletonForPreferences singleton = SingletonForPreferences.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSearch fragmentSearch;
        switch (id){
            case R.id.about_app:
                fragmentSearch =
                        (FragmentSearch) fragmentManager.findFragmentById(R.id.fragment_search);
                fragmentSearch.onClickBtnAboutApp();
                return true;
            case R.id.feedback:
                fragmentSearch =
                        (FragmentSearch) fragmentManager.findFragmentById(R.id.fragment_search);
                fragmentSearch.onClickBtnAboutApp();
                return true;
            default:
                return false;
        }
    }
}
