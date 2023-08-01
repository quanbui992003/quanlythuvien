package FARMENT.thanhvien;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanbhph20506fptpolypnlib.ADAPTER.thanhvienadapter;
import com.example.quanbhph20506fptpolypnlib.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import Dao.thanhviendao;
import model.thanhvien;


public class tabthanhvien extends Fragment {
    RecyclerView rcv;
    List<thanhvien> list;
    FloatingActionButton faa;
    thanhviendao thanhviendao;
    thanhvienadapter thanhvienadapter;
    Context context;


    public tabthanhvien() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabthanhvien, container, false);

        rcv=view.findViewById(R.id.recyc);
        faa=view.findViewById(R.id.floattingqls);
        thanhviendao= new thanhviendao(getActivity());
        list =(List<thanhvien>) thanhviendao.getAllThanhVien();
        thanhvienadapter=new thanhvienadapter(getActivity(),list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        rcv.setLayoutManager(manager);
        rcv.setAdapter(thanhvienadapter);
        faa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            opnedialog();
            }
        });
        return view;
    }
public void opnedialog(){
    AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
    LayoutInflater inflater= getLayoutInflater();
    View view=inflater.inflate(R.layout.floatingthemthanhvien,null);
    builder.setView(view);
    Dialog dialog=builder.create();
    dialog.show();
    EditText txtma,txthoten,txtnamsinh;
    Button them,huy;

    txtma=view.findViewById(R.id.txtmatv);
    txthoten=view.findViewById(R.id.txtten);
    txtnamsinh=view.findViewById(R.id.txtnamsinh);
    them=view.findViewById(R.id.them);
    huy=view.findViewById(R.id.huy);
//    txtma.setEnabled(false);
    them.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            int check=1;
            if(txthoten.getText().toString().isEmpty()) {
                txthoten.setError("k de trong");
            }else if(txtnamsinh.getText().toString().isEmpty()){
                txtnamsinh.setError("k de trong");
            }else if(txthoten.getText().toString().length()<3){
                txthoten.setError("so ky tu phai hon 3");
            }else if(txthoten.getText().toString().length()>15){
                txthoten.setError("so ky tu phai nho hon 15");
            }else if(txtnamsinh.getText().toString().length()<3){
                txtnamsinh.setError("so ky tu phai hon 3");
            }else if(txtnamsinh.getText().toString().length()>15){
                txtnamsinh.setError("so ky tu phai nho hon 15");

            }else if(check>0){
                list=new ArrayList<>();
                thanhvien th = new thanhvien();

//            th.setMaTv(Integer.parseInt(txtma.getText().toString()));
                th.setHoTen(txthoten.getText().toString());
                th.setNamSinh(txtnamsinh.getText().toString());
                if (thanhviendao.insert(th)) {
                    Toast.makeText(getActivity(), "thanh cong", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    list.clear();
                    thanhviendao = new thanhviendao(getContext());
                    list = (List<thanhvien>) thanhviendao.getAllThanhVien();
                    thanhvienadapter = new thanhvienadapter(getActivity(), list);
                    rcv.setAdapter(thanhvienadapter);
                } else {
                    Toast.makeText(getActivity(), "khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
            }



    });


    }
}