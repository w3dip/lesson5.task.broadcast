package ru.sberbank.lesson5.task.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static ru.sberbank.lesson5.task.broadcast.ExampleIntentService.EXTRA_PARAM_STATE;

public class ExampleBroadcastReceiver extends BroadcastReceiver {

    private ViewCallback callback;

    public ExampleBroadcastReceiver(ViewCallback callback) {
        this.callback = callback;
    }

    public ExampleBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        callback.onStateChanged(intent.getStringExtra(EXTRA_PARAM_STATE));
    }
}
