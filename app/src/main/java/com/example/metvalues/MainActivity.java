package com.example.metvalues;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private boolean isMetric;
    private boolean onBasic = true;
    private boolean onMet;
    private boolean onCustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button basic = findViewById(R.id.basic);
        final Button met = findViewById(R.id.met);
        final Button custom = findViewById(R.id.custom);
        final Button imperial = findViewById(R.id.imperial);
        final Button metric = findViewById(R.id.metric);

        final FragmentManager fm  = getSupportFragmentManager();
        final BasicFragment basicFragment = new BasicFragment();
        final MetFragment metFragment = new MetFragment();
        final CustomFragment customFragment = new CustomFragment();

        final Bundle b = new Bundle();

        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.main_fragment, basicFragment)
                        .commit();
                onBasic = true;
                onMet = false;
                onCustom = false;
            }
        });

        met.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.main_fragment, metFragment)
                        .commit();
                onMet = true;
                onBasic = false;
                onCustom = false;
            }
        });

        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction()
                        .replace(R.id.main_fragment, customFragment)
                        .commit();
                onCustom = true;
                onBasic = false;
                onCustom = false;
            }
        });

        imperial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imperial.setBackgroundColor(getResources().getColor(R.color.off_white));
                metric.setBackgroundColor(getResources().getColor(R.color.White));
                b.putBoolean("isMetric", false);

                if (onBasic) {
                    basicFragment.setArguments(b);
                    fm.beginTransaction()
                            .detach(basicFragment)
                            .attach(basicFragment)
                            .commit();
                }
                if (onMet) {
                    metFragment.setArguments(b);
                    fm.beginTransaction()
                            .detach(metFragment)
                            .attach(metFragment)
                            .commit();
                }
                if (onCustom) {
                    customFragment.setArguments(b);
                    fm.beginTransaction()
                            .detach(customFragment)
                            .attach(customFragment)
                            .commit();
                }

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
