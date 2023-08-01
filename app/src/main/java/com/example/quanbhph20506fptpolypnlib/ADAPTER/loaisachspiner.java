package com.example.quanbhph20506fptpolypnlib.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.quanbhph20506fptpolypnlib.R;

import java.util.ArrayList;
import java.util.List;

import model.loaisach;
import model.sach;
import model.thanhvien;

public class loaisachspiner extends ArrayAdapter<loaisach> {
    private Context context;
     List<loaisach> list;
    TextView txtloaisach,txttenloaisach;




    public loaisachspiner(@NonNull Context context, List<loaisach>list) {
        super(context, 0,list);
        this.context=context;
        this.list=list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisachspiner,null);

        }
        loaisach tv= list.get(position);
        if (tv!=null){
            txtloaisach=v.findViewById(R.id.spinermaloaisach1);
            txtloaisach.setText(String.valueOf(tv.getMaloai()));
            txttenloaisach=v.findViewById(R.id.spinertenloaisach1);
            txttenloaisach.setText(tv.getTenloai());
        }
        return v;

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v==null){
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.loaisachspinerdrop,null);

        }
        loaisach tv= list.get(position);
        if (tv!=null){
            txtloaisach=v.findViewById(R.id.spinertvloaisachdrop);
            txtloaisach.setText(String.valueOf(tv.getMaloai()));
            txttenloaisach=v.findViewById(R.id.spinertvtensachdrop);
            txttenloaisach.setText(tv.getTenloai());
        }
        return v;
    }
}
