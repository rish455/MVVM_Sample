package com.whytecreations.ecom.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.whytecreations.ecom.R;

/**
 * @author Rishad
 * @since 17-08-2021
 */
public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }

    @BindingAdapter("strikeText")
    public static void setStrikeThroughText(TextView textView, String str) {
        Context context = textView.getContext();
        textView.setText(CommonUtils.getStrikeThroughString(context.getString(R.string.price_with_currency_string, str)));
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        CommonUtils.loadImage(context, url, imageView);
    }
}
