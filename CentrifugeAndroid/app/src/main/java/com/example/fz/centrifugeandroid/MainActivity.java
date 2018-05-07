package com.example.fz.centrifugeandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import centrifuge.Centrifuge;
import centrifuge.Client;
//import centrifuge.Credentials;
import centrifuge.EventHub;
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

//        Credentials creds = Centrifuge.newCredentials(
//                "42", "1488055494", "",
//                "24d0aa4d7c679e45e151d268044723d07211c6a9465d0e35ee35303d13c5eeff"
//        );

        EventHub events = Centrifuge.newEventHub();
        ConnectHandler connectHandler = new AppConnectHandler(this);
        DisconnectHandler disconnectHandler = new AppDisconnectHandler(this);

        events.onConnect(connectHandler);
        events.onDisconnect(disconnectHandler);

        Client client = Centrifuge.new_(
                "ws://192.168.1.34:8000/connection/websocket",
                events,
                Centrifuge.defaultConfig()
        );
//        client.setCredentials(creds);

        try {
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.toString());
            return;
        }

        SubscriptionEventHub subEvents = Centrifuge.newSubscriptionEventHub();
        PublishHandler publishHandler = new AppPublishHandler(this);
        subEvents.onPublish(publishHandler);

        try {
            Subscription sub = client.subscribe("chat:index", subEvents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
