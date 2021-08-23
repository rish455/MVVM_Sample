package com.whytecreations.ecom.ui.base;

import com.whytecreations.ecom.utils.Constants;

public interface BaseNavigator {
    void handleProgress(boolean isShow);
    void handleError(Constants.ApiError err, String msg);
}
