package FARMENT.loaisach;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanbhph20506fptpolypnlib.ADAPTER.loaisachadapter;
import com.example.quanbhph20506fptpolypnlib.ADAPTER.thanhvienadapter;
import com.example.quanbhph20506fptpolypnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Dao.loaisachdao;
import Dao.thanhviendao;
import model.loaisach;
import model.thanhvien;


public class tabloaisach extends Fragment {
    RecyclerView rcv;
    FloatingActionButton faa;
    ArrayList<loaisach> list= new ArrayList<>();
    Dao.loaisachdao loaisachdao;
    com.example.quanbhph20506fptpolypnlib.ADAPTER.loaisachadapter loaisachadapter;




    public tabloaisach() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tabloaisach,container,false);
        rcv=view.findViewById(R.id.recycloaisach);
        faa=view.findViewById(R.id.floattinglaosach);
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        loaisachdao=new loaisachdao(getContext());
        list= (ArrayList<loaisach>) loaisachdao.getAllLoaiSach();
        loaisachadapter=new loaisachadapter(getContext(),list);
        rcv.setAdapter(loaisachadapter);
        faa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                insetloaisach();
            }
        });

        return view;

    }
    public void insetloaisach(){
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater= getLayoutInflater();
        View view=inflater.inflate(R.layout.insertloaisach,null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        EditText txtmalaoisach,txttenloaisach,txtgialoaisach;
        Button them,huy;
        txtmalaoisach=view.findViewById(R.id.txtmaloaisach);
        txttenloaisach=view.findViewById(R.id.txttenloaisach);
        txtgialoaisach=view.findViewById(R.id.txtgialoaisach);
        them=view.findViewById(R.id.themloaisach);
        huy=view.findViewById(R.id.huyloaisach);


        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check=1;
                if(txttenloaisach.getText().toString().isEmpty()){
                    txttenloaisach.setError("k de trong loa sach");
                } else if(txtgialoaisach.getText().toString().isEmpty()){
                    txttenloaisach.setError("k de gia sach");
                } else if(check>0){
                    loaisach ls= new loaisach();
//                ls.setMaloai(Integer.parseInt(txtmalaoisach.getText().toString()));
                    ls.setTenloai(txttenloaisach.getText().toString());
//                ls.setGiathue(Integer.parseInt(txtgialoaisach.getText().toString()));
                    if (loaisachdao.insert(ls)) {
                        Toast.makeText(getActivity(), "thanh cong", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        list.clear();
                        loaisachdao = new loaisachdao(getContext());
                        list = (ArrayList<loaisach>) loaisachdao.getAllLoaiSach();
                        loaisachadapter = new loaisachadapter(getActivity(), list);
                        rcv.setAdapter(loaisachadapter);
                    }
                }




            }
        });
    }
}