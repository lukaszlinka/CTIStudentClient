package lukasz.ctistudentclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lukasz.ctistudentclient.Models.UserModel;
import lukasz.ctistudentclient.R;
import lukasz.ctistudentclient.Services.UserService;
import lukasz.ctistudentclient.Session.UserSession;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText login, password;
    // adapter REST z Retrofita
    RestAdapter retrofit;
    // nasz interfejs
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_activity_loginButton);
        loginButton.setOnClickListener(this);
        login = (EditText) findViewById(R.id.login_activity_login);
        password = (EditText) findViewById(R.id.login_activity_password);

        retrofit = new RestAdapter.Builder()
                // adres API
                .setEndpoint("http://ctimobile.com/")
                // niech Retrofit loguje wszystko co robi
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        // klient
        userService = retrofit.create(UserService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_loginButton:

                try {
                    userService.postUserLogin(login.getText().toString(), hashPassword(password.getText().toString()), new Callback<UserModel>() {
                        @Override
                        public void success(UserModel userService, Response response) {
                            if (userService != null) {
                                UserSession.getInstance().setUserProfile(userService);
                                finish();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            Toast.makeText(getApplicationContext(), "WWpisz poprawne dane!", Toast.LENGTH_LONG).show();
                        }
                    });

                } catch (Exception e) {
                    Log.d("LoginActivity", e.toString());
                }
                finish();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }

    public String hashPassword(String text) {
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        sha.update(text.getBytes());
        byte[] msgDigest = sha.digest();
        return msgDigest.toString();
    }
}
