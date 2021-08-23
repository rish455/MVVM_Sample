package com.whytecreations.ecom.ui.base;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Rishad
 * @since 17/08/2021
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void onBind(int position);
}