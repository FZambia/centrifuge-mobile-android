package com.example.fz.centrifugeandroid;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import centrifuge.PublishEvent;
import centrifuge.PublishHandler;
import centrifuge.Subscription;

public class AppPublishHandler implements PublishHandler {
    protected MainActivity context;

    public AppPublishHandler(Context context) {
        this.context = (MainActivity) context;
    }

    @Override
    public void onPublish(final Subscription sub, final PublishEvent event) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) ((Activity) context).findViewById(R.id.text);
                String data = new String(event.getData());
                tv.setText("New publication from channel " + sub.channel() + ": " + data);
            }
        });
    }
}
