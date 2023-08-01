package com.example.quanbhph20506fptpolypnlib.ADAPTER;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanbhph20506fptpolypnlib.R;

import java.util.List;

import Dao.loaisachdao;
import model.loaisach;
import model.sach;
import model.thanhvien;

public class loaisachadapter extends RecyclerView.Adapter<loaisachadapter.viewholder> {

private Context context;
List<loaisach> list;
loaisachdao loaisachdao;

    public loaisachadapter(FragmentActivity activity, List<sach> listsach) {
    }

    public loaisachadapter(Context context, List<loaisach> list ){
        this.context = context;
        this.list = list;
        loaisachdao = new loaisachdao(context);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.itemloaisach,parent,false);
        return new viewholder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
           loaisach loaisach= list.get(position);
           holder.edmaloaisach.setText(String.valueOf(loaisach.getMaloai()));
           holder.edtenloaisach.setText(loaisach.getTenloai());
//           holder.edgiathue.setText(String.valueOf(loaisach.getGiathue()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class viewholder extends RecyclerView.ViewHolder {
        TextView edmaloaisach,edtenloaisach,edgiathue;
        ImageView edxoa,edthem;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            edmaloaisach=itemView.findViewById(R.id.dsmaloaisach);
            edtenloaisach=itemView.findViewById(R.id.dstenloaisach);
//            edgiathue=itemView.findViewById(R.id.dsgiathue);
            edxoa=itemView.findViewById(R.id.deleteloaisach);
            edthem=itemView.findViewById(R.id.uploaisach);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        loaisach ls= list.get(position);
        holder.edxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loaisachdao.delete(ls.getMaloai())){
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    list.addAll(loaisachdao.getAllLoaiSach());
                    notifyDataSetChanged();
                }
            }
        });
        holder.edthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openupdate(ls);
            }
        });
    }
    public void openupdate(loaisach ls){
        AlertDialog.Builder builder=new  AlertDialog.Builder(context);
        LayoutInflater inflater= ((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.updateloaisach,null);
        builder.setView(view);
        Dialog dialog=builder.create();
        dialog.show();

        EditText txtmatv=view.findViewById(R.id.maloaisach);
        EditText txttenloaisach=view.findViewById(R.id.tenloasach) ;
//        EditText txtgiathue=view.findViewById(R.id.updadegiathue) ;
        Button txtup=view.findViewById(R.id.themls);
        Button txthuy=view.findViewById(R.id.huyls);

//        txtmatv.setText(tv.getMaTv());
        txttenloaisach.setText(ls.getTenloai());
//        txtgiathue.setText(String.valueOf(ls.getGiathue()));

        txtup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                 thanhvien tv= new thanhvien();
//                tv.setMaTv(Integer.parseInt(txtmatv.getText().toString()));
                ls.setTenloai(txttenloaisach.getText().toString());
//                ls.setGiathue(Integer.parseInt(txtgiathue.getText().toString()));
                if(loaisachdao.update(ls)){
                    Toast.makeText(context, "thanh cong", Toast.LENGTH_SHORT).show();
                    list.clear();
                    dialog.dismiss();
                    list.addAll(loaisachdao.getAllLoaiSach());
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context, "k thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
