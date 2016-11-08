package com.cnjaj.myapplication.pattern.state;

/**
 * Created by Administrator on 2016/11/8.
 */
class StateManager {
    private static StateManager INSTANCE = new StateManager();
    private UserState mUserState;

    private StateManager() {
        mUserState = new LogoutState();
    }

    static UserState getUserState() {
        return INSTANCE.mUserState;
    }

    static void setUserState(UserState userState) {
        INSTANCE.mUserState = userState;
    }
}
