package com.whytecreations.ecom.ui.main.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.whytecreations.ecom.BR;
import com.whytecreations.ecom.R;
import com.whytecreations.ecom.databinding.FragmentLoginBinding;
import com.whytecreations.ecom.ui.base.BaseFragment;
import com.whytecreations.ecom.ui.main.product_list.ProductListFragment;
import com.whytecreations.ecom.utils.CommonUtils;
import com.whytecreations.ecom.utils.Constants;

public class LoginFragment extends BaseFragment<FragmentLoginBinding, LoginViewModel>
        implements LoginNavigator {

    public static final String TAG = LoginFragment.class.getSimpleName();
    private LoginViewModel mViewModel;
    private FragmentLoginBinding mBinding;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
        initAnim();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return mViewModel;
    }

    private void initAnim() {
        CommonUtils.animate(getContext(), R.anim.item_anim_fall_up, mBinding.btnLogin);
    }

    @Override
    public void handleProgress(boolean isShow) {
        mFragmentIListener.handleProgress(isShow);
    }

    @Override
    public void handleError(Constants.ApiError err, String msg) {
        Context context = getContext();
        if (context == null)
            return;

        switch (err){
            case RESPONSE_FAILED:
                Snackbar.make(mBinding.layoutParent, msg, Snackbar.LENGTH_SHORT).show();
                break;
            case SERVER_ERROR:
                Snackbar.make(mBinding.layoutParent, R.string.server_error, Snackbar.LENGTH_SHORT).show();
                break;
            case NETWORK_ISSUE:
                Snackbar.make(mBinding.layoutParent, R.string.please_check_your_internet, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onChangeLanguage() {
        getActivity().recreate();
    }

    @Override
    public void login() {
        CommonUtils.hideKeyboard(getActivity());
        String mobileNo = mBinding.etPhone.getText().toString();
        String password = mBinding.etPassword.getText().toString();
        switch (mViewModel.isDataValid(mobileNo, password)){
            case EMPTY_MOBILE_NO:
                Snackbar.make(mBinding.layoutParent, R.string.please_enter_mobile_number, Snackbar.LENGTH_SHORT).show();
                break;
            case EMPTY_PASSWORD:
                Snackbar.make(mBinding.layoutParent, R.string.please_enter_password, Snackbar.LENGTH_SHORT).show();
                break;
            case VALID:
                mViewModel.login(mobileNo, password);
                break;
        }
    }

    @Override
    public void handleSuccess(String msg) {
        Snackbar.make(mBinding.layoutParent, R.string.login_success, Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(() -> mFragmentIListener.replaceFragment(ProductListFragment.newInstance(), ProductListFragment.TAG), 1000);
    }
}