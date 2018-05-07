package com.example.fz.centrifugeandroid;

import android.widget.TextView;
import android.content.Context;
import android.app.Activity;
import centrifuge.Client;
import centrifuge.ConnectEvent;
import centrifuge.ConnectHandler;

public class AppConnectHandler implements ConnectHandler {
    protected MainActivity context;

    public AppConnectHandler(Context context) {
        this.context = (MainActivity) context;
    }

    @Override
    public void onConnect(Client client, final ConnectEvent event) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) ((Activity) context).findViewById(R.id.text);
                tv.setText("Connected with client ID " + event.getClientID());
            }
        });
    }
}
