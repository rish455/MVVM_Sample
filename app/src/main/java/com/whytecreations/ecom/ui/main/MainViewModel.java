package com.whytecreations.ecom.ui.main;

import com.whytecreations.ecom.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel<MainNavigator> {
    public void onNavBack() {
        getNavigator().goBack();
    }
}
