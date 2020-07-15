package com.example.metvalues;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class BasicFragment extends Fragment {

    private boolean isMetric;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.basic_fragment, container, false);

        Spinner gender_spinner = root.findViewById(R.id.gender_spinner);
        Spinner age_spinner = root.findViewById(R.id.age_spinner);
        Spinner weight_spinner = root.findViewById(R.id.weight_spinner);
        Spinner height_spinner = root.findViewById(R.id.height_spinner);
        Spinner activity_spinner = root.findViewById(R.id.activity_spinner);
        TextView tdee = root.findViewById(R.id.tdee);
        TextView average = root.findViewById(R.id.average);

        List<String> gender = new ArrayList<>();
        List<Integer> age = new ArrayList<>();
        List<Integer> weight = new ArrayList<>();
        List<Integer> height = new ArrayList<>();
        List<String> activity = new ArrayList<>();

        double imperialCalc = 0;
        double metricCalc = 0;
        double avg = 0;

        gender.add(getString(R.string.male));
        gender.add(getString(R.string.female));
        activity.add(getString(R.string.act_1));
        activity.add(getString(R.string.act_2));
        activity.add(getString(R.string.act_3));
        activity.add(getString(R.string.act_4));
        activity.add(getString(R.string.act_5));
        for (int i=1; i<101; i++) {
            age.add(i);
        }

        if (isMetric) {
            for (int i=1; i<151; i++) {
                weight.add(i);
            }
            for (int i=1; i<201; i++) {
                height.add(i);
            }
        } else {
            for (int i=1; i<301; i++) {
                weight.add(i);
            }
            for (int i=1; i<101; i++) {
                height.add(i);
            }
        }

        ArrayAdapter<String> genderAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, gender);
        ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, age);
        ArrayAdapter<Integer> weightAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, weight);
        ArrayAdapter<Integer> heightAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, height);
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

        int ageVal = age_spinner.getSelectedItemPosition() +1;
        int weightVal = weight_spinner.getSelectedItemPosition() +1;
        int heightVal = height_spinner.getSelectedItemPosition() +1;
        int activityVal = activity_spinner.getSelectedItemPosition() +1;

        if (gender_spinner.getSelectedItemPosition() == 0) {
            imperialCalc = 66 + (6.2 * weightVal) + (12.7 * heightVal) - (6.76 * ageVal);
            metricCalc = 66 + (13.7 * weightVal) + (5 * heightVal) - (6.76 * ageVal);
        } else {
            imperialCalc = 655.1 + (4.35 * weightVal) + (4.7 * heightVal) - (4.7 * ageVal);
            metricCalc = 655.1 + (9.6 * weightVal) + (1.8 * heightVal) - (4.7 *ageVal);
        }

        return root;

    }
}