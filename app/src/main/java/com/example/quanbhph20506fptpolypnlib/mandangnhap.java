package com.example.quanbhph20506fptpolypnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
//import android.view.View;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import Dao.thuthudao;

public class mandangnhap extends AppCompatActivity {
Button dangnhap,huy;
EditText txtname,txtmk;
CheckBox checkBox;
thuthudao dao;
MainActivity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mandangnhap);
        dangnhap=findViewById(R.id.dangnhap);
        huy=findViewById(R.id.huy);
        txtname=findViewById(R.id.txtname);
        txtmk=findViewById(R.id.txtmk);
        checkBox=findViewById(R.id.btncheck);
       dao= new thuthudao(this);
        SharedPreferences pref= getSharedPreferences("USER_FILE",MODE_PRIVATE);
        txtname.setText(pref.getString("USENAME",""));
        txtmk.setText(pref.getString("PASSWORD",""));
        checkBox.setChecked(pref.getBoolean("REMEMBER",false));

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtname.setText("");
                txtmk.setText("");
                Toast.makeText(mandangnhap.this, "da huy dang nhap", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void  login(){
        String txttentk=txtname.getText().toString();
        String txtmktk=txtmk.getText().toString();
        if(txttentk.equals("admin")||txtmktk.equals("123")) {
            Toast.makeText(getApplicationContext(), "dang nhap thanh cong", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        if(txttentk.isEmpty()||txtmktk.isEmpty()){
            Toast.makeText(getApplicationContext(), "ten dang nhap va mat khau khong duoc de trong", Toast.LENGTH_SHORT).show();
        }
        else{
            if(dao.checklogin(txttentk,txtmktk)>0){
                Toast.makeText(getApplicationContext(), "login thanh cong", Toast.LENGTH_SHORT).show();
                remenberUser(txttentk,txtmktk,checkBox.isChecked());
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user",txttentk);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), "tan dang nhap va mk khong dung", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void remenberUser(String txttentk, String txtmktk, boolean checked) {
        SharedPreferences pref= getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=pref.edit();
        if(!checked){
            editor.clear();
        }else {
            editor.putString("USENAME",txtmktk);
            editor.putString("PASSWORD",txtmktk);
            editor.putBoolean("REMEMBER",checked);
        }
        editor.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}