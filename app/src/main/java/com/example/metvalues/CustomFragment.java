package com.example.metvalues;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class CustomFragment extends Fragment {

    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.custom_fragment, container, false);

        return root;

    }
}
