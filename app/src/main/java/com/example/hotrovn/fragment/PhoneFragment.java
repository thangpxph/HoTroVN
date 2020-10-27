package com.example.hotrovn.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.hotrovn.R;

import java.util.ArrayList;

public class PhoneFragment extends Fragment {
    private Spinner spinner;
    private ArrayList<com.example.hotrovn.model.Spinner> spinnerList;
    private SpinnerAdapter spinnerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phone, container, false);
        initList();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController controller = Navigation.findNavController(view);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_phoneFragment_to_newsHelpFragment);
            }
        });
        spinnerAdapter = new com.example.hotrovn.adapter.SpinnerAdapter(getContext(), spinnerList);
        spinner.setAdapter(spinnerAdapter);

    }

    private void initList() {
        spinnerList = new ArrayList<>();
        spinnerList.add(new com.example.hotrovn.model.Spinner(R.drawable.vietnam, "Việt Nam (+84)"));
    }
}