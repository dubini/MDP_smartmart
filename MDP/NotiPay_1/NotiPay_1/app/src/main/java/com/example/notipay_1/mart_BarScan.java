package com.example.notipay_1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Inet4Address;
import java.util.ArrayList;

import static android.os.StrictMode.setThreadPolicy;

public class mart_BarScan extends AppCompatActivity {
    IntentIntegrator brscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        brscan = new IntentIntegrator(this);

        brscan.setPrompt("scanning");
        brscan.initiateScan(); //initiateScan() 함수 호출 (바코드스캐너)

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);
    }

    //바코드 인식
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //이 함수를 호출하면 requestcode, resultcode, data를 반환
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qr,barcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(this, "취소", Toast.LENGTH_SHORT).show(); //toast 메세지
                finish();
            } else {
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Intent intent = new Intent(this,mart_ShoppingList.class);   // ShoppingList로 화면전환
                    intent.putExtra("result",result.getContents()); // barcode 에 receive 를 저장해서 데이터를 ShoppingList에 보냄
                    startActivity(intent);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data); //onActivityResult 에서 requestCode,resultCode,data를 받아옴
        }
    }

}