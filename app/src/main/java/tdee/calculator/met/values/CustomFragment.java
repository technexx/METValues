package tdee.calculator.met.values;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomFragment extends Fragment {

    private String category;
    private double met;
    private int minutes;
    private double hours;
    private String calories;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.custom_fragment, container, false);

        SharedPreferences pref = getContext().getSharedPreferences("SharedPref", 0);
        SharedPreferences.Editor edit = pref.edit();

        List<String> catList = new ArrayList<>();
        List<String> metList = new ArrayList<>();
        List<String> hoursList = new ArrayList<>();
        List<String> caloriesList = new ArrayList<>();

        String metConv = "";
        String hoursConv = "";

        Bundle args = getArguments();
        if (args != null) {
            category = args.getString("category");
            metConv = args.getString("met");
            hoursConv = args.getString("hours");
            calories = args.getString("calories");

            met = Double.parseDouble(metConv);
            hours = Double.parseDouble(hoursConv);


            RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
            CustomAdapter customAdapter = new CustomAdapter(getContext(), catList, metList, hoursList, caloriesList);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            //Todo: Conversion here for "total" calories and 24 hour max.

        }
        return root;
    }
}
