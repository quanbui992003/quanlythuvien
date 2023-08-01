package com.example.quanbhph20506fptpolypnlib.ADAPTER;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanbhph20506fptpolypnlib.R;

import java.util.ArrayList;
import java.util.List;

import Dao.thanhviendao;
import model.thanhvien;

public class thanhvienadapter extends RecyclerView.Adapter<thanhvienadapter.thanhvienHolder> {

    private Context context;
    private List<thanhvien>list = new ArrayList<>();
    thanhviendao dao;


    public thanhvienadapter(Context context, List<thanhvien> list) {
        this.context = context;
        this.list = list;
        dao = new thanhviendao(context);
    }

    @NonNull
    @Override
    public thanhvienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemdanhsach,parent,false);
        return new thanhvienHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull thanhvienHolder holder, int position) {
        thanhvien kh= list.get(position);
        holder.txtmatv.setText(String.valueOf(kh.getMaTv()));
        holder.txttentv.setText(kh.getHoTen());
        holder.txtnamsinh.setText(kh.getNamSinh());
    }
    @Override
    public int getItemCount() {
       return list.size();
    }

    public class thanhvienHolder extends RecyclerView.ViewHolder {
        TextView txtmatv,txttentv,txtnamsinh;
        ImageView txtdelete,txtupdate;
        public thanhvienHolder(@NonNull View itemView) {
            super(itemView);
            txtmatv=itemView.findViewById(R.id.dsmatv);
            txttentv=itemView.findViewById(R.id.dshoten);
            txtnamsinh=itemView.findViewById(R.id.dsnamsinh);
            txtdelete=itemView.findViewById(R.id.delete);
            txtupdate=itemView.findViewById(R.id.update);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull thanhvienHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        thanhvien tv= list.get(position);
        holder.txtdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dao.delete(tv.getMaTv())){
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
  list.clear();
  list.addAll(dao.getAllThanhVien());
  notifyDataSetChanged();
                }
            }
        });
        holder.txtupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              openupdate(tv);
            }
        });
    }
    public void openupdate(thanhvien tv){
        AlertDialog.Builder builder=new  AlertDialog.Builder(context);
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.updatethanhvien,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();

        EditText txtmatv=view.findViewById(R.id.txtupdatematv);
        EditText txthotentv=view.findViewById(R.id.txtupdatetentv) ;
        EditText txtnamsinhtv=view.findViewById(R.id.txtupdatenamsinhtv);
        Button txtup=view.findViewById(R.id.btnupdatetv);
        Button txthuy=view.findViewById(R.id.btnhuytv);
//
//        txtmatv.setText(tv.getMaTv());
        txthotentv.setText(tv.getHoTen());
        txtnamsinhtv.setText(tv.getNamSinh());

        txtup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 thanhvien tv= new thanhvien();
//                tv.setMaTv(Integer.parseInt(txtmatv.getText().toString()));
                tv.setHoTen(txthotentv.getText().toString());
                tv.setNamSinh(txtnamsinhtv.getText().toString());
                if(dao.update(tv)){
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    dialog.dismiss();
                    list.addAll(dao.getAllThanhVien());
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "k thanh cong", Toast.LENGTH_SHORT).show();
                }
        }
      });
    }
}
