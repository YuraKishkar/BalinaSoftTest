package com.example.liban.balinasofttest.mvp.view;

/**
 * Created by liban on 24.08.2018.
 */

public interface RegisterView {
    void onClickToLogin();
    void onClickRegister();
    void onCheckPasswordRegister(boolean password);
    void onCheckEmailRegister(boolean email);
}
