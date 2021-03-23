package com.sayaji.cogniwideassgin.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.sayaji.cogniwideassgin.R;
import com.sayaji.cogniwideassgin.commonutils.Action;
import com.sayaji.cogniwideassgin.commonutils.AppConstants;
import com.sayaji.cogniwideassgin.databinding.ActivityLoginBinding;
import com.sayaji.cogniwideassgin.datamodel.LoginDataModel;
import com.sayaji.cogniwideassgin.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private Button btnSubmit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLogin(loginViewModel);
        binding.setLifecycleOwner(this);

        btnSubmit = (Button)findViewById(R.id.btn_login) ;

        loginViewModel.getUser().observe(this, new Observer<LoginDataModel>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChanged(@Nullable LoginDataModel user) {
                Log.d(AppConstants.TAG, "observe - "+loginViewModel.isLoginButtonEnable);
                if (user.getEmail().length() > 0 || user.getPassword().length() > 0) {
//                    Toast.makeText(getApplicationContext(), "email : " + user.getEmail() + " password " + user.getPassword(), Toast.LENGTH_SHORT).show();
                }
                if(loginViewModel.isLoginButtonEnable) {
                    btnSubmit.setEnabled(true);
                    btnSubmit.setTextColor(getColor(R.color.colorBlack));
//                    Toast.makeText(getApplicationContext(), "Submit Button Enable", Toast.LENGTH_LONG).show();
                } else {
                    btnSubmit.setEnabled(false);
                    btnSubmit.setTextColor(getColor(R.color.colorGray));
//                    Toast.makeText(getApplicationContext(), "Submit Button Disbled", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginViewModel.getAction().observe(this, new Observer<Action>() {
            @Override
            public void onChanged(@Nullable final Action action) {
                if(action != null){
                    handleAction(action);
                }
            }
        });
    }

    private void handleAction(Action action) {
        switch (action.getValue()){
            case Action.SHOW_MOVIE_LIST:
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case Action.SHOW_INVALID_PASSWARD_OR_LOGIN:
                //show Toast
                break;
        }
    }
}