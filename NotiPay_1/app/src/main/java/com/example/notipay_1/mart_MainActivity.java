package com.example.notipay_1;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class mart_MainActivity extends AppCompatActivity{

    // FrameLayout에 각 메뉴의 Fragment를 바꿔 줌
    private FragmentManager fragmentManager = getSupportFragmentManager();
    // 4개의 메뉴에 들어갈 Fragment들
    private mart_Menu1Fragment menu1Fragment = new mart_Menu1Fragment();
    private mart_Menu2Fragment menu2Fragment = new mart_Menu2Fragment();
    private mart_Menu3Fragment menu3Fragment = new mart_Menu3Fragment();
    private mart_Menu4Fragment menu4Fragment = new mart_Menu4Fragment();

    mart_Server_chat server_chat;

    String barcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mart_activity_main);
        server_chat = new mart_Server_chat();
        server_chat.connser();

        try {
            Intent intent = getIntent();
            barcode = intent.getExtras().getString("barcode");
            if (barcode != null) {
                Toast.makeText(this,"바코드받음" + barcode,Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        //프래그먼트------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        // 첫 화면 지정
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, menu1Fragment).commitAllowingStateLoss();

        // bottomNavigationView의 아이템이 선택될 때 호출될 리스너 등록
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_menu1: {
                        transaction.replace(R.id.frame_layout, menu1Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu2: {
                        transaction.replace(R.id.frame_layout, menu2Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu3: {
                        transaction.replace(R.id.frame_layout, menu3Fragment).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.navigation_menu4: {
                        transaction.replace(R.id.frame_layout, menu4Fragment).commitAllowingStateLoss();
                        break;
                }
            }
            return true;
            }
        });
        //프래그먼트-----------------------------
    }
}

