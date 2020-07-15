package com.example.metvalues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm  = getSupportFragmentManager();
        MetFragment metFragment = new MetFragment();

        fm.beginTransaction()
                .add(R.id.main_fragment, metFragment)
                .commit();
    }
}
