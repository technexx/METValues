package com.example.metvalues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm  = getSupportFragmentManager();
        BasicFragment metFragment = new BasicFragment();

        fm.beginTransaction()
                .add(R.id.main_fragment, metFragment)
                .commit();
    }
}
