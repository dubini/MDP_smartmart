package com.example.notipay_1;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class mart_ShoppingList extends AppCompatActivity {
    String getData;
    MyAdapter adapter; //어댑터 변수

    ListView listView;

    mart_Server_chat server_chat;
    String receive;
    IntentIntegrator brscan;
    public class MyAdapter extends BaseAdapter {
        // 아이템들을 담기 위한 어레이
        private ArrayList<mart_MyItem> mItems = new ArrayList<>();

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public mart_MyItem getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(mart_MyItem item) {
            mItems.add(item);
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup viewGroup) {
            mart_MyItemView view = new mart_MyItemView(getApplicationContext());

            mart_MyItem item = mItems.get(position);
            view.setName(item.getName());
            view.setPrice(item.getPrice());
            view.setAccount(item.getAccount());
            view.setImage(item.getIcon());

            return view;
        }
    }

    void show(){
        final EditText et = new EditText(getApplicationContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //타이틀설정
        builder.setTitle("PIN 입력").setMessage("PIN번호를 입력하세요").setView(et);
        //내용설정
        builder.setPositiveButton("전송", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"전송완료",Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"취소완료",Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mart_activity_shopping_list);

        ImageButton home = (ImageButton) findViewById(R.id.home);
        Button pay = (Button) findViewById(R.id.pay);
        FloatingActionButton fab =  (FloatingActionButton) findViewById(R.id.FAB);

        listView = (ListView) findViewById(R.id.listview);

        adapter = new MyAdapter();   // 어댑터 등록

        server_chat = new mart_Server_chat();
        server_chat.connser();
        brscan = new IntentIntegrator(this);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });

        Intent intent = getIntent();
        String data = intent.getStringExtra("barcode");

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brscan.setPrompt("scanning...."); //상태 scanning
                brscan.setOrientationLocked(false);  //
                brscan.initiateScan(); //initiateScan() 함수 호출 (바코드스캐너)
            }
        });

        if(data == "a"){
            Log.d(data, "aaaaaaa");
            fab.performClick();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //이 함수를 호출하면 requestcode, resultcode, data를 반환
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qr,barcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show(); //toast 메세지
            } else {
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    getData = result.getContents();//바코드 값 저장
                    String bar_code = String.valueOf(getData); //스트링 형으로 변환
                    if (bar_code.equals("12345670")) {
                        adapter.addItem(new mart_MyItem("당근", 1500, 1, R.drawable.mart_carrot));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("23456716")) {
                        adapter.addItem(new mart_MyItem("사과", 100, 1, R.drawable.mart_apple));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("34567838")) {
                        adapter.addItem(new mart_MyItem("바나나", 4000, 1, R.drawable.mart_banana));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("47723214")) {
                        adapter.addItem(new mart_MyItem("옥수수", 1000, 1, R.drawable.mart_corn));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("61234215")) {
                        adapter.addItem(new mart_MyItem("포도", 2000, 1, R.drawable.mart_grapes));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("45671111")) {
                        adapter.addItem(new mart_MyItem("멜론", 6000, 1, R.drawable.mart_melon));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("14121418")) {
                        adapter.addItem(new mart_MyItem("복숭아", 800, 1, R.drawable.mart_peach));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("13461515")) {
                        adapter.addItem(new mart_MyItem("싱싱한 오징어", 5000, 1, R.drawable.mart_squid));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("35612124")) {
                        adapter.addItem(new mart_MyItem("딸기", 4000, 1, R.drawable.mart_strawberry));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("51231422")) {
                        adapter.addItem(new mart_MyItem("즉석떡볶이", 4850, 1, R.drawable.mart_tteokbokki));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("25231137")) {
                        adapter.addItem(new mart_MyItem("수박", 7000, 1, R.drawable.mart_watermelon));
                        listView.setAdapter(adapter);
                    } else if (bar_code.equals("45121234")) {
                        adapter.addItem(new mart_MyItem("한우 한 근", 700, 1, R.drawable.mart_hanwoo));
                        listView.setAdapter(adapter);
                    } else {
                        Toast.makeText(this, "없는 바코드", Toast.LENGTH_SHORT).show();
                    }

                    server_chat.senddata(bar_code); //server_chat 의 함수 senddata 에 변수 bar_code를 넣어 실행
                    receive = String.valueOf(mart_Server_chat.receive);  // Server_chat의 receive 데이터를 스트링형으로 변환
                }
            }
        }
    }
}