package com.example.metvalues;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MetFragment extends Fragment {

    private List<String> total_list;
    private List<String> category_list;
    private List<String> activity_list;
    private List<String> score_list;

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.met_fragment, container, false);

        category_list = new ArrayList<>();
        activity_list = new ArrayList<>();
        score_list = new ArrayList<>();

        return root;
    }

}
