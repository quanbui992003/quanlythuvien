package FARMENT.doimatkau;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanbhph20506fptpolypnlib.R;

import Dao.thuthudao;
import model.thuthu;


public class doimatkhau extends Fragment {

    EditText edpassold, edPass, edrePass;
    Button btndoi, btnhuy;
    thuthudao dao;

    public doimatkhau() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doimatkhau, container, false);
        edpassold = view.findViewById(R.id.txtdoimatkhau);
        edPass = view.findViewById(R.id.txtdoimatkhaumoi);
        edrePass = view.findViewById(R.id.txtdoinhaplaimk);
        btndoi = view.findViewById(R.id.btnDoi);
        btnhuy = view.findViewById(R.id.btnhuydoi);
        dao = new thuthudao(getContext());

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edpassold.setText("");
                edPass.setText("");
                edrePass.setText("");
            }
        });

        btndoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");
                if (validate()>0){
                    thuthu tt = dao.getIdTT(user);
                    tt.setMatKhau(edPass.getText().toString());
                    dao.update(tt);
                    if(dao.update(tt)>0){
                        Toast.makeText(getContext(), "thay doi mat khau", Toast.LENGTH_SHORT).show();
                        edpassold.setText("");
                        edPass.setText("");
                        edrePass.setText("");
                    }
                      else {
                        Toast.makeText(getContext(), "thay doi that bai", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        return view;
    }

    public int validate() {
        int check = 1;
        if (edpassold.getText().length() == 0 || edPass.getText().length() == 0 || edrePass.getText().length() == 0) {
            Toast.makeText(getContext(), "ban phai nhap day du thong tin", Toast.LENGTH_SHORT).show();
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passold= pref.getString("PASSWORD","");
            String pass=edPass.getText().toString();
            String repass=edrePass.getText().toString();
            if(!passold.equals(edpassold.getText().toString())){
                Toast.makeText(getContext(), "ma k cu sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!pass.equals(repass)){
                Toast.makeText(getContext(), "mat khau k trung khop", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }

}
