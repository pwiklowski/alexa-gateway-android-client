package com.wiklosoft.alexagatewayclienttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wiklosoft.alexagatewayclient.AlexaGatewayClient;

public class MainActivity extends AppCompatActivity {
    AlexaGatewayClient client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //temp solution for simple tests
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                client = new AlexaGatewayClient();
                client.connect();
            }
        });
        t.start();


    }
}
