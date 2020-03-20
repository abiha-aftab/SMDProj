package com.example.smd_po_module.ui.login;

import android.text.TextUtils;

public class LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    public void login(final String username, final String password, final OnLoginFinishedListener listener) {
        if (TextUtils.isEmpty(username)) {
            listener.onUsernameError();
        } else if (TextUtils.isEmpty(password)) {
            listener.onPasswordError();
        } else {
            listener.onSuccess();
        }
    }
}
