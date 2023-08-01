package com.example.quanbhph20506fptpolypnlib.ADAPTER;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanbhph20506fptpolypnlib.R;

import java.util.ArrayList;
import java.util.List;

import Dao.loaisachdao;
import Dao.sachdao;
import model.loaisach;
import model.sach;

public class sachadapter extends RecyclerView.Adapter<sachadapter.viewholder> {
     Context context;
    List<sach> list;
    sachdao sachdao;
    loaisachdao loaisachdao;
    List<loaisach> listloaisach ;
    loaisachadapter loaisachadapter;
    loaisachspiner loaisachspiner;
    int maloaisach;

    public sachadapter() {
    }

    public sachadapter(Context context, List<sach> list) {
        this.context = context;
        this.list = list;
        sachdao = new sachdao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemdanhsachsach, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        sachdao = new sachdao(context);
        loaisachdao = new loaisachdao(context);
        sach sach = list.get(position);
        if (sach != null) {
            loaisach loaiSach1 = loaisachdao.getIdLoaiSach(String.valueOf(sach.getMaLoai()));
            holder.txtmasach.setText(String.valueOf(sach.getMaSach()));
            holder.txttensach.setText(sach.getTenSach());
            holder.txttienthue.setText(sach.getGiathue() + " VND");
            holder.txtmaloaisach1.setText(String.valueOf(loaiSach1.getTenloai()));
        }
//        sachdao = new sachdao(context);
//        loaisachdao = new loaisachdao(context);
//        sach sach = list.get(position);
//        if(sach != null) {
//            loaisach ls= loaisachdao.getIdLoaiSach(String.valueOf(sach.getMaLoai()));
//            holder.txtmasach.setText(String.valueOf(sach.getMaSach()));
//            holder.txttienthue.setText(sach.getGiathue() + " VND");
//            holder.txtmaloaisach.setText(ls.getTenloai());
//        }
        holder.txtupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatesach(sach);
            }
        });
        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sachdao.delete(sach.getMaSach())) {
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(sachdao.getAllsach());
                    notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txtmasach, txttensach, txttienthue,txtmaloaisach1;
        ImageView txtupdate, txtdelete;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtmasach = itemView.findViewById(R.id.dsmasach);
            txttensach = itemView.findViewById(R.id.dstensach);
            txttienthue = itemView.findViewById(R.id.dstienthuesach);
            txtmaloaisach1 = itemView.findViewById(R.id.spinersach);
            txtupdate = itemView.findViewById(R.id.updatesach);
            txtdelete = itemView.findViewById(R.id.deletesach);


        }
    }





    public void updatesach(sach s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.updatesach, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText txttensach, txtgiathue;
        Spinner spinnerup;
        Button btnupdate, btndelete;

        txttensach = view.findViewById(R.id.txtupdatetensach);
        txtgiathue = view.findViewById(R.id.txtupdategiathuesach);
        spinnerup = view.findViewById(R.id.spupdatesach);
        btnupdate = view.findViewById(R.id.updatethemloaisach);
        btndelete = view.findViewById(R.id.updatehuyloaisach);
//
        listloaisach = new ArrayList<loaisach>();
        loaisachdao = new loaisachdao(context);
        listloaisach = (List<loaisach>) loaisachdao.getAllLoaiSach();
        loaisachspiner = new loaisachspiner(context, listloaisach);
        spinnerup.setAdapter(loaisachspiner);
//
        spinnerup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maloaisach = listloaisach.get(i).getMaloai();
                Toast.makeText(context, "chon" + listloaisach.get(i).getTenloai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s.setTenSach(txttensach.getText().toString());
                s.setGiathue(Integer.parseInt(txtgiathue.getText().toString()));
                s.setMaLoai(maloaisach);
                if (sachdao.update(s)) {
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    list.addAll(sachdao.getAllsach());
                    notifyDataSetChanged();
                }
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txttensach.setText("");
                txtgiathue.setText("");
            }
        });
    }



}
