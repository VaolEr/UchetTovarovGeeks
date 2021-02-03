package com.uchettovarovgeeks.ui.authentefication;

import android.app.Activity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.uchettovarovgeeks.data.App;
import com.uchettovarovgeeks.ui.MainActivity;
import com.uchettovarovgeeks.R;
import com.uchettovarovgeeks.jsonModel.JsonGetter;
import com.uchettovarovgeeks.jsonModel.auth.Token;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private SharedPreferences sharedPref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final CheckBox adminCheckBox = findViewById(R.id.checkBox);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);

        sharedPref = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        if (sharedPref.contains("email")){
            usernameEditText.setText(sharedPref.getString("email",""));
            passwordEditText.setText(sharedPref.getString("password", ""));
            loginButton.setEnabled(true);
        }

        loginViewModel.getLoginFormState().observe(this, loginFormState -> {
            if (loginFormState == null) {
                return;
            }
            loginButton.setEnabled(loginFormState.isDataValid());
            if (loginFormState.getUsernameError() != null) {
                usernameEditText.setError(getString(loginFormState.getUsernameError()));
            }
            if (loginFormState.getPasswordError() != null) {
                passwordEditText.setError(getString(loginFormState.getPasswordError()));
            }
        });

        loginViewModel.getLoginResult().observe(this, loginResult -> {
            if (loginResult == null) {
                return;
            }
            loadingProgressBar.setVisibility(View.GONE);
            if (loginResult.getError() != null) {

                Toast.makeText(getApplicationContext(), loginResult.getError().toString(), Toast.LENGTH_SHORT).show();
            }
            if (loginResult.getSuccess() != null) {
                updateUiWithUser(loginResult.getSuccess());
            }
            setResult(Activity.RESULT_OK);

        });

        passwordEditText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                //authUser(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
            return false;
        });

        loginButton.setOnClickListener(v -> {
            loadingProgressBar.setVisibility(View.VISIBLE);
            authUser(usernameEditText.getText().toString(),
                    passwordEditText.getText().toString(),
                    adminCheckBox.isChecked());
        });
    }

    private void authUser(String username, String password, Boolean admin){
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", username);
        editor.putString("password", password);
        editor.apply();

        JsonGetter apiholder = App.getApiHolder();
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("email",username);
        hashMap.put("password",password);

        Call<Token> getToken = apiholder.getToken(hashMap);
        getToken.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NotNull Call<Token> call, @NotNull Response<Token> response) {
                if (response.body() != null) {

                    loginViewModel.login(username,
                            password,
                            ((Token) response.body()),
                            admin);

                }else {
                    Toast.makeText(getApplicationContext(), getString(R.string.login_failed) + " " + response.raw().code(), Toast.LENGTH_SHORT).show();
                    loginViewModel.login("",
                            "",
                            new Token(),
                            false);
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
    }

}