package com.example.metvalues;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

public class CustomFragment extends Fragment {

    private String category;
    private double met;
    private int minutes;
    private double hours;
    private int calories;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.custom_fragment, container, false);

        String metConv = "";
        String hoursConv = "";

        Bundle args = getArguments();
        if (args != null) {
            category = args.getString("category");
            metConv = args.getString("met");
            minutes = args.getInt("minutes");
            hoursConv = args.getString("hours");
            calories = args.getInt("calories");

            met = Double.parseDouble(metConv);
            hours = Double.parseDouble(hoursConv);
        }

        Log.i("test", category + metConv + String.valueOf(minutes) + hoursConv + String.valueOf(calories));


        return root;

    }
}
