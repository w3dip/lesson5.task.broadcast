package ru.sberbank.lesson5.task.broadcast;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class ExampleIntentService extends IntentService {
    public static final String ACTION_CHANGE_STATE_FILTER = "ru.sberbank.lesson5.task.broadcast.action.CHANGE_STATE_FILTER";
    public static final String ACTION_CHANGE_STATE = "ru.sberbank.lesson5.task.broadcast.action.CHANGE_STATE";
    public static final String EXTRA_PARAM_STATE = "ru.sberbank.lesson5.task.broadcast.extra.PARAM_STATE";

    private LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);
    private StateManager stateManager = StateManager.getInstance();

    public ExampleIntentService() {
        super("ExampleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CHANGE_STATE.equals(action)) {
                intent.setAction(ACTION_CHANGE_STATE_FILTER);
                stateManager.changeState();
                localBroadcastManager.sendBroadcast(intent.putExtra(EXTRA_PARAM_STATE, stateManager.getState()));
            }
        }
    }
}
