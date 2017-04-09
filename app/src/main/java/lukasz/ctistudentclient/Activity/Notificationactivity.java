package lukasz.ctistudentclient.Activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;

import lukasz.ctistudentclient.Fragments.BlankFragment;
import lukasz.ctistudentclient.Fragments.SendDescriptionFrament;
import lukasz.ctistudentclient.Fragments.SendPlaceFragment;
import lukasz.ctistudentclient.R;

public class Notificationactivity extends AppCompatActivity {

    private Button sendDescBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationactivity);

         sendDescBtn = (Button)findViewById(R.id.notification_next_button);
            if(sendDescBtn!=null){
                sendDescBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SendPlaceFragment newFragment = new SendPlaceFragment();

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

            SendDescriptionFrament sendDescriptionFrament = new SendDescriptionFrament();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            sendDescriptionFrament.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, sendDescriptionFrament).commit();
        }
    }
}
