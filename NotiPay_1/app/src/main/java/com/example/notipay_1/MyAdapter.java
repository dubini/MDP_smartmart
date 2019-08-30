package com.example.notipay_1;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends LinearLayout {

    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;
    Button plus,minus;

    public MyAdapter(Context context) {
        super(context);
        init(context);
    }

    public MyAdapter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_custom, this, true);

        textView = (TextView) findViewById(R.id.name);
        textView2 = (TextView) findViewById(R.id.price);
        textView3 = (TextView) findViewById(R.id.account);
        imageView = (ImageView) findViewById(R.id.icon);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);


    }

    public void setName(String name) { textView.setText(name); }

    public void setPrice(int price) {
        textView2.setText(String.valueOf(price));
    }

    public void setAccount(int account) {
        textView3.setText(String.valueOf(account));
    }

    public void setImage(int icon) {
        imageView.setImageResource(icon);
    }
}