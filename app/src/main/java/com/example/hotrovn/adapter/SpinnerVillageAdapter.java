package com.example.hotrovn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotrovn.R;
import com.example.hotrovn.model.address.Village;

import java.util.ArrayList;

public class SpinnerVillageAdapter extends ArrayAdapter<Village> {
    public SpinnerVillageAdapter(Context context, ArrayList<Village> villagesList) {
        super(context, 0, villagesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }
    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner_address, parent, false);
        };
        TextView tv_address = convertView.findViewById(R.id.tv_spinner_address);

        Village village = getItem(position);
        if (village !=null){
            tv_address.setText(village.getName());
        }
        return convertView;
    }
}
