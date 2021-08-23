package com.whytecreations.ecom.ui.main.login;


import com.whytecreations.ecom.ui.base.BaseNavigator;

public interface LoginNavigator extends BaseNavigator {
    void onChangeLanguage();
    void login();
    void handleSuccess(String msg);
}
