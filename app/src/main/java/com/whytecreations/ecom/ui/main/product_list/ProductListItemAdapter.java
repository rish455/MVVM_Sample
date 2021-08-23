package com.whytecreations.ecom.ui.main.product_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.whytecreations.ecom.data.remote.ProductResp;
import com.whytecreations.ecom.databinding.ItemProductListBinding;
import com.whytecreations.ecom.ui.base.BaseViewHolder;

import java.util.List;

public class ProductListItemAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<ProductResp.ProductData> mDataSet;

    public ProductListItemAdapter(Context mContext, List<ProductResp.ProductData> mDataSet) {
        this.mContext = mContext;
        this.mDataSet = mDataSet;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductListBinding binding = ItemProductListBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);
        return new ProductListItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public class ProductListItemViewHolder extends BaseViewHolder {

        ItemProductListBinding mBinding;
        ProductListItemViewModel mItemViewModel;

        public ProductListItemViewHolder(ItemProductListBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        @Override
        public void onBind(int position) {
            ProductResp.ProductData data = mDataSet.get(position);

            mBinding.setProductItem(data);
            mBinding.executePendingBindings();
        }
    }
}
