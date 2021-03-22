package com.sayaji.cogniwideassgin.viewmodel;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sayaji.cogniwideassgin.commonutils.AppConstants;
import com.sayaji.cogniwideassgin.datamodel.LoginDataModel;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public boolean isLoginButtonEnable = false;

    public LoginViewModel() {

    }

    private MutableLiveData<LoginDataModel> userMutableLiveData;

    public LiveData<LoginDataModel> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }


    public void onLoginClicked() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 3000);
    }

    public void onUsernameTextChanged(CharSequence text) {
        Log.d(AppConstants.TAG, email.getValue()+", "+password.getValue());
        LoginDataModel user = new LoginDataModel(email.getValue(), password.getValue());
        if (!user.isEmailValid()) {
            errorEmail.setValue("Error");
        } else {
            errorEmail.setValue(null);
        }
        if (!user.isValidPasswordLength()) {
            errorPassword.setValue("Error");
        } else {
            errorPassword.setValue(null);
        }
        if(errorEmail.getValue() == null && errorPassword.getValue() == null) {
            isLoginButtonEnable = true;
        } else {
            isLoginButtonEnable = false;
        }
        userMutableLiveData.setValue(user);
    }
}
