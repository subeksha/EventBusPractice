package com.example.subeksha.eventbuspractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragment();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EBus.getBus().register(this);
    }

    private void addFragment() {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new UserFragment())
                .commit();
    }

    public void sendMessageToFragment(View view) {
        EditText etMessage = (EditText) findViewById(R.id.activityData);
        Events.ActivityToFragmentMessage activityToFragmentMessageEvent =
                new Events.ActivityToFragmentMessage(String.valueOf(etMessage.getText()));

        EBus.getBus().post(activityToFragmentMessageEvent);
    }

    @Subscribe
    public void getMessage(Events.FragmentToActivityMessage fragmentActivityMessage) {
        TextView messageView = (TextView) findViewById(R.id.message);
        messageView.setText(getString(R.string.message_received) + " " + fragmentActivityMessage.getMessage());

        Toast.makeText(getApplicationContext(),
                getString(R.string.message_main_activity) + " " + fragmentActivityMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EBus.getBus().unregister(this);
    }
}

