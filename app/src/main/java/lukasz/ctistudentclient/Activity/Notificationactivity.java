package lukasz.ctistudentclient.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

import lukasz.ctistudentclient.Fragments.ScannerFragment;
import lukasz.ctistudentclient.Fragments.SendCongratulationsFragment;
import lukasz.ctistudentclient.Fragments.SendDescriptionFrament;
import lukasz.ctistudentclient.Fragments.SendPlaceFragment;
import lukasz.ctistudentclient.Fragments.SendPriorityFragment;
import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Models.Singleton;
import lukasz.ctistudentclient.Models.UserModel;
import lukasz.ctistudentclient.R;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Notificationactivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendDescBtn;
    private String currentview;
    private Fragment newFragment;
    private NotificationModel notification;
    private EditText editText;
    private IntentIntegrator qrScan;
    private Fragment[] steps = {new ScannerFragment(),new SendDescriptionFrament(),new SendPlaceFragment(),new SendPriorityFragment(), new SendCongratulationsFragment()};
    private int stepsIndex = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationactivity);

        currentview = "scanner";

        Toast.makeText(getApplicationContext(), Singleton.getInstance().getUserProfile().getCity(), Toast.LENGTH_LONG).show();

        sendDescBtn = (Button) findViewById(R.id.notification_next_button);

        qrScan = new IntentIntegrator(this);

        if (sendDescBtn != null) {
            sendDescBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    switch (stepsIndex) {
                        //case "scanner":
                        case 0:
                            //currentview = "description";
                            stepsIndex++;
                            newFragment = steps[stepsIndex];// SendDescriptionFrament();
                            break;
                        //case "description":
                        case 1:
                            notification = Singleton.getInstance().getUserNotification();
                            editText = ((SendDescriptionFrament) newFragment).getEditText();
                            if (notification != null)
                                notification.setDescription(editText.getText().toString());

                            Singleton.getInstance().setUserNotification(notification);
                            //currentview = "place";
                            stepsIndex++;
                            newFragment =steps[stepsIndex];// new SendPlaceFragment();
                            break;
                        //case "place":
                        case 2:
                            notification = Singleton.getInstance().getUserNotification();
                            if (notification != null) {
                                notification.setLevel(((SendPlaceFragment) newFragment).getLevelEditText().getText().toString());
                                notification.setClassroom(((SendPlaceFragment) newFragment).getRoomEditText().getText().toString());
                                notification.setPlaceDescription(((SendPlaceFragment) newFragment).getDescriptionEditText().getText().toString());
                            }

                            Singleton.getInstance().setUserNotification(notification);
                            //currentview = "priority";
                            stepsIndex++;
                            newFragment = steps[stepsIndex];//new SendPriorityFragment();
                            break;
                        //case "priority":
                        case 3:
                            //currentview = "congratulations";
                            RadioGroup radioGroup =((SendPriorityFragment) newFragment).getRadioGroup();
                            int id =radioGroup.getCheckedRadioButtonId();
                            View radioButton = radioGroup.findViewById(id);
                            int index = radioGroup.indexOfChild(radioButton);
                            notification = Singleton.getInstance().getUserNotification();
                                notification.setPriority(index);

                            stepsIndex++;
                            newFragment =steps[stepsIndex];// new SendCongratulationsFragment();
                            break;
                        //case "congratulations":
                        case 4:
                            Intent intent = new Intent(Notificationactivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            break;
                        default:
                            //do work
                            break;
                    }

                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentContainer, newFragment);
                    transaction.addToBackStack(null);

                    transaction.commit();
                }
            });
        }
        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }

            ScannerFragment scannerFragment = new ScannerFragment();
            scannerFragment.setArguments(getIntent().getExtras());
            scannerFragment.SetScannerCode();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, scannerFragment).commit();


        }
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                try {

                    JSONObject obj = new JSONObject(result.getContents());
                    Toast.makeText(this, obj.getString("name")+" "+obj.getString("address"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        if (stepsIndex > 0)
            stepsIndex--;

        switch (stepsIndex) {
            //case "scanner":
            case 0:
                newFragment =steps[stepsIndex];// new ScannerFragment();
                break;
            case 1:
                newFragment =steps[stepsIndex];// new SendDescriptionFrament();
                break;
            case 2:
                newFragment =steps[stepsIndex];// new SendPlaceFragment();
                break;
            case 3:
                newFragment =steps[stepsIndex];// new SendCongratulationsFragment();
                break;
            default:
                break;
        }
        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
//initiating the qr code scan
        qrScan.initiateScan();
    }

}
