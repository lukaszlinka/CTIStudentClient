package lukasz.ctistudentclient.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import lukasz.ctistudentclient.Fragments.CongratulationsFragment;
import lukasz.ctistudentclient.Fragments.DescriptionFrament;
import lukasz.ctistudentclient.Fragments.PriorityFragment;
import lukasz.ctistudentclient.Fragments.ScannerFragment;
import lukasz.ctistudentclient.Fragments.PlaceFragment;
import lukasz.ctistudentclient.Fragments.SummaryFragment;
import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Session.UserSession;
import lukasz.ctistudentclient.R;

public class Notificationactivity extends AppCompatActivity implements View.OnClickListener {

    private Button sendDescBtn;
    private Fragment newFragment, currentFragment;
    private NotificationModel notification;
    private EditText editText;
    private IntentIntegrator qrScan;
    private Fragment[] steps = {new ScannerFragment(), new DescriptionFrament(), new PlaceFragment(), new PriorityFragment(), new SummaryFragment(), new CongratulationsFragment()};
    private int stepsIndex = 0;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationactivity);

        Toast.makeText(getApplicationContext(), UserSession.getInstance().getUserProfile().getCity(), Toast.LENGTH_LONG).show();

        sendDescBtn = (Button) findViewById(R.id.notification_next_button);

        qrScan = new IntentIntegrator(this);

        if (sendDescBtn != null) {
            sendDescBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    currentFragment = newFragment;

                    switch (stepsIndex) {
                        //case "scanner":
                        case 0:
                            if (UserSession.getInstance().getUserNotification().getScanCode().equals("Brak...") || Long.parseLong(UserSession.getInstance().getUserNotification().getScanCode()) <= 0) {
                                EmptyContent();
                                break;
                            }
                            stepsIndex++;
                            newFragment = steps[stepsIndex];// DescriptionFrament();
                            break;
                        //case "description":
                        case 1:
                            notification = UserSession.getInstance().getUserNotification();
                            editText = ((DescriptionFrament) newFragment).getEditText();
                            if (notification != null)
                                notification.setDescription(editText.getText().toString());

                            UserSession.getInstance().setUserNotification(notification);

                            if (UserSession.getInstance().getUserNotification().getImage() == null || UserSession.getInstance().getUserNotification().getDescription().equals("Brak...")) {
                                EmptyContent();
                                break;
                            }

                            stepsIndex++;
                            newFragment = steps[stepsIndex];// new PlaceFragment();
                            break;
                        //case "place":
                        case 2:
                            notification = UserSession.getInstance().getUserNotification();
                            if (notification != null) {
                                notification.setLevel(((PlaceFragment) newFragment).getLevelEditText().getText().toString());
                                notification.setClassroom(((PlaceFragment) newFragment).getRoomEditText().getText().toString());
                                notification.setPlaceDescription(((PlaceFragment) newFragment).getDescriptionEditText().getText().toString());
                            }

                            UserSession.getInstance().setUserNotification(notification);

                            if (Integer.parseInt(UserSession.getInstance().getUserNotification().getClassroom()) <= 0) {
                                EmptyContent();
                                break;
                            }

                            stepsIndex++;
                            newFragment = steps[stepsIndex];//new PriorityFragment();
                            break;
                        //case "priority":
                        case 3:
                            RadioGroup radioGroup = ((PriorityFragment) newFragment).getRadioGroup();
                            int id = radioGroup.getCheckedRadioButtonId();
                            View radioButton = radioGroup.findViewById(id);
                            int index = radioGroup.indexOfChild(radioButton);
                            notification = UserSession.getInstance().getUserNotification();
                            notification.setPriority(index);

                            stepsIndex++;
                            newFragment = steps[stepsIndex];// new CongratulationsFragment();
                            break;
                        case 4:

                            stepsIndex++;
                            newFragment = steps[stepsIndex];// new SummaryFragment();
                            break;
                        //case "congratulations":
                        case 5:
                            notification = UserSession.getInstance().getUserNotification();
                            UserSession.getInstance().getUserNotificationList().add(notification);

                            UserSession.getInstance().clearUserNotification();

                            Intent intent = new Intent(Notificationactivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            break;
                        default:
                            //do work
                            break;
                    }

                    if (newFragment != null && !currentFragment.equals(newFragment)) {

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragmentContainer, newFragment);
                        transaction.addToBackStack(null);

                        transaction.commit();
                    }
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

            currentFragment = scannerFragment;
            newFragment = scannerFragment;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, scannerFragment).commit();
        }
    }

    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                try {

                    JSONObject obj = new JSONObject(result.getContents());
                    Toast.makeText(this, obj.getString("name") + " " + obj.getString("address"), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();

                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void EmptyContent() {
        String text = "";

        switch (stepsIndex) {
            //case "scanner":
            case 0:
                text = "Brak kodu kreskowego";
                break;
            case 1:
                text = "Proszę uzupełnić wszystkie pola";
                break;
            case 2:
                text = "Proszę uzupełnić salę";
                break;
            case 3:
                text = "Brak kodu kreskowego!";
                break;
            default:
                break;
        }

        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        if (stepsIndex > 0)
            stepsIndex--;

        newFragment = steps[stepsIndex];

        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {

        qrScan.initiateScan();
    }

}
