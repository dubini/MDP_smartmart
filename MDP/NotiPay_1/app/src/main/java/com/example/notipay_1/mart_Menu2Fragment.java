package com.example.notipay_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class mart_Menu2Fragment extends Fragment {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editsearch;        // 검색어를 입력할 Input 창
    private mart_BarScan.MyAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View jinsu = inflater.inflate(R.layout.mart_fragment_menu2, container, false);

        FloatingActionButton fab = (FloatingActionButton)jinsu.findViewById(R.id.FAB);
        Button btn = (Button)jinsu.findViewById(R.id.find);
        listView = (ListView)jinsu.findViewById(R.id.listfind);
        editsearch = (EditText)jinsu.findViewById(R.id.editsearch);

        // 리스트를 생성한다.
        list = new ArrayList<String>();

        settingList();

        arrayList = new ArrayList<String>();
        arrayList.addAll(list);

        //adapter = new mart_BarScan.MyAdapter(list,this);

        listView.setAdapter(adapter);

        editsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = editsearch.getText().toString();
                search(text);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), mart_BarScan.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return jinsu;

    }

    private void search(String text) {
        list.clear();

        if(text.length() == 0){
            list.addAll(arrayList);
        }
        else{
            for(int i =0;i<arrayList.size();i++){
                if(arrayList.get(i).toLowerCase().contains(text)){
                    list.add(arrayList.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    private  void settingList(){
        list.add("당근");
        list.add("딸기");
        list.add("떡볶이");
        list.add("멜론");
        list.add("바나나");
        list.add("복숭아");
        list.add("사과");
        list.add("수박");
        list.add("오징어");
        list.add("옥수수");
        list.add("포도");
        list.add("한우");
    }
}