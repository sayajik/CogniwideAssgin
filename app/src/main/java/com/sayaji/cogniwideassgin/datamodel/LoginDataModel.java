package com.sayaji.cogniwideassgin.datamodel;

import android.util.Patterns;

public class LoginDataModel {
    private String mEmail;
    private String mPassword;


    public LoginDataModel(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        if (mEmail == null) {
            return "";
        }
        return mEmail;
    }


    public String getPassword() {

        if (mPassword == null) {
            return "";
        }
        return mPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches();
    }


    public boolean isValidPasswordLength() {
        return getPassword().length() >= 6 && getPassword().length() <= 12 ;
    }
}
