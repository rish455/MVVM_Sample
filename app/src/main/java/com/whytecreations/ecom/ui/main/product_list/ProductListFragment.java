package com.whytecreations.ecom.ui.main.product_list;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.whytecreations.ecom.BR;
import com.whytecreations.ecom.R;
import com.whytecreations.ecom.data.remote.ProductResp;
import com.whytecreations.ecom.databinding.FragmentProductListBinding;
import com.whytecreations.ecom.ui.base.BaseFragment;
import com.whytecreations.ecom.utils.CommonUtils;
import com.whytecreations.ecom.utils.Constants;
import com.whytecreations.ecom.utils.GridSpacingItemDecoration;

import java.util.List;

public class ProductListFragment extends BaseFragment<FragmentProductListBinding, ProductListViewModel>
        implements ProductListNavigator {

    public static final String TAG = ProductListFragment.class.getSimpleName();
    private ProductListViewModel mViewModel;
    private FragmentProductListBinding mBinding;

    public ProductListFragment() {
        // Required empty public constructor
    }

    public static ProductListFragment newInstance() {
        ProductListFragment fragment = new ProductListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);
        super.onCreate(savedInstanceState);
        mViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding = getViewDataBinding();
        mViewModel.listProducts();
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product_list;
    }

    @Override
    public ProductListViewModel getViewModel() {
        return mViewModel;
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

        switch (err) {
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
    public void handleSuccess(List<ProductResp.ProductData> dataSet) {
        setUp(dataSet);
    }


    private void setUp(List<ProductResp.ProductData> dataSet) {
        ProductListItemAdapter adapter = new ProductListItemAdapter(getContext(), dataSet);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mBinding.rvProducts.setLayoutManager(layoutManager);
        mBinding.rvProducts.addItemDecoration(new GridSpacingItemDecoration(2, CommonUtils.dpToPx(getResources(), 10), true));
        mBinding.rvProducts.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvProducts.setAdapter(adapter);
    }
}