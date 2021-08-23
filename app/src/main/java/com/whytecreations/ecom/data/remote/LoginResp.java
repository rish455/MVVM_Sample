package com.whytecreations.ecom.data.remote;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.whytecreations.ecom.utils.Constants;
import com.whytecreations.ecom.utils.LocaleHelper;

/**
 * @author Rishad
 * @since 16-08-2021
 */
public class LoginResp {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String messageEn;
    @SerializedName("message_ar")
    @Expose
    private String messageAr;
    @SerializedName("data")
    @Expose
    private LoginData data;

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return LocaleHelper.getLocale().equalsIgnoreCase(Constants.LANG_ENGLISH) ? messageEn : messageAr;
    }

    public LoginData getData() {
        return data;
    }

    public class LoginData{
        @SerializedName("token")
        @Expose
        private String token;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("notification")
        @Expose
        private String notification;
        @SerializedName("photo")
        @Expose
        private String photo;

        public String getToken() {
            return token;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public String getPhone() {
            return phone;
        }

        public String getDob() {
            return dob;
        }

        public String getGender() {
            return gender;
        }

        public String getNotification() {
            return notification;
        }

        public String getPhoto() {
            return photo;
        }
    }
}
