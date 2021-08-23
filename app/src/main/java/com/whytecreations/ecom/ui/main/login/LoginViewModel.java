package com.whytecreations.ecom.ui.main.login;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.whytecreations.ecom.api.ApiClient;
import com.whytecreations.ecom.api.ApiInterface;
import com.whytecreations.ecom.data.remote.LoginResp;
import com.whytecreations.ecom.ui.base.BaseViewModel;
import com.whytecreations.ecom.utils.Constants;
import com.whytecreations.ecom.utils.Constants.LoginDataError;
import com.whytecreations.ecom.utils.Constants.ApiError;
import com.whytecreations.ecom.utils.PreferenceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    private final ApiInterface mApiService;
    //private final Context mContext;

    public LoginViewModel() {
        mApiService = ApiClient.getClient().create(ApiInterface.class);
        //mContext = context;
    }

    public void onClickLogin() {
        getNavigator().login();
    }

    public void onClickArabic() {
        changeLanguage(Constants.LANG_ARABIC);
    }

    public void onClickEnglish() {
        changeLanguage(Constants.LANG_ENGLISH);
    }


    LoginDataError isDataValid(String mobileNo, String password) {
        if (TextUtils.isEmpty(mobileNo)) {
            return LoginDataError.EMPTY_MOBILE_NO;
        }
        if (TextUtils.isEmpty(password)){
            return LoginDataError.EMPTY_PASSWORD;
        }
        return LoginDataError.VALID;
    }

    void login(String mobileNo, String password){
        getNavigator().handleProgress(true);
        Call<LoginResp> call = mApiService.login(mobileNo, password);
        call.enqueue(new Callback<LoginResp>() {
            @Override
            public void onResponse(@NonNull Call<LoginResp> call, @NonNull Response<LoginResp> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResp resp = response.body();
                    if (resp.getStatus() == 200){
                        getNavigator().handleSuccess(resp.getMessage());
                    } else {
                        getNavigator().handleError(ApiError.RESPONSE_FAILED, resp.getMessage());
                    }
                } else {
                    // response error
                    getNavigator().handleError(ApiError.SERVER_ERROR, "");
                }

                getNavigator().handleProgress(false);
            }

            @Override
            public void onFailure(@NonNull Call<LoginResp> call, @NonNull Throwable t) {
                getNavigator().handleError(ApiError.NETWORK_ISSUE, "");
                getNavigator().handleProgress(false);
            }
        });
    }

    private void changeLanguage(String lang){
        if (PreferenceUtils.getLanguage().equalsIgnoreCase(lang))
            return;
        PreferenceUtils.setLanguage(lang);
        getNavigator().onChangeLanguage();
    }
}
