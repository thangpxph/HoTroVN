package com.example.hotrovn.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hotrovn.R;
import com.example.hotrovn.adapter.SpinnerDistrictAdapter;
import com.example.hotrovn.adapter.SpinnerProvinceAdapter;
import com.example.hotrovn.adapter.SpinnerVillageAdapter;
import com.example.hotrovn.model.address.Address;
import com.example.hotrovn.model.address.District;
import com.example.hotrovn.model.address.Province;
import com.example.hotrovn.model.address.Village;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class AddNewsHelpFragment extends Fragment {
    private Spinner spinner_province, spinner_district, spinner_village;
    private ArrayList<Province> provincesList;
    private ArrayList<District> districtsList;
    private ArrayList<Village> villagesList;
    SpinnerProvinceAdapter spinnerProvinceAdapter;
    SpinnerDistrictAdapter spinnerDistrictAdapter;
    SpinnerVillageAdapter spinnerVillageAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_news_help, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner_province = (Spinner) view.findViewById(R.id.spinner_province);
        spinner_district = (Spinner) view.findViewById(R.id.spinner_district);
        spinner_village = (Spinner) view.findViewById(R.id.spinner_village);
        view.findViewById(R.id.btnSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        Reader reader = new InputStreamReader(getResources().openRawResource(R.raw.data));
        JsonElement json = new Gson().fromJson(reader, JsonElement.class);
        String jsonString = json.toString();
        final Address address = new Gson().fromJson(jsonString, Address.class);
        initGetList(address);
        spinnerProvinceAdapter = new SpinnerProvinceAdapter(getContext(), provincesList);
        spinner_province.setAdapter(spinnerProvinceAdapter);
        spinner_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                if (position == 0) {
                    initDistrict();
                    initVillage();
                } else {
                    districtsList = new ArrayList<>();
                    districtsList.add(new District(0, "Chọn Quận/Huyện"));
                    districtsList.addAll(address.getProvince().get(position - 1).getDistrict());
                    spinnerDistrictAdapter = new SpinnerDistrictAdapter(getContext(), districtsList);
                    spinner_district.setAdapter(spinnerDistrictAdapter);
                    spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                            if (pos == 0) {
                                initVillage();
                            } else {
                                villagesList = new ArrayList<>();
                                villagesList.add(new Village(0, "Chọn Phường/Xã"));
                                Log.e("pos", position + "");
                                villagesList.addAll(address.getProvince().get(position - 1).getDistrict().get(pos - 1).getVillage());
                                spinnerVillageAdapter = new SpinnerVillageAdapter(getContext(), villagesList);
                                spinner_village.setAdapter(spinnerVillageAdapter);
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void initVillage() {
        villagesList = new ArrayList<>();
        villagesList.add(new Village(0, "Chọn Phường/Xã"));
        spinnerVillageAdapter = new SpinnerVillageAdapter(getContext(), villagesList);
        spinner_village.setAdapter(spinnerVillageAdapter);
    }

    private void initDistrict() {
        districtsList = new ArrayList<>();
        districtsList.add(new District(0, "Chọn Quận/Huyện"));
        spinnerDistrictAdapter = new SpinnerDistrictAdapter(getContext(), districtsList);
        spinner_district.setAdapter(spinnerDistrictAdapter);
    }

    private void initGetList(Address address) {
        provincesList = new ArrayList<>();
        provincesList.add(new Province(0, "Chọn Tỉnh/Thành Phố"));
        provincesList.addAll(address.getProvince());
    }


    private void showDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_success);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtt = window.getAttributes();
        windowAtt.gravity = Gravity.CENTER;
        window.setAttributes(windowAtt);

        dialog.findViewById(R.id.btnDialogDismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}