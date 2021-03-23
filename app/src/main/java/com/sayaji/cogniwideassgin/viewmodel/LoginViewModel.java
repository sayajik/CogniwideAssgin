package com.sayaji.cogniwideassgin.viewmodel;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sayaji.cogniwideassgin.commonutils.Action;
import com.sayaji.cogniwideassgin.commonutils.AppConstants;
import com.sayaji.cogniwideassgin.datamodel.LoginDataModel;
import com.sayaji.cogniwideassgin.datamodel.Result;

import java.util.List;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<String> errorPassword = new MutableLiveData<>();
    public MutableLiveData<String> errorEmail = new MutableLiveData<>();

    public MutableLiveData<String> email = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    public boolean isLoginButtonEnable = false;

    //Stores actions for view.
    private MutableLiveData<Action> mAction = new MutableLiveData<>();

    public LiveData<Action> getAction() {
        return mAction;
    }


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
        showMovieListScreen();
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

    private void showLoginError() {
        mAction.setValue(new Action(Action.SHOW_INVALID_PASSWARD_OR_LOGIN));
    }


    private void showMovieListScreen() {
        mAction.setValue(new Action(Action.SHOW_MOVIE_LIST));
    }

}
