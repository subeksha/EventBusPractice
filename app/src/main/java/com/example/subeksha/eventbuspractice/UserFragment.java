package com.example.subeksha.eventbuspractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Subeksha on 2/19/17.
 */

public class UserFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // register the event to listen.
        EBus.getBus().register(this);
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setClickListener(view);
    }

    public void setClickListener(final View view) {
        Button btnSubmit = (Button) view.findViewById(R.id.submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etMessage = (EditText) view.findViewById(R.id.editText);

                Events.FragmentToActivityMessage fragmentToActivityMessageEvent =
                        new Events.FragmentToActivityMessage(
                                String.valueOf(etMessage.getText()));
                EBus.getBus().post(fragmentToActivityMessageEvent);
            }
        });
    }

    @Subscribe
    public void getMessage(Events.ActivityToFragmentMessage activityFragmentMessage) {
        TextView messageView = (TextView) getView().findViewById(R.id.message);
        messageView.setText(
                getString(R.string.message_received) +
                        " " + activityFragmentMessage.getMessage());

        Toast.makeText(getActivity(),
                getString(R.string.message_fragment) +
                        " " + activityFragmentMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EBus.getBus().unregister(this);
    }


}
