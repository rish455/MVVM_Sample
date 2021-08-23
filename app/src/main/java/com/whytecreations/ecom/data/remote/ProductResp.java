package com.whytecreations.ecom.data.remote;

import static android.os.Build.VERSION_CODES.R;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.whytecreations.ecom.utils.Constants;
import com.whytecreations.ecom.utils.LocaleHelper;

import java.util.List;

/**
 * @author Rishad
 * @since 16-08-2021
 */
public class ProductResp {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("message_ar")
    @Expose
    private String messageAr;
    @SerializedName("data")
    @Expose
    private List<ProductData> data = null;

    public Integer getStatus() {
        return status;
    }

    public Integer getTotal() {
        return total;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageAr() {
        return messageAr;
    }

    public List<ProductData> getData() {
        return data;
    }

    public static class ProductData{
        @SerializedName("products_id")
        @Expose
        private Integer productsId;
        @SerializedName("brand_name")
        @Expose
        private String brandNameEn;
        @SerializedName("brand_name_ar")
        @Expose
        private String brandNameAr;
        @SerializedName("name")
        @Expose
        private String nameEn;
        @SerializedName("name_ar")
        @Expose
        private String nameAr;
        @SerializedName("photo")
        @Expose
        private String photo;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("previous_price")
        @Expose
        private String previousPrice;
        @SerializedName("wish_list")
        @Expose
        private Integer wishList;

        public Integer getProductsId() {
            return productsId;
        }

        public String getBrandName() {
            return LocaleHelper.getLocale().equalsIgnoreCase(Constants.LANG_ENGLISH) ? brandNameEn : brandNameAr;
        }

        public String getName() {
            return LocaleHelper.getLocale().equalsIgnoreCase(Constants.LANG_ENGLISH) ? nameEn : nameAr;
        }

        public String getPhoto() {
            return photo;
        }

        public String getPrice() {
            return price;
        }

        public String getPreviousPrice() {
            return previousPrice;
        }

        public Integer getWishList() {
            return wishList;
        }
    }
}
