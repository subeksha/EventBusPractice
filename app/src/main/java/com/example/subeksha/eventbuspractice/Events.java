package com.example.subeksha.eventbuspractice;

/**
 * Created by Subeksha on 2/19/17.
 */

public class Events {


    public static class FragmentToActivityMessage {
        private String message;
        public FragmentToActivityMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }


    public static class ActivityToFragmentMessage {
        private String message;
        public ActivityToFragmentMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }


    public static class ActivityToActivityMessage {
        private String message;
        public ActivityToActivityMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }
    }

}


