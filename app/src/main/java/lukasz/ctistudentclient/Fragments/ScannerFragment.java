package lukasz.ctistudentclient.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;

import lukasz.ctistudentclient.Activity.MainActivity;
import lukasz.ctistudentclient.Activity.Notificationactivity;
import lukasz.ctistudentclient.Activity.QrCodeScannerActivity;
import lukasz.ctistudentclient.Models.NotificationModel;
import lukasz.ctistudentclient.Models.Singleton;
import lukasz.ctistudentclient.R;

import static lukasz.ctistudentclient.R.id.buttonScan;

public class ScannerFragment extends Fragment implements View.OnClickListener{

    private Button button;
    private TextView textViewCode;
    private NotificationModel notification;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        SetScannerCode();
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_send_scanner, container, false);

        button = (Button) v.findViewById(R.id.buttonScan);
            button.setOnClickListener(this);
        textViewCode = (TextView) v.findViewById(R.id.fragment_send_scanner_textViewCode);

        SetScannerCode();
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonScan:
                Intent intent = new Intent(getActivity(), QrCodeScannerActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void SetScannerCode(){
        notification = Singleton.getInstance().getUserNotification();
        if(notification!=null && textViewCode!=null)
            textViewCode.setText(notification.getScanCode());
    }
}
