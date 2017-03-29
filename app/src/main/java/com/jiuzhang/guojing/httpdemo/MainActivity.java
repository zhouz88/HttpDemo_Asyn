package com.jiuzhang.guojing.httpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://www.google.com")
                        .build();

                OkHttpClient client = new OkHttpClient();
                try {
                    Response response = client.newCall(request).execute();
                    final String responseString = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = (TextView) findViewById(R.id.text_view);
                            textView.setText(responseString);
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
