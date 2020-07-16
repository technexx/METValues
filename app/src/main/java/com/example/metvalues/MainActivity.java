package com.example.metvalues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isMetric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button imperial = findViewById(R.id.imperial);
        final Button metric = findViewById(R.id.metric);

        final FragmentManager fm  = getSupportFragmentManager();
        final BasicFragment basicFragment = new BasicFragment();

        final Bundle b = new Bundle();

        imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imperial.setBackgroundColor(getResources().getColor(R.color.off_white));
                metric.setBackgroundColor(getResources().getColor(R.color.White));
                b.putBoolean("isMetric", false);
                basicFragment.setArguments(b);
                fm.beginTransaction()
                        .detach(basicFragment)
                        .attach(basicFragment)
                        .commit();
            }
        });

        metric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imperial.setBackgroundColor(getResources().getColor(R.color.White));
                metric.setBackgroundColor(getResources().getColor(R.color.off_white));
                b.putBoolean("isMetric", true);
                basicFragment.setArguments(b);
                fm.beginTransaction()
                        .detach(basicFragment)
                        .attach(basicFragment)
                        .commit();
            }
        });
        fm.beginTransaction()
                .add(R.id.main_fragment, basicFragment)
                .commit();
    }
}
