package com.whytecreations.ecom.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.whytecreations.ecom.BR;
import com.whytecreations.ecom.R;
import com.whytecreations.ecom.databinding.ActivityMainBinding;
import com.whytecreations.ecom.ui.base.BaseActivity;
import com.whytecreations.ecom.ui.main.login.LoginFragment;
import com.whytecreations.ecom.utils.CommonUtils;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements MainNavigator{

    private MainViewModel mViewModel;
    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtils.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, LoginFragment.newInstance(), LoginFragment.TAG, true);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return mViewModel;
    }

    @Override
    public void goBack() {

    }

    @Override
    public void replaceFragment(Fragment fragment, String tag) {
        CommonUtils.replaceFragment(getSupportFragmentManager(), R.id.fragment_container, fragment, tag, false);
    }

    @Override
    public void handleProgress(boolean isShow) {
        if (isShow)
            showProgressDialog(this);
        else dismissProgressDialog();
    }
}