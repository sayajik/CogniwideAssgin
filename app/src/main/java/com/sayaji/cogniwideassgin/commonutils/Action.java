package com.sayaji.cogniwideassgin.commonutils;

public class Action {
    public static final int SHOW_MOVIE_LIST = 0;
    public static final int SHOW_INVALID_PASSWARD_OR_LOGIN = 1;
    private final int mAction;

    public Action(int action) {
        mAction = action;
    }

    public int getValue() {
        return mAction;
    }
}
