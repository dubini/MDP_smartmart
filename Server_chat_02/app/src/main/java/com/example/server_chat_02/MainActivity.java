package com.example.server_chat_02;

import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import static android.os.StrictMode.setThreadPolicy;

public class MainActivity extends AppCompatActivity {

    JSONArray peoples = null;


    String IP = "192.168.0.3";
    int PORT = 8201;

    Button conbt, sendbt;
    EditText sendms;
    TextView textData;

    SocketChannel socketChannel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conbt = (Button)findViewById(R.id.conbt);
        sendbt = (Button)findViewById(R.id.sendbt);
        sendms = (EditText)findViewById(R.id.sendms);
        textData = (TextView)findViewById(R.id.textData);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        setThreadPolicy(policy);

        conbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    socketChannel = SocketChannel.open(); // 객체 생성
                    socketChannel.configureBlocking(true); //
                    socketChannel.connect(new InetSocketAddress(IP, PORT));
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        sendbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendt =sendms.getText().toString();

                sendms.setText(null);

                try {
                    ByteBuffer buffer = null;
                    Charset charset = Charset.forName("UTF-8");
                    buffer = charset.encode(sendt);

                    socketChannel.write(buffer);

                    buffer = ByteBuffer.allocate(100); //100 자리 byte 만큼 자리확보
                    int len = socketChannel.read(buffer); //버퍼에 얼마나 많은 byte 가 기록됐는가
                    buffer.flip();

                    String recive = charset.decode(buffer).toString();
                    JSONObject jsonObject = new JSONObject(recive);
                    JSONObject c = peoples.getJSONObject(0);
                    String status = c.getString("message :");
                    textData.setText(status);

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }
    public void finish() {
        if(socketChannel.isOpen()){
            try {
                socketChannel.close();
                Toast.makeText(this, "소켓을 닫았습니다.", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.finish();
    }

}


