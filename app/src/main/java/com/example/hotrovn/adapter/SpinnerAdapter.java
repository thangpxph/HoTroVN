package com.example.hotrovn.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hotrovn.R;
import com.example.hotrovn.model.Spinner;

import java.util.ArrayList;


public class SpinnerAdapter extends ArrayAdapter<Spinner> {
    public SpinnerAdapter(Context context, ArrayList<Spinner> spinnerList) {
        super(context, 0, spinnerList);
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner, parent, false);
        };
        ImageView imgSpinner = convertView.findViewById(R.id.image_spinner);
        TextView tvSpinner = convertView.findViewById(R.id.tv_spinner);

        Spinner spinner = getItem(position);
        if (spinner !=null){
            imgSpinner.setImageResource(spinner.getImgSpinner());
            tvSpinner.setText(spinner.getTvSpinner());
        }
            return convertView;
    }
}
