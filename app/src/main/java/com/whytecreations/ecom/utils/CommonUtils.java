package com.whytecreations.ecom.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManagerKt;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentTransactionKt;
import androidx.transition.FragmentTransitionSupport;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;
import com.whytecreations.ecom.R;

/**
 * @author Rishad
 * @since 17-08-2021
 */
public final class CommonUtils {

    public static void replaceFragment(FragmentManager fm, int layout, Fragment fragment, String tag, boolean isInitial) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (!isInitial)
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
        transaction.replace(layout, fragment, tag);
        if (!isInitial)
            transaction.addToBackStack(tag);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    public static void animate(Context context, int animRes, View view) {
        Animation animation = AnimationUtils.loadAnimation(context, animRes);
        animation.setDuration(650);
        animation.setStartOffset(50);
        view.startAnimation(animation);
    }

    public static int dpToPx(Resources r, int dp) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public static void hideKeyboard(Activity activity) {
        if (activity == null)
            return;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void loadImage(Context context, String url, ImageView ivImage) {
        RequestOptions options = new RequestOptions()
                .placeholder(getShimmerPlaceholder())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);

        Glide.with(context)
                .load(url)
                .apply(options)
                .into(ivImage);
    }

    private static Drawable getShimmerPlaceholder() {
        Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
                .setDuration(1800) // how long the shimmering animation takes to do one full sweep
                .setBaseAlpha(0.5f) //the alpha of the underlying children
                .setHighlightAlpha(0.4f) // the shimmer alpha amount
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build();

        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);
        return shimmerDrawable;
    }

    public static SpannableString getStrikeThroughString(String content) {
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new StrikethroughSpan(), 0, spannableString.length(), 0);
        return spannableString;
    }
}
