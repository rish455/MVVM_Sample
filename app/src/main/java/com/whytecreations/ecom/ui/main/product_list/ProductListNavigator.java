package com.whytecreations.ecom.ui.main.product_list;


import com.whytecreations.ecom.data.remote.ProductResp;
import com.whytecreations.ecom.ui.base.BaseNavigator;

import java.util.List;

public interface ProductListNavigator extends BaseNavigator {
    void handleSuccess(List<ProductResp.ProductData> dataSet);
}
