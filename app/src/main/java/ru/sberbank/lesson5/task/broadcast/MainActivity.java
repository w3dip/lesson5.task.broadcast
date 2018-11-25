package ru.sberbank.lesson5.task.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static ru.sberbank.lesson5.task.broadcast.ExampleIntentService.ACTION_CHANGE_STATE;
import static ru.sberbank.lesson5.task.broadcast.ExampleIntentService.ACTION_CHANGE_STATE_FILTER;

public class MainActivity extends Activity {
    private LocalBroadcastManager localBroadcastManager;
    private ExampleBroadcastReceiver exampleBroadcastReceiver;
    private IntentFilter intentFilter;
    private TextView stateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateView = findViewById(R.id.currentState);
        intentFilter = new IntentFilter(ACTION_CHANGE_STATE_FILTER);
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        exampleBroadcastReceiver = new ExampleBroadcastReceiver(new ViewCallbackImpl());

        Button button = findViewById(R.id.changeState);
        button.setOnClickListener(changeStateListener);
    }

    private View.OnClickListener changeStateListener = new View.OnClickListener() {
        public void onClick(View v) {
            startService(new Intent(ACTION_CHANGE_STATE, null, MainActivity.this,
                    ExampleIntentService.class));
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        localBroadcastManager.registerReceiver(exampleBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        localBroadcastManager.unregisterReceiver(exampleBroadcastReceiver);
    }

    public class ViewCallbackImpl implements ViewCallback {
        @Override
        public void onStateChanged(String state) {
            stateView.setText(state);
        }
    }
}
