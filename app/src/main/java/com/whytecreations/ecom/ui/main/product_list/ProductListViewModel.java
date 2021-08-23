package com.whytecreations.ecom.ui.main.product_list;

import androidx.annotation.NonNull;

import com.whytecreations.ecom.api.ApiClient;
import com.whytecreations.ecom.api.ApiInterface;
import com.whytecreations.ecom.data.remote.ProductResp;
import com.whytecreations.ecom.ui.base.BaseViewModel;
import com.whytecreations.ecom.utils.Constants.ApiError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListViewModel extends BaseViewModel<ProductListNavigator> {

    private final ApiInterface mApiService;

    public ProductListViewModel() {
        mApiService = ApiClient.getClient().create(ApiInterface.class);
    }

    void listProducts() {
        getNavigator().handleProgress(true);
        Call<ProductResp> call = mApiService.listProduct(1);
        call.enqueue(new Callback<ProductResp>() {
            @Override
            public void onResponse(@NonNull Call<ProductResp> call, @NonNull Response<ProductResp> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResp resp = response.body();
                    if (resp.getStatus() == 200) {
                        getNavigator().handleSuccess(resp.getData());
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
            public void onFailure(@NonNull Call<ProductResp> call, @NonNull Throwable t) {
                getNavigator().handleError(ApiError.NETWORK_ISSUE, "");
                getNavigator().handleProgress(false);
                //finish();
            }
        });
    }
}
