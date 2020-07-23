package com.example.metvalues;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class BasicFragment extends Fragment {

    private String ageVal;
    private String weightVal;
    private String heightVal;
    private int activityVal;

    private int ageConv;
    private int weightConv;
    private int heightConv;

    private boolean isMetric;
    private double impMale ;
    private double impFemale;
    private double metMale;
    private double metFemale;
    private double avg;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.basic_fragment, container, false);

        Bundle args = getArguments();
        if (args != null) {
            isMetric = args.getBoolean("isMetric");
        }

        final Spinner gender_spinner = root.findViewById(R.id.gender_spinner);
        final Spinner age_spinner = root.findViewById(R.id.age_spinner);
        final Spinner weight_spinner = root.findViewById(R.id.weight_spinner);
        final Spinner height_spinner = root.findViewById(R.id.height_spinner);
        final Spinner activity_spinner = root.findViewById(R.id.activity_spinner);
        final TextView bmr = root.findViewById(R.id.bmr);
        final TextView tdee = root.findViewById(R.id.tdee);
        final Button calculate = root.findViewById(R.id.calculate);
        final Button about = root.findViewById(R.id.about);

        final List<String> gender = new ArrayList<>();
        final List<String> age = new ArrayList<>();
        List<String> weight = new ArrayList<>();
        final List<String> height = new ArrayList<>();
        List<String> activity = new ArrayList<>();

        gender.add(getString(R.string.male));
        gender.add(getString(R.string.female));
        activity.add(getString(R.string.act_0));
        activity.add(getString(R.string.act_1));
        activity.add(getString(R.string.act_2));
        activity.add(getString(R.string.act_3));
        activity.add(getString(R.string.act_4));
        activity.add(getString(R.string.act_5));
        for (int i=18; i<101; i++) {
            age.add(i + " " + "years");
        }

        if (isMetric) {
            for (int i=1; i<151; i++){
                weight.add((i) + " "  + "kg");
            }
            for (int i=1; i<201; i++) {
                height.add(i + " " + "cm");
            }
        } else {
            for (int i=1; i<301; i++) {
                weight.add(i + " " + "lb");
            }
            for (int i=1; i<101; i++) {
                height.add(i + " " + "inches");
            }
        }

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, gender);
        ArrayAdapter<String> ageAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, age);
        ArrayAdapter<String> weightAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, weight);
        ArrayAdapter<String> heightAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, height);
        ArrayAdapter<String> activityAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, activity);

        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        heightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        gender_spinner.setAdapter(genderAdapter);
        age_spinner.setAdapter(ageAdapter);
        weight_spinner.setAdapter(weightAdapter);
        height_spinner.setAdapter(heightAdapter);
        activity_spinner.setAdapter(activityAdapter);

        age_spinner.setSelection(17);
        weight_spinner.setSelection(149);
        height_spinner.setSelection(59);

        //Todo: Add Strings for metric/imperial
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ageVal = age_spinner.getSelectedItem().toString();
                weightVal = weight_spinner.getSelectedItem().toString();
                heightVal = height_spinner.getSelectedItem().toString();
                activityVal = activity_spinner.getSelectedItemPosition();

                String[] conv1 = ageVal.split(" ", 2);
                String[] conv2 = weightVal.split(" ", 2);
                String[] conv3 = heightVal.split(" ", 2);

                String conv4 = conv1[0];
                String conv5 = conv2[0];
                String conv6 = conv3[0];

                ageConv = Integer.parseInt(conv4);
                weightConv = Integer.parseInt(conv5);
                heightConv = Integer.parseInt(conv6);
                Log.i("test", conv4);

                double modVal = 0;

                switch (activityVal) {
                    case 0:
                        modVal = 1; break;
                    case 1:
                        modVal = 1.2; break;
                    case 2:
                        modVal = 1.37; break;
                    case 3:
                        modVal = 1.55; break;
                    case 4:
                        modVal = 1.725; break;
                    case 5:
                        modVal = 1.9;
                }

                impMale = ( 66 + (6.2 * weightConv) + (12.7 * heightConv) - (6.76 * ageConv) ) * modVal;
                metMale = ( 66 + (13.7 * weightConv) + (5 * heightConv) - (6.76 * ageConv) ) * modVal;
                impFemale = ( 655.1 + (4.35 * weightConv) + (4.7 * heightConv) - (4.7 * ageConv) ) * modVal;
                metFemale = ( 655.1 + (9.6 * weightConv) + (1.8 * heightConv) - (4.7 * ageConv) ) * modVal;
                double calories = 0;

                if (isMetric) {
                    if (gender_spinner.getSelectedItemPosition() == 0) {
                        calories = metMale;
                    } else {
                        calories = metFemale;
                    }
                } else {
                    if (gender_spinner.getSelectedItemPosition() == 0) {
                        calories = impMale;
                    } else {
                        calories = impFemale;
                    }
                }

                double bmrVal = calories / modVal;
                bmr.setText(getString(R.string.two_part, String.valueOf(Math.round(bmrVal)), getString(R.string.calories)));

                tdee.setText(getString(R.string.two_part, String.valueOf(Math.round(calories)), getString(R.string.calories)));

                double avg = calories / 24;
            }
        });

        final ConstraintLayout basicFragment = root.findViewById(R.id.basic_fragment);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View myView = layoutInflater.inflate(R.layout.about_tdee, null);

        final PopupWindow popupWindow = new PopupWindow(myView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basicFragment.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            popupWindow.setWidth(850);
                            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                            popupWindow.setElevation(5);
                            popupWindow.showAtLocation(basicFragment, Gravity.CENTER, 0, 0);
                        } catch (Exception e) {
                            Log.e("Error", "Popup not inflating");
                        }
                    }
                });
            }
        });

        return root;
    }
}