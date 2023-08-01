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

import java.util.List;

import model.loaisach;
import model.sach;
import model.thanhvien;

public class Sachspineradpter extends ArrayAdapter<sach> {
    private Context context;
    List<sach> list;
    TextView txtmasach,txttensach;


    public Sachspineradpter(@NonNull Context context, List<sach> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.getviewsachspiner,null);
        sach th = list.get(position);
//        tvmatv = view.findViewById(R.id.spinertv2);
//        tvmatv.setText(th.getMaTv()+".");
        txttensach = view.findViewById(R.id.spinermasach);
        txttensach.setText(th.getTenSach());
        return  view;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.sachspinerdrop, null);
        sach th = list.get(position);
//        tvmatv = view.findViewById(R.id.spinertv2);
//        tvmatv.setText(th.getMaTv()+".");
        txttensach = view.findViewById(R.id.spinermasachdrop);
        txttensach.setText(th.getTenSach());
        return view;
    }
    }
