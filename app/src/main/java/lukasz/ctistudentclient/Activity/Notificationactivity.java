package lukasz.ctistudentclient.Activity;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import lukasz.ctistudentclient.Fragments.ScannerFragment;
import lukasz.ctistudentclient.Fragments.SendCongratulationsFragment;
import lukasz.ctistudentclient.Fragments.SendDescriptionFrament;
import lukasz.ctistudentclient.Fragments.SendPlaceFragment;
import lukasz.ctistudentclient.Fragments.SendPriorityFragment;
import lukasz.ctistudentclient.Models.Singleton;
import lukasz.ctistudentclient.R;

public class Notificationactivity extends AppCompatActivity {

    private Button sendDescBtn;
    private String currentview;
    private Fragment newFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationactivity);

        currentview = "scanner";

        Toast.makeText(getApplicationContext(), Singleton.getInstance().getUserProfile().getCity(), Toast.LENGTH_LONG).show();

         sendDescBtn = (Button)findViewById(R.id.notification_next_button);
            if(sendDescBtn!=null){
                sendDescBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        switch(currentview) {
                            case "scanner":
                                currentview = "description";
                                newFragment = new SendDescriptionFrament();
                                break;
                            case "description":
                                currentview = "place";
                                newFragment = new SendPlaceFragment();
                                break;
                            case "place":
                                currentview = "priority";
                                newFragment = new SendPriorityFragment();
                                break;
                            case "priority":
                                currentview = "congratulations";
                                newFragment = new SendCongratulationsFragment();
                                break;
                            case "congratulations":
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

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            scannerFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, scannerFragment).commit();
        }
    }
}
