package com.example.fz.centrifugeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import centrifuge.Centrifuge;
import centrifuge.Client;
import centrifuge.DisconnectHandler;
import centrifuge.ConnectHandler;
import centrifuge.PublishHandler;
import centrifuge.Subscription;
import centrifuge.SubscriptionEventHub;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.text);

        ConnectHandler connectHandler = new AppConnectHandler(this);
        DisconnectHandler disconnectHandler = new AppDisconnectHandler(this);

        Client client = Centrifuge.new_(
                "ws://192.168.1.35:8000/connection/websocket",
                Centrifuge.defaultConfig()
        );
//        client.setToken("???");

        client.onConnect(connectHandler);
        client.onDisconnect(disconnectHandler);

        try {
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.toString());
            return;
        }

        Subscription sub;
        try {
            sub = client.newSubscription("chat:index");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        PublishHandler publishHandler = new AppPublishHandler(this);
        sub.onPublish(publishHandler);

        try {
            sub.subscribe();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
