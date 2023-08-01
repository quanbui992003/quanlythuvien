package com.example.quanbhph20506fptpolypnlib.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanbhph20506fptpolypnlib.R;

import java.util.ArrayList;
import java.util.List;

import Dao.thongkedao;
import model.top;

public class thongkeadapter extends RecyclerView.Adapter<thongkeadapter.viewHolder>{
List<top> list;
thongkedao thongkedao;
Context context;

    public thongkeadapter() {

    }

    public thongkeadapter(List<top> list, Context context) {
        this.list = list;
        this.context = context;
        thongkedao = new thongkedao(context);

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.danhsachthongke,null);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
     top top= list.get(position);
     holder.txtmatk.setText("tensach:"+top.getTenSach());
     holder.txtnumber.setText(String.valueOf("soluong:"+top.getSoLuong()));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
    TextView txtnumber,txtmatk;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtmatk=itemView.findViewById(R.id.masachtk);
            txtnumber=itemView.findViewById(R.id.namesachtk);
        }
    }

}
