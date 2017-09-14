package lukasz.ctistudentclient.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lukasz.ctistudentclient.R;

public class SendFeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    Button sendButton;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        sendButton = (Button) findViewById(R.id.activity_sendFeedback_sendButton);
        sendButton.setOnClickListener(this);
        editText = (EditText) findViewById(R.id.activity_sendFeedback_editText);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_sendFeedback_sendButton:
                if (editText != null) {
                    if (!editText.getText().toString().matches("")) {
                        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                        dlgAlert.setMessage("Uwagi zostały przesłane :)");
                        dlgAlert.setTitle("Dziękujemy!");
                        dlgAlert.setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        SendFeedbackActivity.super.finish();
                                    }
                                });
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wpisz swoje uwagi!", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
}
