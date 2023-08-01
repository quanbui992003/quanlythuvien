package com.example.quanbhph20506fptpolypnlib.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quanbhph20506fptpolypnlib.R;

import java.util.ArrayList;
import java.util.List;

import model.sach;
import model.thanhvien;

public class thanhvienspineradapter extends ArrayAdapter<thanhvien> {
    private Context context;
 List<thanhvien> list;
TextView tvmatv,tvten;


    public thanhvienspineradapter(@NonNull Context context, List<thanhvien>list) {
        super(context, 0,list);
        this.list = list;
        this.context = context;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.spinerthanhviendrop,null);
        thanhvien th = list.get(position);
//        tvmatv = view.findViewById(R.id.spinertv2);
//        tvmatv.setText(th.getMaTv()+".");
        tvten = view.findViewById(R.id.spinertv3);
        tvten.setText(th.getHoTen());
        return  view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.spinerthanhviendrop,null);
        thanhvien th = list.get(position);
//        tvmatv = view.findViewById(R.id.spinertv2);
//        tvmatv.setText(th.getMaTv()+".");
        tvten = view.findViewById(R.id.spinertv3);
        tvten.setText(th.getHoTen());
        return  view;
       
    }
}
