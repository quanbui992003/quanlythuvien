package FARMENT.themtaikhoan;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanbhph20506fptpolypnlib.R;
import com.google.android.material.snackbar.Snackbar;

import Dao.thuthudao;
import model.thuthu;


public class themtaikhoan extends Fragment {
EditText eduser,edhoten,edpass,edrepass;
Button btnluu,btnhuy;
thuthudao dao;

    public themtaikhoan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_themtaikhoan,container,false);
       eduser=view.findViewById(R.id.txtthemtk);
       edhoten=view.findViewById(R.id.txthotenthemtk);
       edpass=view.findViewById(R.id.txtmatkhau1);
       edrepass=view.findViewById(R.id.txtnhaplaimatkhauthem);
       btnluu=view.findViewById(R.id.btnluu);
       btnhuy=view.findViewById(R.id.btnhuy);


        btnhuy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               eduser.setText("");
               edhoten.setText("");
               edpass.setText("");
               edrepass.setText("");
           }

       });
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thuthu tt= new thuthu();
                tt.setMaTT(eduser.getText().toString());
                tt.setHoTen(edhoten.getText().toString());
                tt.setMatKhau(edpass.getText().toString());
                dao= new thuthudao(getContext());
                if(vadiate()>0){
                    if(dao.insert(tt)>0){

                        Snackbar.make(view, "Thêm thủ thư thành công!", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(ContextCompat.getColor(getActivity(), R.color.black))
                                .show();
                        Toast.makeText(getActivity(), "luu thanh cong", Toast.LENGTH_SHORT).show();
                        eduser.setText("");
                        edhoten.setText("");
                        edpass.setText("");
                        edrepass.setText("");
                    }
                }
            }
        });
        return view;
    }
    public int vadiate(){

       int check = 1;
        if(eduser.getText().length()==0 || edhoten.getText().length()==0||edpass.getText().length()==0||edrepass.
        getText().length()==0){
    Toast.makeText(getContext(), "ban phai dang ky day du", Toast.LENGTH_SHORT).show();
     check = -1;


}else{
    String pass=edpass.getText().toString();
    String repass= edrepass.getText().toString();
    if(!pass.equals(repass)){
        Toast.makeText(getContext(), "nhap mat khau khoon trung", Toast.LENGTH_SHORT).show();
        check=-1;
    }
}
return check;
    }
}