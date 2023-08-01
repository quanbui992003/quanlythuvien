package FARMENT.quanlyphieumuon;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanbhph20506fptpolypnlib.ADAPTER.Sachspineradpter;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.loaisachspiner;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.phieumuonadapter;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.sachadapter;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.thanhvienadapter;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.thanhvienspineradapter;
import com.example.quanbhph20506fptpolypnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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


public class tabphieumuon extends Fragment {
 private Context context;
 RecyclerView rcv;
 FloatingActionButton faa;
//phieumuon


 //model
    List<phieumuon> listpm;
    List<sach> listsach;
    List<thanhvien> listthanhvien= new ArrayList<>();
//dao
  sachdao sachdao;
   thanhviendao thanhviendao;
    phieumuondao dao;
//adpater
    thanhvienadapter thanhvienadapter;
    sachadapter sachadapter;
    phieumuonadapter phieumuonadapter;
    //spinner
    thanhvienspineradapter thanhvienspiner;
    Sachspineradpter lsachspiner;

    int masach,mathanhvien,tienthue;
    Spinner spinnertv,spinnersach;
    TextInputLayout edtienthue,edbngaythue;

    SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
    public tabphieumuon() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tabphieumuon,container,false);
        rcv=view.findViewById(R.id.recycphieumuon);
        faa=view.findViewById(R.id.floattingphieumuon);
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        dao= new phieumuondao(getContext());
        listpm=dao.getAllPhieuMuon();
        phieumuonadapter= new phieumuonadapter(getActivity(),listpm);
        rcv.setAdapter(phieumuonadapter);

        faa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertpm();
            }
        });
        return view;
    }
    public void insertpm(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getLayoutInflater();
        View view=inflater.inflate(R.layout.insertphieumuon,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();
        CheckBox checksach;
        Button btnthempm,btnhuypm;


        spinnertv =view.findViewById(R.id.spnthanhvien);
        spinnersach=view.findViewById(R.id.spnsachphieu_muon);
        checksach=view.findViewById(R.id.chk_status);
        TextView edtienthuepm= view.findViewById(R.id.phtienthue);
        TextView edtrasach= view.findViewById(R.id.phtrasach);
        TextView edngaymuon= view.findViewById(R.id.pmngaymuon);
        btnthempm=view.findViewById(R.id.btnthemadd_phieu_muon);
        btnhuypm=view.findViewById(R.id.btnhuyphieu_muon);
        edngaymuon.setText("ngaythue:"+format.format(new Date()));

        listsach = new ArrayList<sach>();
        sachdao = new sachdao(getContext());
        listsach = sachdao.getAllsach();
        lsachspiner = new Sachspineradpter(getContext(),listsach);
        spinnersach.setAdapter(lsachspiner);
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
        thanhviendao = new thanhviendao(getContext());
        listthanhvien=(List<thanhvien>) thanhviendao.getAllThanhVien();
        thanhvienspiner = new thanhvienspineradapter(getContext(),listthanhvien);
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

        btnthempm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                phieumuon muon = new phieumuon();
//                muon.setMaPM(Integer.parseInt(txtmaphieumuon.getText().toString()));

                muon.setMasach(masach);
                muon.setMaTV(mathanhvien);
                muon.setNgay(new Date());
                muon.setTienthue(tienthue);
                if (checksach.isChecked()){
                    muon.setTrasach(1);
                }else {
                    muon.setTrasach(0);
                }
                if (dao.insert(muon)){
                    Toast.makeText(getContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
//                    listpm.clear();
                    dao= new phieumuondao(getContext());
                    listpm=dao.getAllPhieuMuon();
                    phieumuonadapter= new phieumuonadapter(getActivity(),listpm);
                    rcv.setAdapter(phieumuonadapter);
                    phieumuonadapter.notifyDataSetChanged();


                }else{
                    Toast.makeText(getContext(), "them that bai", Toast.LENGTH_SHORT).show();
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