package com.example.subeksha.eventbuspractice;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Subeksha on 2/19/17.
 */

public class EBus {
    private static EventBus sBus;
    public static EventBus getBus() {
        if (sBus == null)
            sBus = EventBus.getDefault();
        return sBus;
    }
}
