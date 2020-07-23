package com.example.metvalues;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetFragment extends Fragment {

    private ArrayAdapter subCategoryAdapter;

    private boolean isMetric;
    private int catNumber;
    private String met;
    private String weightVal;

    private double hours;
    private int minutes;
    private int caloriesVal;
    private TextView calories_burned;

    private ArrayAdapter timeAdapter;

    private String subCat;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        //Todo: Un-reinstantiated globals survive replacement!

        View root = inflater.inflate(R.layout.met_fragment, container, false);

        final Button about = root.findViewById(R.id.about_met);
        List<String> weight_list = new ArrayList<>();
        List<String> category_list = new ArrayList<>();
        List<String> choose_time = new ArrayList<>();
        List<Integer> minutes_list = new ArrayList<>();
        List<Double> hours_list = new ArrayList<>();
        hours = 0;
        minutes = 0;


//        final Button add_button = root.findViewById(R.id.add_button);

        final TextView met_score = root.findViewById(R.id.met_score);
        calories_burned = root.findViewById(R.id.calories_burned);

        final int calories = 0;
        met = "0";

        category_list.add(getString(R.string.bicycling));
        category_list.add(getString(R.string.conditioning));
        category_list.add(getString(R.string.dancing));
        category_list.add(getString(R.string.fishing));
        category_list.add(getString(R.string.home));
        category_list.add(getString(R.string.garden));
        category_list.add(getString(R.string.misc));
        category_list.add(getString(R.string.music));
        category_list.add(getString(R.string.occupational));
        category_list.add(getString(R.string.running));
        category_list.add(getString(R.string.self_care));
        category_list.add(getString(R.string.sexual));
        category_list.add(getString(R.string.sports));
        category_list.add(getString(R.string.walking));
        category_list.add(getString(R.string.water));
        category_list.add(getString(R.string.winter));

        final String[] bicycling = getResources().getStringArray(R.array.bicycling);
        final String[] conditioning = getResources().getStringArray(R.array.conditioning);
        final String[] dancing = getResources().getStringArray(R.array.dancing);
        final String[] fishing = getResources().getStringArray(R.array.fishing);
        final String[] home = getResources().getStringArray(R.array.home);
        final String[] garden  = getResources().getStringArray(R.array.garden);
        final String[] misc = getResources().getStringArray(R.array.misc);
        final String[] music = getResources().getStringArray(R.array.music);
        final String[] occupational = getResources().getStringArray(R.array.occupation);
        final String[] running = getResources().getStringArray(R.array.running);
        final String[] self_care = getResources().getStringArray(R.array.self_care);
        final String[] sexual = getResources().getStringArray(R.array.sexual_activity);
        final String[] sports = getResources().getStringArray(R.array.sports);
        final String[] walking = getResources().getStringArray(R.array.walking);
        final String[] water = getResources().getStringArray(R.array.walking);
        final String[] winter = getResources().getStringArray(R.array.winter);

        final String[] bicycling_values = getResources().getStringArray(R.array.bicycle_values);
        final String[] conditioning_values = getResources().getStringArray(R.array.conditioning_values);
        final String[] dancing_values = getResources().getStringArray(R.array.dancing_values);
        final String[] fishing_values = getResources().getStringArray(R.array.fishing_values);
        final String[] home_values = getResources().getStringArray(R.array.home_values);
        final String[] garden_values = getResources().getStringArray(R.array.garden_values);
        final String[] misc_values = getResources().getStringArray(R.array.misc_values);
        final String[] music_values = getResources().getStringArray(R.array.music_values);
        final String[] occupational_values = getResources().getStringArray(R.array.occupation_values);
        final String[] running_values = getResources().getStringArray(R.array.running_values);
        final String[] self_care_values = getResources().getStringArray(R.array.self_care_values);
        final String[] sexual_values = getResources().getStringArray(R.array.sex_values);
        final String[] sports_values = getResources().getStringArray(R.array.sports_values);
        final String[] walking_values = getResources().getStringArray(R.array.walking_values);
        final String[] water_values = getResources().getStringArray(R.array.walking_values);
        final String[] winter_values = getResources().getStringArray(R.array.winter_values);

        final Spinner weight_spinner = root.findViewById(R.id.met_spinnerOne);
        final Spinner category_spinner = root.findViewById(R.id.met_spinnerTwo);
        final Spinner sub_category_spinner = root.findViewById(R.id.met_spinnerThree);
        final Spinner hours_spinner = root.findViewById(R.id.hours_spent);
        final Spinner minutes_spinner = root.findViewById(R.id.hours_spent);

        Bundle args = getArguments();
        if (args != null) {
            isMetric = args.getBoolean("isMetric");
        }

        if (isMetric) {
            for (int i=1; i<151; i++){
                weight_list.add((i) + " "  + "kg");
            }
        } else {
            for (int i=1; i<301; i++) {
                weight_list.add(i + " " + "lb");
            }
        }

        for (int i=0; i<48; i++) {
            hours = hours + 0.5;
            hours_list.add(hours);
        }
        for (int i=0; i<240; i++) {
            minutes = minutes +1;
            minutes_list.add(minutes);
        }

        choose_time.add(getString(R.string.hours_spent));

        ArrayAdapter<String> weightAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, weight_list);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, category_list);
        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, bicycling);
        ArrayAdapter<String> chooseTimeAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, choose_time);
        timeAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, minutes_list);

        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        weight_spinner.setAdapter(weightAdapter);
        category_spinner.setAdapter(categoryAdapter);
        sub_category_spinner.setAdapter(subCategoryAdapter);
        minutes_spinner.setAdapter(timeAdapter);

        weight_spinner.setSelection(149);
        category_spinner.setSelection(0);
        sub_category_spinner.setSelection(0);

        weight_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                double hours_selected = Double.parseDouble(hours_spinner.getSelectedItem().toString());
                double hoursVal = hours_selected;
                int minutes_selected = Integer.parseInt(minutes_spinner.getSelectedItem().toString());

                hoursVal = hours_selected * 60;

                weightVal = weight_spinner.getSelectedItem().toString();
                double metVal = Double.parseDouble(met);

                String[] conv = weightVal.split(" ", 2);
                String conv2 = conv[0];
                double weightConv = Integer.parseInt(conv2);

                if (!isMetric) {
                    weightConv = weightConv / 2.205;
                }

                double calc = (metVal * 3.5 * weightConv) / 200;
                final double finalCalc = calc * minutes_selected;

                caloriesVal = (int) Math.round(finalCalc);
                calories_burned.setText(String.valueOf(caloriesVal));
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (category_spinner.getSelectedItemPosition()) {
                    case 0:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, bicycling);
                        catNumber = 0;
                        break;
                    case 1:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, conditioning);
                        catNumber = 1;
                        break;
                    case 2:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, dancing);
                        catNumber = 2;
                        break;
                    case 3:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, fishing);
                        catNumber = 3;
                        break;
                    case 4:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, home);
                        catNumber = 4;
                        break;
                    case 5:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, garden);
                        catNumber = 5;
                        break;
                    case 6:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, misc);
                        catNumber = 6;
                        break;
                    case 7:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, music);
                        catNumber = 7;
                        break;
                    case 8:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, occupational);
                        catNumber = 8;
                        break;
                    case 9:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, running);
                        catNumber = 9;
                        break;
                    case 10:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, self_care);
                        catNumber = 10;
                        break;
                    case 11:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, sexual);
                        catNumber = 11;
                        break;
                    case 12:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, sports);
                        catNumber = 12;
                        break;
                    case 13:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, water);
                        catNumber = 13;
                        break;
                    case 14:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, walking);
                        catNumber = 14;
                        break;
                    case 15:
                        subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner_two, winter);
                        catNumber = 15;
                }
                subCategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                sub_category_spinner.setAdapter(subCategoryAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sub_category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = sub_category_spinner.getSelectedItemPosition();
                switch (catNumber) {
                    case 0:
                        met = (bicycling_values[pos]);
                        subCat = bicycling[pos];
                        break;
                    case 1:
                        met = (conditioning_values[pos]);
                        subCat = conditioning[pos];
                        break;
                    case 2:
                        met = (dancing_values[pos]);
                        subCat = dancing[pos];
                        break;
                    case 3:
                        met = (fishing_values[pos]);
                        subCat = fishing[pos];
                        break;
                    case 4:
                        met = (home_values[pos]);
                        subCat = home[pos];
                        break;
                    case 5:
                        met = (garden_values[pos]);
                        subCat = garden[pos];
                        break;
                    case 6:
                        met = (misc_values[pos]);
                        subCat = misc[pos];
                        break;
                    case 7:
                        met = (music_values[pos]);
                        subCat = music[pos];
                        break;
                    case 8:
                        met = (occupational_values[pos]);
                        subCat = occupational[pos];
                        break;
                    case 9:
                        met = (running_values[pos]);
                        subCat = running[pos];
                        break;
                    case 10:
                        met = (self_care_values[pos]);
                        subCat = self_care[pos];
                        break;
                    case 11:
                        met = (sexual_values[pos]);
                        subCat = sexual[pos];
                        break;
                    case 12:
                        met = (sports_values[pos]);
                        subCat = sports[pos];
                        break;
                    case 13:
                        met = (walking_values[pos]);
                        subCat = walking[pos];
                        break;
                    case 14:
                        met = (water_values[pos]);
                        subCat = water[pos];
                        break;
                    case 15:
                        met = (winter_values[pos]);
                        subCat = winter[pos];
                }
                met_score.setText(met);
                double hours_selected = Double.parseDouble(hours_spinner.getSelectedItem().toString());
                double hoursVal = hours_selected;
                int minutes_selected = Integer.parseInt(minutes_spinner.getSelectedItem().toString());

                hoursVal = hours_selected * 60;

                weightVal = weight_spinner.getSelectedItem().toString();
                double metVal = Double.parseDouble(met);

                String[] conv = weightVal.split(" ", 2);
                String conv2 = conv[0];
                double weightConv = Integer.parseInt(conv2);

                if (!isMetric) {
                    weightConv = weightConv / 2.205;
                }

                double calc = (metVal * 3.5 * weightConv) / 200;
                final double finalCalc = calc * minutes_selected;
                calories_burned.setText(String.valueOf(Math.round(finalCalc)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        hours_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                double hours_selected = Double.parseDouble(hours_spinner.getSelectedItem().toString());
                double hoursVal = hours_selected;
                int minutes_selected = Integer.parseInt(minutes_spinner.getSelectedItem().toString());

                hoursVal = hours_selected * 60;

                weightVal = weight_spinner.getSelectedItem().toString();
                double metVal = Double.parseDouble(met);

                String[] conv = weightVal.split(" ", 2);
                String conv2 = conv[0];
                double weightConv = Integer.parseInt(conv2);

                if (!isMetric) {
                    weightConv = weightConv / 2.205;
                }

                double calc = (metVal * 3.5 * weightConv) / 200;
                final double finalCalc = calc * minutes_selected;
                calories_burned.setText(String.valueOf(Math.round(finalCalc)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final ConstraintLayout metFragment = root.findViewById(R.id.met_fragment);
        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View myView = layoutInflater.inflate(R.layout.about_met, null);

        final PopupWindow popupWindow = new PopupWindow(myView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metFragment.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            popupWindow.setWidth(850);
                            popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
                            popupWindow.setElevation(5);
                            popupWindow.showAtLocation(metFragment, Gravity.CENTER, 0, 0);
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
