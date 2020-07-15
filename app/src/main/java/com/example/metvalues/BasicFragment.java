package com.example.metvalues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class BasicFragment extends Fragment {

    private List<String> gender;
    private List<Integer> age;
    private List<Integer> weight;
    private List<Integer> height;
    private List<String> activity;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.basic_fragment, container, false);

        Spinner gender_spinner = root.findViewById(R.id.gender_spinner);
        Spinner age_spinner = root.findViewById(R.id.age_spinner);
        Spinner weight_spinner = root.findViewById(R.id.weight_spinner);
        Spinner height_spinner = root.findViewById(R.id.height_spinner);
        Spinner activity_spinner = root.findViewById(R.id.activity_spinner);

        gender = new ArrayList<>();
        age = new ArrayList<>();
        weight = new ArrayList<>();
        height = new ArrayList<>();
        activity = new ArrayList<>();

        gender.add(getString(R.string.male));
        gender.add(getString(R.string.female));
        for (int i=1; i<101; i++) {
            age.add(i);
        }
        for (int i=1; i<301; i++) {
            weight.add(i);
        }
        for (int i=1; i<97; i++) {
            height.add(i);
        }
        activity.add(getString(R.string.act_1));
        activity.add(getString(R.string.act_2));
        activity.add(getString(R.string.act_3));
        activity.add(getString(R.string.act_4));
        activity.add(getString(R.string.act_5));

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

        return root;

    }
}