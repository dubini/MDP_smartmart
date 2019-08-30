package com.example.notipay_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BarScan extends AppCompatActivity {

    String getData;
    ItemAdapter adapter; //어댑터 변수
    IntentIntegrator brscan;

    ListView lv_1;
    Server_chat server_chat;
    String receive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        lv_1 = (ListView) findViewById(R.id.listview_1);

        server_chat = new Server_chat();
        server_chat.connser();

        adapter = new ItemAdapter();   // 어댑터 등록
        brscan = new IntentIntegrator(this);

        brscan.setPrompt("scanning");
        brscan.initiateScan(); //initiateScan() 함수 호출 (바코드스캐너)

        lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                MyItem item = (MyItem) adapter.getItem(position);
                Toast.makeText(getApplicationContext(), "선택 : " + item.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }

    class ItemAdapter extends BaseAdapter {


        // 아이템들을 담기 위한 어레이
        private ArrayList<MyItem> mItems = new ArrayList<MyItem>();

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        public void addItem(MyItem item) { mItems.add(item);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            MyAdapter view = new MyAdapter(getApplicationContext());

            MyItem item = mItems.get(position);
            view.setName(item.getName());
            view.setPrice(item.getPrice());
            view.setAccount(item.getAccount());
            view.setImage(item.getIcon());
            final TextView tv_account = (TextView) convertView.findViewById(R.id.account) ;
            Button bt_plus = (Button)convertView.findViewById(R.id.plus);
            Button bt_minus = (Button)convertView.findViewById(R.id.minus);

            //  bt_minus 버튼 클릭시 이벤트
            bt_plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int account_i = mItems.get(position).getAccount();  // position 자리의 수량을 account_i 에 저장
                    account_i++;
                    tv_account.setText(String.valueOf(account_i));
                    mItems.get(position).getAccount();  // position 자리의 수량을 받아옴
                    mItems.get(position).setAccount(account_i); // position 자리의 수량을 account_i 로 설정

                }
            });

            bt_minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int account_i = mItems.get(position).getAccount();
                    if(account_i>=1){
                        account_i--;
                        tv_account.setText(String.valueOf(account_i));
                        mItems.get(position).setAccount(account_i);
                    }
                }
            });

            return view;


        }
    }

    //바코드 인식
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //이 함수를 호출하면 requestcode, resultcode, data를 반환
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //qr,barcode 가 없으면
            if (result.getContents() == null) {
                Toast.makeText(BarScan.this, "취소", Toast.LENGTH_SHORT).show(); //toast 메세지
                Intent intent = new Intent(BarScan.this, MainActivity.class);
                startActivity(intent);
            } else {
                try {
                    JSONObject obj = new JSONObject(result.getContents());
                } catch (JSONException e) {
                    e.printStackTrace();
                    getData = result.getContents(); //바코드 값 저장
                    String bar_code = String.valueOf(getData); //스트링 형으로 변환
                    if(bar_code.equals("12345670")){
                        adapter.addItem(new MyItem("당근", 1500, 1, R.drawable.carrot));
                        lv_1.setAdapter(adapter);
                    }else if(bar_code.equals("23456716")) {
                        adapter.addItem(new MyItem("사과", 100, 1, R.drawable.apple));
                        lv_1.setAdapter(adapter);
                    }else{
                        Toast.makeText(BarScan.this,"없는 바코드",Toast.LENGTH_SHORT).show();
                    }
                    server_chat.senddata(bar_code); //server_chat 의 함수 senddata 에 변수 bar_code를 넣어 실행
                    receive = String.valueOf(Server_chat.receive);  // Server_chat의 receive 데이터를 스트링형으로 변환
                    Intent intent = new Intent(BarScan.this, ShoppingList.class);   // ShoppingList로 화면전환
                    intent.putExtra("barcode",intent); // barcode 에 receive 를 저장해서 데이터를 ShoppingList에 보냄
                    startActivity(intent);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data); //onActivityResult 에서 requestCode,resultCode,data를 받아옴
        }
    }
}

