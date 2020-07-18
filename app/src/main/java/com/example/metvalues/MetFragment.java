package com.example.metvalues;

import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetFragment extends Fragment {

    private Adapter subCategoryAdapter;
    private List<String> total_list;


    private boolean isMetric;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.met_fragment, container, false);

        List<String> weight_list = new ArrayList<>();
        List<String> category_list = new ArrayList<>();
        List<String> activity_list = new ArrayList<>();
        List<String> score_list = new ArrayList<>();

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

        String[] bicycling = getResources().getStringArray(R.array.bicycling);
        String[] conditioning = getResources().getStringArray(R.array.conditioning);
        String[] dancing = getResources().getStringArray(R.array.dancing);
        String[] fishing = getResources().getStringArray(R.array.fishing);
        String[] home = getResources().getStringArray(R.array.home);
        String[] garden  = getResources().getStringArray(R.array.garden);
        String[] misc = getResources().getStringArray(R.array.misc);
        String[] music = getResources().getStringArray(R.array.music);
        String[] occupational = getResources().getStringArray(R.array.occupation);
        String[] running = getResources().getStringArray(R.array.running);
        String[] self_care = getResources().getStringArray(R.array.self_care);
        String[] sexual = getResources().getStringArray(R.array.sex_values);
        String[] sports = getResources().getStringArray(R.array.sports);
        String[] walking = getResources().getStringArray(R.array.walking);
        String[] water = getResources().getStringArray(R.array.walking);
        String[] winter = getResources().getStringArray(R.array.winter);

        String[] bicycling_values = getResources().getStringArray(R.array.bicycle_values);
        String[] conditioning_values = getResources().getStringArray(R.array.conditioning_values);
        String[] dancing_values = getResources().getStringArray(R.array.dancing_values);
        String[] fishing_values = getResources().getStringArray(R.array.fishing_values);
        String[] home_values = getResources().getStringArray(R.array.home_values);
        String[] garden_values = getResources().getStringArray(R.array.garden_values);
        String[] misc_values = getResources().getStringArray(R.array.misc_values);
        String[] music_values = getResources().getStringArray(R.array.music_values);
        String[] occupational_values = getResources().getStringArray(R.array.occupation_values);
        String[] running_values = getResources().getStringArray(R.array.running_values);
        String[] self_care_values = getResources().getStringArray(R.array.self_care_values);
        String[] sexual_values = getResources().getStringArray(R.array.sex_values);
        String[] sports_values = getResources().getStringArray(R.array.sports_values);
        String[] walking_values = getResources().getStringArray(R.array.walking_values);
        String[] water_values = getResources().getStringArray(R.array.walking_values);
        String[] winter_values = getResources().getStringArray(R.array.winter_values);

        Spinner weight_spinner = root.findViewById(R.id.met_spinnerOne);
        Spinner category_spinner = root.findViewById(R.id.met_spinnerTwo);
        Spinner sub_category_spinner = root.findViewById(R.id.met_spinnerThree);

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

        ArrayAdapter<String> weightAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, weight_list);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, category_list);

        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weight_spinner.setAdapter(weightAdapter);
        category_spinner.setAdapter(categoryAdapter);

        switch (category_spinner.getSelectedItemPosition()) {
            case 0:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, bicycling_values);
                 break;
            case 1:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, conditioning_values);
                break;
            case 2:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, dancing_values);
                break;
            case 3:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, fishing_values);
                break;
            case 4:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, home_values);
                break;
            case 5:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, garden_values);
                break;
            case 6:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, misc_values);
                break;
            case 7:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, music_values);
                break;
            case 8:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, occupational_values);
                break;
            case 9:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, running_values);
                break;
            case 10:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, bicycling_values);
                break;
            case 11:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, self_care_values);
                break;
            case 12:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, sexual_values);
                break;
            case 13:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, sports_values);
                break;
            case 14:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, walking_values);
                break;
            case 15:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, water_values);
                break;
            case 16:
                subCategoryAdapter = new ArrayAdapter<>(getContext(), R.layout.spinner, winter_values);
        }

        return root;
    }

}
