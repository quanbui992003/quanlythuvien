package FARMENT.sach;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanbhph20506fptpolypnlib.ADAPTER.loaisachadapter;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.loaisachspiner;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.sachadapter;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.thanhvienadapter;
import com.example.quanbhph20506fptpolypnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Dao.loaisachdao;
import Dao.sachdao;
import Dao.thanhviendao;
import model.loaisach;
import model.sach;
import model.thanhvien;

public class tabsach extends Fragment {

    RecyclerView rcv;
    FloatingActionButton fab;
    sachdao sachdao;
    List<sach> listsach= new ArrayList<>();
    sachadapter sachadapter;

    EditText txtmasach,txttensach,txtgiathue;
    Spinner spinner;
    Button btnthem,btnhuy;

    loaisachdao loaisachdao;
    List<loaisach> listloaisach;
    loaisachadapter loaisachadapter;
    loaisachspiner loaisachspiner;
    loaisach loaisach;
    int maloaisach;
    public tabsach() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tabsach,container,false);
        rcv=view.findViewById(R.id.recycsach);
        fab=view.findViewById(R.id.floattingsach);
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        sachdao = new sachdao(getContext());
        listsach = (List<sach>)sachdao.getAllsach();
        sachadapter=new sachadapter(getActivity(),listsach);
        rcv.setAdapter(sachadapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             openthem();
            }
        });
        return view;
    }
    @SuppressLint("MissingInflatedId")
    public void openthem(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getLayoutInflater();
        View view=inflater.inflate(R.layout.insertsach,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();


        txtmasach=view.findViewById(R.id.txtmasach);
        txttensach=view.findViewById(R.id.txttensach);
        txtgiathue=view.findViewById(R.id.txtgiathuesach);
        spinner=view.findViewById(R.id.spsach);
        btnthem=view.findViewById(R.id.themloaisach);
        btnhuy=view.findViewById(R.id.deletesach);
        txtmasach.setEnabled(false);

        listloaisach = new ArrayList<loaisach>();
        loaisachdao = new loaisachdao(getContext());
        listloaisach=(List<loaisach>) loaisachdao.getAllLoaiSach();
        loaisachspiner = new loaisachspiner(getContext(),listloaisach);
        spinner.setAdapter(loaisachspiner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maloaisach = listloaisach.get(i).getMaloai();
//                String nameloaisach = listloaisach.get(i).getTenloai();
                Toast.makeText(getContext(), "chon:"+listloaisach.get(i).getTenloai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach sach = new sach();
                sach.setTenSach(txttensach.getText().toString());
                sach.setGiathue(Integer.parseInt(txtgiathue.getText().toString()));
                sach.setMaLoai(maloaisach);
                if(sachdao.insert(sach)>0){
                    Toast.makeText(getContext(), "thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    listsach.clear();
                    sachdao = new sachdao(getContext());
                    listsach = (List<sach>)sachdao.getAllsach();
                    sachadapter=new sachadapter(getActivity(),listsach);
                    rcv.setAdapter(sachadapter);
                    sachadapter.notifyDataSetChanged();
                }
            }
        });
//        btnhuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
    }


}