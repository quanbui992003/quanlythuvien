package com.example.quanbhph20506fptpolypnlib.ADAPTER;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanbhph20506fptpolypnlib.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.loaisachdao;
import Dao.phieumuondao;
import Dao.sachdao;
import Dao.thanhviendao;
import model.loaisach;
import model.phieumuon;
import model.sach;
import model.thanhvien;

public class phieumuonadapter extends RecyclerView.Adapter<phieumuonadapter.vienHolder> {

    private Context context;
     List<phieumuon> list ;
    List<thanhvien> listthanhvien;
    List<sach> listsach= new ArrayList<>();
    sachadapter sachadapter;
    thanhvienspineradapter thanhvienspineradapter;
    Sachspineradpter sachspineradpter;
    thanhvienspineradapter thanhvienspiner;
    thanhviendao thanhviendao;
    sachdao sachdao;
    phieumuondao phieumuondao;
    int masach,mathanhvien,tienthue;
    //
    loaisachspiner lsachspiner;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    public phieumuonadapter() {
    }
    public phieumuonadapter(Context context, List<phieumuon> list) {
        this.context = context;
        this.list = list;
        phieumuondao = new phieumuondao(context);
    }



    @NonNull
    @Override
    public vienHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemdanhsachphieumuon, parent, false);
        return new vienHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull vienHolder holder, int position) {
        phieumuon phieuMuon = list.get(position);
        phieumuondao = new phieumuondao(context);
        thanhviendao = new thanhviendao(context);
        sachdao = new sachdao(context);
        if(phieuMuon != null) {
             thanhvien thanhVien= thanhviendao.getIdTV(String.valueOf(phieuMuon.getMaTV()));
             sach s = sachdao.getIdSach(String.valueOf(phieuMuon.getMasach()));
              holder.txtmaphieumuonpm.setText("MAPM :"+String.valueOf(phieuMuon.getMaPM()));
              holder.txtmasachpm.setText("TEN SACH:"+String.valueOf(s.getTenSach()));
              holder.txtmatvpm.setText("TEN THANH VIEN:"+thanhVien.getHoTen());
              holder.txttienthue.setText("GIA:"+s.getGiathue() + " vnđ");
            if(phieuMuon.getTrasach() == 1) {
                holder.txtngaytra.setText("Đã trả sách.");
                holder.txtngaytra.setTextColor(Color.BLUE);

            }else {
                holder.txtngaytra.setText(" Chưa trả sách.");
                holder.txtngaytra.setTextColor(Color.RED);
            }
            holder.txtngaymuon.setText("Ngày thuê: "+ phieuMuon.getNgay());

      }
        holder.deletepm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phieumuondao.delete(phieuMuon.getMaPM())) {
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(phieumuondao.getAllPhieuMuon());
                    notifyDataSetChanged();
                }
            }
        });
        holder.updatepm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          updatepm(phieuMuon);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class vienHolder extends RecyclerView.ViewHolder {

        TextView txtmaphieumuonpm, txtmattpm, txtmatvpm, txtmasachpm, txttienthue, txtngaymuon, txtngaytra;
        ImageView deletepm, updatepm;

        public vienHolder(@NonNull View itemView) {
            super(itemView);
            txtmaphieumuonpm = itemView.findViewById(R.id.dsmaphieumuon);
            txtmattpm = itemView.findViewById(R.id.dsmattpm);
            txtmatvpm = itemView.findViewById(R.id.dsmatvpm);
            txtmasachpm = itemView.findViewById(R.id.dsmasachpm);
            txttienthue = itemView.findViewById(R.id.dstienthue);
            txtngaymuon = itemView.findViewById(R.id.dsngay);
            deletepm = itemView.findViewById(R.id.deletepm);
            updatepm = itemView.findViewById(R.id.updatepm);
            txtngaytra = itemView.findViewById(R.id.dsngaytra);

        }
    }
    public  void updatepm(phieumuon muon){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.updatelphieumuon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        CheckBox checksach;
        Button btnthempm,btnhuypm;
        Spinner spinnertv,spinnersach;

//        TextView txtupmaphieumuon=view.findViewById(R.id.updatemaphieumuon);
//        TextView txtupmattphieumuon=view.findViewById(R.id.updatemattphieumuon);
        spinnertv =view.findViewById(R.id.updatematvphieumuon);
        spinnersach=view.findViewById(R.id.updatemasachphieumuon);
        checksach=view.findViewById(R.id.btncheckupdate);
        TextView edtienthuepm= view.findViewById(R.id.updatetienthuephieumuon);
//        TextView edtrasach= view.findViewById(R.id.updatengaytra);
        TextView edngaymuon= view.findViewById(R.id.updatengaymuon);
        btnthempm=view.findViewById(R.id.btnupdatephieumuon);
        btnhuypm=view.findViewById(R.id.btnhuyupdatephieumuon);
        edngaymuon.setText("ngaythue:"+format.format(new Date()));
//
//        for( int i=0;i<listsach.size();i++) {
//            if (muon.getMasach() == (listsach.get(i).getMaSach())) {
//                spinnersach.setSelection(i);
//            }
//        }
//        for(int i=0;i<listthanhvien.size();i++){
//            if(muon.getMaTV()==(listthanhvien.get(i).getMaTv())){
//                spinnertv.setSelection(i);
//            }
//
//        }
        if(muon.getTrasach()==1){
            checksach.setChecked(true);
        }else{
            checksach.setChecked(false);
        }
        listsach = new ArrayList<sach>();
        sachdao = new sachdao(context);
        listsach = sachdao.getAllsach();
        sachspineradpter = new Sachspineradpter(context,listsach);
        spinnersach.setAdapter(sachspineradpter);
        spinnersach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                masach = listsach.get(i).getMaSach();
                tienthue=listsach.get(i).getGiathue();
                edtienthuepm.setText("tienthue"+tienthue);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listthanhvien = new ArrayList<thanhvien>();
        thanhviendao = new thanhviendao(context);
        listthanhvien=(List<thanhvien>) thanhviendao.getAllThanhVien();
        thanhvienspiner = new thanhvienspineradapter(context,listthanhvien);
        spinnertv.setAdapter(thanhvienspiner);
//
        spinnertv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mathanhvien = listthanhvien.get(i).getMaTv();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//
        btnthempm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                muon.setMasach(masach);
                muon.setMaTV(mathanhvien);
                muon.setNgay(new Date());
                muon.setTienthue(tienthue);
                if (checksach.isChecked()){
                    muon.setTrasach(1);
                }else {
                    muon.setTrasach(0);
                }
                if (phieumuondao.update(muon)){
                    Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
//                    listpm.clear();
                    phieumuondao= new phieumuondao(context);
                    list=phieumuondao.getAllPhieuMuon();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "them that bai", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuypm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
