package lukasz.ctistudentclient.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lukasz.ctistudentclient.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText login, password;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.login_activity_loginButton);
        loginButton.setOnClickListener(this);
        login = (EditText) findViewById(R.id.login_activity_login);
        password = (EditText) findViewById(R.id.login_activity_password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_activity_loginButton:
                if (login.getText().toString().equals("login") && password.getText().toString().equals("password")) {

                    Intent intent = new Intent(this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Niepoprawne dane logowania!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
