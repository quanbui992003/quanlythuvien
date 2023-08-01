package com.example.quanbhph20506fptpolypnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import Dao.thuthudao;
import FARMENT.doimatkau.doimatkhau;
import FARMENT.loaisach.tabloaisach;
import FARMENT.quanlyphieumuon.tabphieumuon;
import FARMENT.sach.tabsach;
import FARMENT.thanhvien.tabthanhvien;
import FARMENT.themtaikhoan.themtaikhoan;
import FARMENT.top.doanhthu;
import FARMENT.top.top10;
import model.thuthu;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    View mHeaderView;
    TextView tvUsername;
    thuthudao thuThuDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout =findViewById(R.id.draw);
        toolbar =findViewById(R.id.toobar);
        navigationView=findViewById(R.id.nav);
        setSupportActionBar(toolbar);

//        mHeaderView = navigationView.getHeaderView(0);
//        tvUsername = mHeaderView.findViewById(R.id.txtthemtk);
//        Intent intent = getIntent();
//        String user = intent.getStringExtra("user");
//        thuThuDAO = new thuthudao(this);
//        thuthu thuThu = thuThuDAO.getIdTT(user);
//        String username = thuThu.getHoTen();
//        if(!user.equalsIgnoreCase("admin")) {
//            navigationView.getMenu().findItem(R.id.themnguoidung).setVisible(false);
//        }
        ActionBarDrawerToggle actionBarDrawerToggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigationddrawer_open,R.string.navigationddrawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.quanly:
                        tabphieumuon tabpm= new tabphieumuon();
                        replaceFrg(tabpm);
                        break;
                    case R.id.quanlyloaisach:
                        tabloaisach tabls = new tabloaisach();
                        replaceFrg(tabls);
                        break;
                    case R.id.quanlysach:
                        tabsach quanlysach= new tabsach();
                        replaceFrg(quanlysach);
                        break;
                    case R.id.quanlythanhvien:
                        tabthanhvien quanlytv= new tabthanhvien();
                        replaceFrg(quanlytv);
                        break;
                    case R.id.thongke:

                        break;
                    case R.id.top10:
                        top10 t= new top10();
                        replaceFrg(t);
                        break;
                    case R.id.doanhthu:
                        doanhthu dt= new doanhthu();
                        replaceFrg(dt);
                        break;
                    case R.id.nguoidung:
                        break;
                    case R.id.themnguoidung:
                        themtaikhoan ttk= new themtaikhoan();
                        replaceFrg(ttk);
                        break;

                    case R.id.doimatkhau:
                        doimatkhau dmk= new doimatkhau();
                        replaceFrg(dmk);
                        break;
                    case R.id.dangxuat:
                        Intent intent= new Intent(MainActivity.this,mandangnhap.class);
                        startActivity(intent);
                        break;
                }
//                  hideItem();
                toolbar.setTitle(item.getTitle());
                return true;
            }

            private void replaceFrg(tabthanhvien quanlytv) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,quanlytv).commit();
            }

            private void replaceFrg(tabsach quanlysach) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,quanlysach).commit();
            }
            private void replaceFrg(tabphieumuon quanlyphieumuon) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,quanlyphieumuon).commit();
            }
            private void replaceFrg(tabloaisach qlloaisach) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,qlloaisach).commit();
            }
            private void replaceFrg(doimatkhau dmk) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,dmk).commit();
            }
            private void replaceFrg(themtaikhoan ttk) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,ttk).commit();

            }
            private void replaceFrg(top10 t) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,t).commit();
            }
            private void replaceFrg(doanhthu dt) {
                FragmentManager fm= getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.Framelayout,dt).commit();
            }
        });
    }
    private void hideItem(){
        Menu menu= navigationView.getMenu();
        menu.findItem(R.id.themnguoidung).setVisible(false);
        menu.findItem(R.id.doanhthu).setVisible(false);
    }

}