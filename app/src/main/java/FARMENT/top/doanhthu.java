package FARMENT.top;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quanbhph20506fptpolypnlib.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Dao.thongkedao;


public class doanhthu extends Fragment {
    Button btn_doanh_thu;
    EditText ed_doanh_thu_tu_ngay, ed_doanh_thu_den_ngay;
    TextView tv_doanh_thu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDay;

    public doanhthu() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doanhthu, container, false);
        return view;
    }
    @Override
    public void onViewCreated( View view,   Bundle savedInstanceState) {
        ed_doanh_thu_tu_ngay = view.findViewById(R.id.ed_doanh_thu_tu_ngay);
        ed_doanh_thu_den_ngay = view.findViewById(R.id.ed_doanh_thu_den_ngay);
        tv_doanh_thu = view.findViewById(R.id.tv_doanh_thu);
        btn_doanh_thu = view.findViewById(R.id.btn_doanh_thu);
        DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_doanh_thu_tu_ngay.setText(sdf.format(c.getTime()));
            }
        };
        DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_doanh_thu_den_ngay.setText(sdf.format(c.getTime()));
            }
        };
        ed_doanh_thu_tu_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0, mDateTuNgay, mYear, mMonth, mDay);
                d.show();
            }
        });

        ed_doanh_thu_den_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),
                        0, mDateDenNgay, mYear, mMonth, mDay);
                d.show();
            }
        });
        btn_doanh_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = ed_doanh_thu_tu_ngay.getText().toString();
                String denNgay = ed_doanh_thu_den_ngay.getText().toString();
                thongkedao thongKeDAO = new thongkedao(getActivity());
                tv_doanh_thu.setText(thongKeDAO.getDoanhThu(tuNgay, denNgay) + " vnđ");
            }
        });
    }
}