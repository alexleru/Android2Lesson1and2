package ru.alexey.weather.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ru.alexey.weather.ActivityAboutWeather;
import ru.alexey.weather.R;

public class FragmentSearchAndPreferences extends Fragment {
    private View view;
    private EditText editText;
    private SwitchCompat[] switchCompat = new SwitchCompat[3];
    public final static String CITY = "CITY";
    public final static String ADDDATA = "ADDDATA";
    public final static String ABOUT = "ABOUT";
    private boolean [] showAddData = new boolean[3];
    private boolean isExitFragmentAboutWeather;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_and_preferences, container, false);
        initView();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isExitFragmentAboutWeather =
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    private Intent getIntentAboutWeather() {
        Intent intent = new Intent(getActivity(), ActivityAboutWeather.class);
        intent.putExtra(CITY, editText.getText().toString());
        intent.putExtra(ABOUT, "ABOUT_WEATHER");
        getDataFromSwitchCompat();
        intent.putExtra(ADDDATA, showAddData);
        return intent;
    }

    private Intent getIntentAboutApp() {
        Intent intent = new Intent(getActivity(), ActivityAboutWeather.class);
        intent.putExtra(ABOUT, "ABOUT_APP");
        return intent;
    }

    private Bundle getBundleAboutWeather() {
        Bundle bundle = new Bundle();
        bundle.putString(CITY, editText.getText().toString());
        getDataFromSwitchCompat();
        bundle.putBooleanArray(ADDDATA, showAddData);
        return bundle;
    }

    private void getDataFromSwitchCompat(){
        for (int i = 0; i < showAddData.length; i++){
            showAddData[i] = switchCompat[i].isChecked();
        }
    }

    private void initView(){
        TextView textView = view.findViewById(R.id.weather);
        textView.setText(getString(R.string.label));
        editText = view.findViewById(R.id.edit_text);
        switchCompat[0] = view.findViewById(R.id.show_wind);
        switchCompat[1] = view.findViewById(R.id.show_humidity);
        switchCompat[2] = view.findViewById(R.id.show_pressure);
        Button buttonSearch = view.findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnSearch();
            }
        });
        Button buttonAboutProgram = view.findViewById(R.id.button_about_program);
        buttonAboutProgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickBtnAboutWeather();
            }
        });
    }

    private void onClickBtnSearch() {
        String cityInEditText = editText.getText().toString();
        if (cityInEditText.equals("") || cityInEditText.equals(null)){
            Toast.makeText(getActivity(),
                    getContext().getResources().getText(R.string.attention_city_name),
                    Toast.LENGTH_LONG).show();
        }else {
            if(isExitFragmentAboutWeather){
                showFragmentAboutWeather();
            }
            else{
                startActivity(getIntentAboutWeather());
            }
        }
    }

    private void onClickBtnAboutWeather() {
        if(isExitFragmentAboutWeather){
            showFragmentAboutApp();
        }
        else{
            startActivity(getIntentAboutApp());
        }
    }

    private void showFragmentAboutWeather(){
        FragmentAboutWeather detail;
            detail = FragmentAboutWeather.create(getBundleAboutWeather());
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_about_weather, detail);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            fragmentTransaction.commit();
    }

    private void showFragmentAboutApp() {
        FragmentAboutApp detail = new FragmentAboutApp();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_about_weather, detail);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.commit();
    }
}
