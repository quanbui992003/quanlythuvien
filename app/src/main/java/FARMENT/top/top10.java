package FARMENT.top;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanbhph20506fptpolypnlib.ADAPTER.thongkeadapter;
import com.example.quanbhph20506fptpolypnlib.R;

import java.util.ArrayList;
import java.util.List;

import Dao.thongkedao;
import model.top;


public class top10 extends Fragment {
private Context mcontext;
RecyclerView rcv;
thongkedao thongkedao;
thongkeadapter thongkeadapter;
List<top> list=new ArrayList<>();


    public top10() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext= context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_top10,container,false);

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rcv=view.findViewById(R.id.rcvthongke);
        LinearLayoutManager manager= new LinearLayoutManager(getContext());
        rcv.setLayoutManager(manager);
        thongkedao = new thongkedao(getActivity());
        list = (List<top>) thongkedao.getTop();
        thongkeadapter= new thongkeadapter(list, getActivity());
        rcv.setAdapter(thongkeadapter);

    }
}