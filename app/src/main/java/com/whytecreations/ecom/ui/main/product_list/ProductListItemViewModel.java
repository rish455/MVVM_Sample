package com.whytecreations.ecom.ui.main.product_list;

import com.whytecreations.ecom.data.remote.ProductResp;

public class ProductListItemViewModel {

    private ProductListItemViewModelListener mListener;

    private ProductResp.ProductData mData;

    public ProductListItemViewModel(ProductResp.ProductData data) {
        this.mData = data;
    }

    public interface ProductListItemViewModelListener {
        void onItemClick(int pos);
    }
}
